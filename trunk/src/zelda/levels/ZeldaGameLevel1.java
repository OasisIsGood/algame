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

import zelda.base.GuardMovableDriver;
import zelda.base.LinkMoveStrategy;
import zelda.entity.characters.Guard;
import zelda.entity.characters.Link;
import zelda.entity.characters.ZeldaPrincess;
import zelda.entity.decors.Bush;
import zelda.entity.decors.Hammer;
import zelda.entity.decors.SuperPotion;
import zelda.entity.decors.Sword;
import zelda.entity.decors.Tree;
import zelda.game.GameZelda;
import zelda.game.GameZeldaAWTImpl;
import zelda.rule.ZeldaMoveBlockers;
import zelda.rule.ZeldaOverlaps;

public class ZeldaGameLevel1 extends GameLevelDefaultImpl {

	Canvas canvas;

	public static final int SPRITE_SIZE = 24;
	public static final int NUMBER_OF_ENNEMYS = 3;
	protected static final int NB_ROWS = GameZeldaAWTImpl.NB_ROWS;
	protected static final int NB_COLUMNS = GameZeldaAWTImpl.NB_COLUMNS;

	protected IntegerObservable win;

	public static enum direction {
		UP, DOWN, RIGHT, LEFT
	};

	public ZeldaGameLevel1(GameZelda g) {
		super(g);
		win = g.win();
		canvas = g.getCanvas();
	}
	
	@Override
	protected void init() {
		// TODO Mettre en place cette méthode et le tour est joué.
		OverlapProcessor overlapProcessor = new OverlapProcessorDefaultImpl();
		// TODO mieux définir les positions de départ et trouver des vrais
		// variables !!!!
		OverlapRuleApplier overlapRules = new ZeldaOverlaps(new Point(), 
				new Point(), life[0], score[0], win, canvas);

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

		Link link = new Link(canvas);
		GameMovableDriverDefaultImpl pacDriver = new GameMovableDriverDefaultImpl();
		MoveStrategyKeyboard keyStr = new LinkMoveStrategy(link);
		pacDriver.setStrategy(keyStr);
		pacDriver.setmoveBlockerChecker(moveBlockerChecker);
		canvas.addKeyListener(keyStr);
		link.setDriver(pacDriver);
		link.setPosition(new Point(40 * SPRITE_SIZE, 20 * SPRITE_SIZE));
		universe.addGameEntity(link);

		// Murs sur les 4 cotés
		for (int i = 0; i < NB_COLUMNS/2; ++i) {
			// murs horizontaux
			universe.addGameEntity(new Tree(canvas, 
					new Point(i * SPRITE_SIZE, 0)));
			universe.addGameEntity(new Tree(canvas, 
					new Point(i * SPRITE_SIZE, (NB_ROWS - 1) * SPRITE_SIZE)));
		}
		for (int j = 0; j < NB_ROWS/2; ++j) {
			// murs verticaux
			universe.addGameEntity(new Tree(canvas, 
					new Point(0, j * SPRITE_SIZE)));
			universe.addGameEntity(new Tree(canvas, 
					new Point((NB_COLUMNS - 1) * SPRITE_SIZE, j * SPRITE_SIZE)));
		}

		/*
		 * for (int i = 0; i < NB_COLUMNS; ++i) { for (int j = 0; j < NB_ROWS;
		 * ++j) { // mur horizontal milieu if ((j == (NB_ROWS / 2)) && (i !=
		 * (NB_COLUMNS / 2 - 2)) && (i != (NB_COLUMNS / 2 - 1)) && (i !=
		 * (NB_COLUMNS / 2)) && (i != (NB_COLUMNS / 2 + 1)) && (i != (NB_COLUMNS
		 * / 2 + 2))) universe.addGameEntity(new Tree(canvas, new Point(i
		 * SPRITE_SIZE, j SPRITE_SIZE))); // mur vertical milieu if ((i ==
		 * (NB_COLUMNS / 2)) && (j != (NB_ROWS / 2 - 1)) && (j != (NB_ROWS / 2))
		 * && (j != (NB_ROWS / 2 + 1))) universe.addGameEntity(new Tree(canvas,
		 * new Point(i SPRITE_SIZE, j SPRITE_SIZE))); } }
		 */

		universe.addGameEntity(new ZeldaPrincess(canvas, new Point(
				2 * SPRITE_SIZE, 2 * SPRITE_SIZE)));
		universe.addGameEntity(new Sword(canvas, new Point(
				38 * SPRITE_SIZE, 20 * SPRITE_SIZE)));
		universe.addGameEntity(new Hammer(canvas, new Point(20 * SPRITE_SIZE,
				20 * SPRITE_SIZE)));
		universe.addGameEntity(new Bush(canvas, new Point(14 * SPRITE_SIZE,
				14 * SPRITE_SIZE)));
		universe.addGameEntity(new Bush(canvas, new Point(10 * SPRITE_SIZE,
				25 * SPRITE_SIZE)));
		universe.addGameEntity(new SuperPotion(canvas, new Point(
				10 * SPRITE_SIZE, 10 * SPRITE_SIZE)));

		Guard guard = new Guard(canvas, new Point(2 * SPRITE_SIZE,
				4 * SPRITE_SIZE));
		GameMovableDriverDefaultImpl guardDriv = new GuardMovableDriver();
		MoveStrategyRandom ranStr = new MoveStrategyRandom();
		guardDriv.setStrategy(ranStr);
		guardDriv.setmoveBlockerChecker(moveBlockerChecker);
		guard.setDriver(guardDriv);
		universe.addGameEntity(guard);

		//addWalls(new Point(18, 18), direction.UP, 16);
		//addWalls(new Point(10, 10), direction.DOWN, 4);
		//addWalls(new Point(10, 10), direction.RIGHT, 4);
		//addWalls(new Point(15, 15), direction.LEFT, 4);
	}

	@SuppressWarnings("unused")
	private void addWalls(Point origin, direction dir, int num) {
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
	}
}
