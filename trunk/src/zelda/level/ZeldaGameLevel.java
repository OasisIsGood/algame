package zelda.level;

import gameframework.base.IntegerObservable;
import gameframework.game.CanvasDefaultImpl;
import gameframework.game.GameLevelDefaultImpl;
import gameframework.game.GameUniverse;
import gameframework.game.GameUniverseViewPort;
import gameframework.game.GameUniverseViewPortDefaultImpl;
import gameframework.game.MoveBlockerChecker;
import gameframework.game.MoveBlockerCheckerDefaultImpl;
import gameframework.game.MoveBlockerRuleApplierDefaultImpl;
import gameframework.game.OverlapProcessor;
import gameframework.game.OverlapProcessorDefaultImpl;
import gameframework.game.OverlapRuleApplier;

import java.awt.Canvas;
import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import zelda.game.GameZelda;
import zelda.game.GameZeldaUniverseDefaultImpl;
//import zelda.rule.ZeldaMoveBlockers;
import zelda.rule.ZeldaOverlaps;

public class ZeldaGameLevel extends GameLevelDefaultImpl {
	Canvas canvas;

	public static final int SPRITE_SIZE = 16;
	public int NUMBER_OF_ENNEMYS = 0; // Faire en sorte que cette valeur est
										// une raison d'�tre et corresponde �
										// quelque chose
	protected static final int NB_ROWS = 31;
	protected static final int NB_COLUMNS = 50;

	protected IntegerObservable win;

	public static enum direction {
		UP, DOWN, RIGHT, LEFT
	};

	private File f;

	public ZeldaGameLevel(GameZelda g, File f) {
		super(g);
		win = g.win();
		canvas = g.getCanvas();
		this.f = f;
	}

	@Override
	protected void init() {
		OverlapProcessor overlapProcessor = new OverlapProcessorDefaultImpl();
		OverlapRuleApplier overlapRules = new ZeldaOverlaps(new Point(
				14 * SPRITE_SIZE, 17 * SPRITE_SIZE), new Point(
				14 * SPRITE_SIZE, 15 * SPRITE_SIZE), life[0], score[0], win,
				canvas);

		overlapProcessor.setOverlapRules(overlapRules);

		MoveBlockerChecker moveBlockerChecker = new MoveBlockerCheckerDefaultImpl();
		//moveBlockerChecker.setMoveBlockerRules(new ZeldaMoveBlockers());
		moveBlockerChecker.setMoveBlockerRules(new MoveBlockerRuleApplierDefaultImpl());

		universe = new GameZeldaUniverseDefaultImpl(moveBlockerChecker,
				overlapProcessor);
		overlapRules.setUniverse(universe);

		gameBoard = new GameUniverseViewPortDefaultImpl(canvas, universe);
		((GameUniverseViewPortDefaultImpl) gameBoard)
				.setBackground("images/background/background_image_zelda2.gif");
		((CanvasDefaultImpl) canvas).setDrawingGameBoard(gameBoard);
		
		try {
			LevelReader reader = checkExtension(f);
			reader.read();
		} catch (FileNotFoundException e) {
			System.err.println(e.getMessage());
			System.exit(0);
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}

	private LevelReader checkExtension(File f) throws IOException {
		if (f.getName().endsWith(".xml"))
			return new XMLReader(canvas, universe, f);
		else
			return new TextReader(canvas, universe, f);
	}
	
	public GameUniverseViewPort getGameBoard() {
		return gameBoard;
	}
	
	public GameUniverse getUniverse() {
		return universe;
	}
}
