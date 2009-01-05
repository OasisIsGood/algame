package zelda;

import gameframework.base.MoveStrategyKeyboard;
import gameframework.game.CanvasDefaultImpl;
import gameframework.game.Game;
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

import zelda.base.LinkMoveStrategy;
import zelda.entity.characters.Guard;
import zelda.entity.characters.Link;
import zelda.entity.decors.Bomb;
import zelda.entity.decors.Bush;
import zelda.entity.decors.Hammer;
import zelda.entity.decors.SuperPotion;
import zelda.entity.decors.Tree;
import zelda.rule.ZeldaMoveBlockers;
import zelda.rule.ZeldaOverlaps;

public class ZeldaGameLevel extends GameLevelDefaultImpl {
	
	Canvas canvas;
	
	public static final int SPRITE_SIZE = 16;
	public static final int NUMBER_OF_ENNEMYS = 2;
	protected static final int NB_ROWS = 31;
	protected static final int NB_COLUMNS = 50;

	public ZeldaGameLevel(Game g) {
		super(g);
		canvas = g.getCanvas();
	}

	@Override
	protected void init() {
		// TODO Mettre en place cette méthode et le tour est joué.
		OverlapProcessor overlapProcessor = new OverlapProcessorDefaultImpl();
		// TODO mieux définir les positions de départ et trouver des vrais variables !!!!
		OverlapRuleApplier overlapRules = new ZeldaOverlaps(
				new Point(14 * SPRITE_SIZE, 17 * SPRITE_SIZE), new Point(
						  14 * SPRITE_SIZE, 15 * SPRITE_SIZE), life[0], score[0]);
		overlapProcessor.setOverlapRules(overlapRules);
		
		MoveBlockerChecker moveBlockerChecker = new MoveBlockerCheckerDefaultImpl();
		moveBlockerChecker.setMoveBlockerRules(new ZeldaMoveBlockers());
		
		universe = new GameUniverseDefaultImpl(moveBlockerChecker, overlapProcessor);
 		overlapRules.setUniverse(universe);
 		
 		gameBoard = new GameUniverseViewPortDefaultImpl(canvas, universe);
 		((GameUniverseViewPortDefaultImpl)gameBoard).setBackground("images/background/background_image_zelda.gif");
		((CanvasDefaultImpl) canvas).setDrawingGameBoard(gameBoard);
		
		Link myPac = new Link(canvas);
		GameMovableDriverDefaultImpl pacDriver = new GameMovableDriverDefaultImpl();
		MoveStrategyKeyboard keyStr = new LinkMoveStrategy();
		pacDriver.setStrategy(keyStr);
		pacDriver.setmoveBlockerChecker(moveBlockerChecker);
		canvas.addKeyListener(keyStr);
		myPac.setDriver(pacDriver);
		myPac.setPosition(new Point(14 * SPRITE_SIZE, 17 * SPRITE_SIZE));
		universe.addGameEntity(myPac);
		
		// Murs sur les 4 cotés
		for (int i = 0; i <= NB_COLUMNS; ++i) { 
			universe.addGameEntity(new Tree(canvas, new Point(i * SPRITE_SIZE, 0)));
			universe.addGameEntity(new Tree(canvas, new Point(i * SPRITE_SIZE, (NB_ROWS+1) * SPRITE_SIZE)));
			universe.addGameEntity(new Tree(canvas, new Point(0, i * SPRITE_SIZE)));
			universe.addGameEntity(new Tree(canvas, new Point(NB_COLUMNS * SPRITE_SIZE, i * SPRITE_SIZE)));
		}
		
		universe.addGameEntity(new Guard(canvas/*, new Point(14 * SPRITE_SIZE, 14 * SPRITE_SIZE)*/));
		universe.addGameEntity(new Bush(canvas, new Point(14 * SPRITE_SIZE, 14 * SPRITE_SIZE)));
		universe.addGameEntity(new Bush(canvas, new Point(10 * SPRITE_SIZE, 25 * SPRITE_SIZE)));
		universe.addGameEntity(new Bomb(canvas, new Point(4 * SPRITE_SIZE, 28 * SPRITE_SIZE)));
		universe.addGameEntity(new SuperPotion(canvas, new Point(10 * SPRITE_SIZE, 10 * SPRITE_SIZE)));
		universe.addGameEntity(new Hammer(canvas, new Point(18 * SPRITE_SIZE, 18 * SPRITE_SIZE)));
	}
}
