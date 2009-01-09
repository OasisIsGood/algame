package zelda.levels;

import gameframework.base.IntegerObservable;
import gameframework.game.CanvasDefaultImpl;
import gameframework.game.GameLevelDefaultImpl;
import gameframework.game.GameUniverseDefaultImpl;
import gameframework.game.GameUniverseViewPortDefaultImpl;
import gameframework.game.MoveBlockerChecker;
import gameframework.game.MoveBlockerCheckerDefaultImpl;
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
import zelda.rule.ZeldaMoveBlockers;
import zelda.rule.ZeldaOverlaps;

public class ZeldaGameLevel extends GameLevelDefaultImpl {
	Canvas canvas;

	public static final int SPRITE_SIZE = 16;
	public static final int NUMBER_OF_ENNEMYS = 2;
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
		// TODO mieux définir les positions de départ et trouver des vrais
		// variables !!!!
		OverlapRuleApplier overlapRules = new ZeldaOverlaps(new Point(
				14 * SPRITE_SIZE, 17 * SPRITE_SIZE), new Point(
				14 * SPRITE_SIZE, 15 * SPRITE_SIZE), life[0], score[0], win,
				canvas);

		overlapProcessor.setOverlapRules(overlapRules);

		MoveBlockerChecker moveBlockerChecker = new MoveBlockerCheckerDefaultImpl();
		moveBlockerChecker.setMoveBlockerRules(new ZeldaMoveBlockers());

		universe = new GameZeldaUniverseDefaultImpl(moveBlockerChecker,
				overlapProcessor);
		overlapRules.setUniverse(universe);

		gameBoard = new GameUniverseViewPortDefaultImpl(canvas, universe);
		((GameUniverseViewPortDefaultImpl) gameBoard)
				.setBackground("images/background/background_image_zelda.gif");
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
		if(f.getName().endsWith(".xml"))
			return new XMLReader(canvas, universe, f);
		else
			return new TextReader(canvas, universe, f);
	}

	/*private void addWalls(Point origin, direction dir, int num) {
		if (dir.equals(direction.UP)) {
			for (int i = num; i > 0; --i) {
				universe.addGameEntity(new Tree(canvas, 
						new Point((int) origin.getX() * SPRITE_SIZE,
								(int) ((origin.getY() - i) * SPRITE_SIZE))));
			}
		}
		if (dir.equals(direction.DOWN)) {
			addWalls(new Point((int) origin.getX(), (int) origin.getY() + num),
					direction.UP, num);
		}
		if (dir.equals(direction.LEFT)) {
			for (int i = 0; i < num; ++i) {
				universe.addGameEntity(new Tree(canvas, 
						new Point((int) (origin.getX() - i) * SPRITE_SIZE,
								(int) (origin.getY() * SPRITE_SIZE))));
			}
		}
		if (dir.equals(direction.RIGHT)) {
			addWalls(new Point((int) origin.getX() + num, (int) origin.getY()),
					direction.LEFT, num);
		}
	}*/
}
