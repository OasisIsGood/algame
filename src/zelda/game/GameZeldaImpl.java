package zelda.game;

import gameframework.base.IntegerObservable;
import gameframework.game.CanvasDefaultImpl;
import gameframework.game.Game;
import gameframework.game.GameLevel;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Container;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import zelda.ZeldaGameLevel;

public class GameZeldaImpl implements Game, Observer {
	protected static final int NB_ROWS = 31;
	protected static final int NB_COLUMNS = 28;
	protected static final int SPRITE_SIZE = 16;
	public static final int MAX_NUMBER_OF_PLAYER = 4;
	public static final int NUMBER_OF_LIVES = 100;

	protected CanvasDefaultImpl defaultCanvas = null;
	protected IntegerObservable score[] = new IntegerObservable[MAX_NUMBER_OF_PLAYER];
	protected IntegerObservable life[] = new IntegerObservable[MAX_NUMBER_OF_PLAYER];
	
	private JFrame f;
	private ZeldaGameLevel currentPlayedLevel = null;
	
	protected int levelNumber;
	protected ArrayList<GameLevel> gameLevels;
	protected Iterator<GameLevel> itLevel;

	protected JLabel lifeText, scoreText;
	protected JLabel information;
	protected JLabel informationValue;
	protected JLabel lifeValue, scoreValue;
	protected JLabel currentLevel;
	protected JLabel currentLevelValue;

	public GameZeldaImpl() {
		for (int i = 0; i < MAX_NUMBER_OF_PLAYER; ++i) {
			score[i] = new IntegerObservable();
			life[i] = new IntegerObservable();
		}
		
		lifeText = new JLabel("Lives:");
		scoreText = new JLabel("Score:");
		information = new JLabel("State:");
		informationValue = new JLabel("Playing");
		currentLevel = new JLabel("Level:");
		createGUI();
	}
	
	@Override
	public void createGUI() {
		f = new JFrame("Zelda Game");
		f.dispose();

		createMenuBar();
		Container c = createStatusBar();

		defaultCanvas = new CanvasDefaultImpl();
		defaultCanvas.setSize(SPRITE_SIZE * NB_COLUMNS, SPRITE_SIZE * NB_ROWS);
		f.add(defaultCanvas);
		f.add(c, BorderLayout.NORTH);
		f.pack();
		f.setVisible(true);

		f.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}

	private Container createStatusBar() {
		JPanel c = new JPanel();
		GridBagLayout layout = new GridBagLayout();
		c.setLayout(layout);
		lifeValue = new JLabel(Integer.toString(life[0].getValue()));
		scoreValue = new JLabel(Integer.toString(score[0].getValue()));
		currentLevelValue = new JLabel(Integer.toString(levelNumber));
		c.add(lifeText);
		c.add(lifeValue);
		c.add(scoreText);
		c.add(scoreValue);
		c.add(currentLevel);
		c.add(currentLevelValue);
		c.add(information);
		c.add(informationValue);
		return c;
	}

	private void createMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		JMenu file = new JMenu("file");
		JMenuItem start = new JMenuItem("new game");
		JMenuItem save = new JMenuItem("save");
		JMenuItem restore = new JMenuItem("load");
		JMenuItem quit = new JMenuItem("quit");
		JMenu game = new JMenu("game");
		JMenuItem pause = new JMenuItem("pause");
		JMenuItem resume = new JMenuItem("resume");
		menuBar.add(file);
		menuBar.add(game);
		f.setJMenuBar(menuBar);

		start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				start();
			}
		});
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				save();
			}
		});
		restore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				restore();
			}
		});
		quit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		pause.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pause();
			}
		});
		resume.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resume();
			}
		});

		file.add(start);
		file.add(save);
		file.add(restore);
		file.add(quit);
		game.add(pause);
		game.add(resume);
	}

	@Override
	public Canvas getCanvas() {
		return defaultCanvas;
	}

	@Override
	public IntegerObservable[] life() {
		return life;
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void restore() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void save() {
		// TODO Auto-generated method stub. Penser a un système de sauvegarde

	}

	@Override
	public IntegerObservable[] score() {
		return score;
	}

	@Override
	public void start() {
		// TODO Auto-generated method stub
		for (int i = 0; i < MAX_NUMBER_OF_PLAYER; ++i) {
			score[i].addObserver(this);
			life[i].addObserver(this);
			life[i].setValue(NUMBER_OF_LIVES);
			score[i].setValue(0);
		}
		itLevel = gameLevels.iterator();
		levelNumber = 0;
		try {
			if (currentPlayedLevel != null && currentPlayedLevel.isAlive()) {
				currentPlayedLevel.interrupt();
				currentPlayedLevel = null;
			}
			currentPlayedLevel = (ZeldaGameLevel) itLevel.next();
			levelNumber++;
			currentLevelValue.setText(Integer.toString(levelNumber));
			currentPlayedLevel.start();
			currentPlayedLevel.join();
		} catch (Exception e) {
		}
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		if (arg0 instanceof IntegerObservable) {
			IntegerObservable observable = (IntegerObservable) arg0;
			handleScoreObservable(observable);
			handleLifeObservable(observable);
		}
	}
	
	private void handleLifeObservable(IntegerObservable observable) {
		for (IntegerObservable lifeObservable : life) {
			if (observable == lifeObservable) {
				int lives = observable.getValue();
 				lifeValue.setText(Integer.toString(lives));
 				if (lives == 0) {
 					informationValue.setText("Defeat");
					currentPlayedLevel.interrupt();
					currentPlayedLevel.end();
				}
			}
		}
	}

	private void handleScoreObservable(IntegerObservable observable) {
		for (IntegerObservable scoreObservable : score) {
			if (observable == scoreObservable) {
				scoreValue.setText(Integer.toString(observable.getValue()));
			}
		}
	}

	public void setLevels(ArrayList<GameLevel> levels) {
		gameLevels = levels;
		//TODO Penser à vérifier qu'il ne faille pas faire un update du level
	}
}
