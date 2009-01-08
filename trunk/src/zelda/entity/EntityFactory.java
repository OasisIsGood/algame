package zelda.entity;

import gameframework.base.MoveStrategyKeyboard;
import gameframework.base.MoveStrategyRandom;
import gameframework.game.GameMovableDriverDefaultImpl;
import gameframework.game.GameUniverse;
import gameframework.game.MoveBlockerChecker;
import gameframework.game.MoveBlockerCheckerDefaultImpl;

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
import zelda.entity.decors.Tree;
import zelda.levels.ZeldaGameLevel1.direction;
import zelda.rule.ZeldaMoveBlockers;

public class EntityFactory {
	// TODO Créer les fonctions de creation des entités
	public static void createLink(Point p, Canvas canvas, GameUniverse universe) {

		MoveBlockerChecker moveBlockerChecker = new MoveBlockerCheckerDefaultImpl();
		moveBlockerChecker.setMoveBlockerRules(new ZeldaMoveBlockers());

		Link link = new Link(canvas);
		GameMovableDriverDefaultImpl pacDriver = new GameMovableDriverDefaultImpl();
		MoveStrategyKeyboard keyStr = new LinkMoveStrategy(link);
		pacDriver.setStrategy(keyStr);
		pacDriver.setmoveBlockerChecker(moveBlockerChecker);
		canvas.addKeyListener(keyStr);
		link.setDriver(pacDriver);
		link.setPosition(p);
		universe.addGameEntity(link);
	}

	public static void createZelda(Point p, Canvas canvas, GameUniverse universe) {
		universe.addGameEntity(new ZeldaPrincess(canvas, p));
	}

	public static void createPotion(Point p, Canvas canvas,
			GameUniverse universe) {

	}

	public static void createGuard(Point p, Canvas canvas, GameUniverse universe) {
		MoveBlockerChecker moveBlockerChecker = new MoveBlockerCheckerDefaultImpl();
		moveBlockerChecker.setMoveBlockerRules(new ZeldaMoveBlockers());

		Guard guard = new Guard(canvas, p);
		GameMovableDriverDefaultImpl guardDriv = new GuardMovableDriver();
		MoveStrategyRandom ranStr = new MoveStrategyRandom();
		guardDriv.setStrategy(ranStr);
		guardDriv.setmoveBlockerChecker(moveBlockerChecker);
		guard.setDriver(guardDriv);
		universe.addGameEntity(guard);
	}

	public static void createBomb(Point p, Canvas canvas, GameUniverse universe) {

	}

	public static void createBush(Point p, Canvas canvas, GameUniverse universe) {
		universe.addGameEntity(new Bush(canvas, p));
	}

	public static void createSuperPotion(Point p, Canvas canvas,
			GameUniverse universe) {
		universe.addGameEntity(new SuperPotion(canvas, p));
	}

	public static void createTree(Point p, Canvas canvas, GameUniverse universe) {

	}

	public static void createHammer(Point p, Canvas canvas,
			GameUniverse universe) {
		universe.addGameEntity(new Hammer(canvas, p));
	}

	public static void createWall(Point p, direction dir, int num,
			Canvas canvas, GameUniverse universe) {
		int SPRITE_SIZE = 0; //TODO changer ca
		if (dir.equals(direction.UP)) {
			for (int i = num; i > 0; --i) {
				universe.addGameEntity(new Tree(canvas, new Point((int) p
						.getX()
						* SPRITE_SIZE, (int) ((p.getY() - i) * SPRITE_SIZE))));
			}
		}
		if (dir.equals(direction.DOWN)) {
			createWall(new Point((int) p.getX(), (int) p.getY() + num),
					direction.UP, num, canvas, universe);
		}
		if (dir.equals(direction.LEFT)) {
			for (int i = 0; i < num; ++i) {
				universe.addGameEntity(new Tree(canvas, new Point((int) (p
						.getX() - i)
						* SPRITE_SIZE, (int) (p.getY() * SPRITE_SIZE))));
			}
		}
		if (dir.equals(direction.RIGHT)) {
			createWall(new Point((int) p.getX() + num, (int) p.getY()),
					direction.LEFT, num, canvas, universe);
		}
	}

}
