package zelda.levels;

import gameframework.base.IntegerObservable;
import gameframework.base.MoveStrategyKeyboard;
import gameframework.base.MoveStrategyRandom;
import gameframework.game.CanvasDefaultImpl;
import gameframework.game.GameLevelDefaultImpl;
import gameframework.game.GameMovableDriverDefaultImpl;
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

import zelda.base.GuardMovableDriver;
import zelda.base.LinkMoveStrategy;
import zelda.entity.EntityFactory;
import zelda.entity.characters.Guard;
import zelda.entity.characters.Link;
import zelda.entity.characters.ZeldaPrincess;
import zelda.entity.decors.Bush;
import zelda.entity.decors.Hammer;
import zelda.entity.decors.SuperPotion;
import zelda.entity.decors.Tree;
import zelda.game.GameZelda;
import zelda.levels.ZeldaGameLevel1.direction;
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
		// TODO Mettre en place cette méthode et le tour est joué.
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

		universe = new GameUniverseDefaultImpl(moveBlockerChecker,
				overlapProcessor);
		overlapRules.setUniverse(universe);

		gameBoard = new GameUniverseViewPortDefaultImpl(canvas, universe);
		((GameUniverseViewPortDefaultImpl) gameBoard)
				.setBackground("images/background/background_image_zelda.gif");
		((CanvasDefaultImpl) canvas).setDrawingGameBoard(gameBoard);
		
		try {
			LevelReader reader = new TextReader(canvas, universe, f);
			reader.read();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
