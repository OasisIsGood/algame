package zelda.entity;

import gameframework.base.MoveStrategyKeyboard;
import gameframework.base.MoveStrategyRandom;
import gameframework.game.GameMovableDriverDefaultImpl;

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
import zelda.game.GameZeldaUniverse;
import zelda.level.ZeldaGameLevel1.direction;

public class EntityFactory {
	
	public static void createLink(Point p, Canvas canvas, GameZeldaUniverse universe) {

		Link link = new Link(canvas);
		GameMovableDriverDefaultImpl pacDriver = new GameMovableDriverDefaultImpl();
		MoveStrategyKeyboard keyStr = new LinkMoveStrategy(link);
		pacDriver.setStrategy(keyStr);
		pacDriver.setmoveBlockerChecker(universe.getMoveBlockerChecker());
		canvas.addKeyListener(keyStr);
		link.setDriver(pacDriver);
		link.setPosition(new Point(p.x * Link.SPRITE_SIZE, p.y * Link.SPRITE_SIZE));
		universe.addGameEntity(link);
	}

	public static void createZelda(Point p, Canvas canvas, GameZeldaUniverse universe) {
		universe.addGameEntity(new ZeldaPrincess(canvas, new Point(p.x * ZeldaPrincess.SPRITE_SIZE, p.y * ZeldaPrincess.SPRITE_SIZE)));
	}

	public static void createPotion(Point p, Canvas canvas,
			GameZeldaUniverse universe) {

	}

	public static void createGuard(Point p, Canvas canvas, GameZeldaUniverse universe) {

		Guard guard = new Guard(canvas, new Point(p.x * Guard.SPRITE_SIZE, p.y * Guard.SPRITE_SIZE));
		GameMovableDriverDefaultImpl guardDriv = new GuardMovableDriver();
		MoveStrategyRandom ranStr = new MoveStrategyRandom();
		guardDriv.setStrategy(ranStr);
		guardDriv.setmoveBlockerChecker(universe.getMoveBlockerChecker());
		guard.setDriver(guardDriv);
		universe.addGameEntity(guard);
	}

	public static void createBomb(Point p, Canvas canvas, GameZeldaUniverse universe) {

	}

	public static void createBush(Point p, Canvas canvas, GameZeldaUniverse universe) {
		universe.addGameEntity(new Bush(canvas, new Point(p.x * Bush.SPRITE_SIZE, p.y * Bush.SPRITE_SIZE)));
	}

	public static void createSuperPotion(Point p, Canvas canvas,
			GameZeldaUniverse universe) {
		universe.addGameEntity(new SuperPotion(canvas, new Point(p.x * SuperPotion.SPRITE_SIZE, p.y * SuperPotion.SPRITE_SIZE)));
	}

	public static void createTree(Point p, Canvas canvas, GameZeldaUniverse universe) {

	}

	public static void createHammer(Point p, Canvas canvas,
			GameZeldaUniverse universe) {
		universe.addGameEntity(new Hammer(canvas, new Point(p.x * Hammer.SPRITE_SIZE, p.y * Hammer.SPRITE_SIZE)));
	}

	public static void createWall(Point p, direction dir, int num,
			Canvas canvas, GameZeldaUniverse universe) {
		if (dir.equals(direction.UP)) {
			for (int i = num; i > 0; --i) {
				universe.addGameEntity(new Tree(canvas, new Point((int) p
						.getX()
						* Tree.SPRITE_SIZE, (int) ((p.getY() - i) * Tree.SPRITE_SIZE))));
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
						* Tree.SPRITE_SIZE, (int) (p.getY() * Tree.SPRITE_SIZE))));
			}
		}
		if (dir.equals(direction.RIGHT)) {
			createWall(new Point((int) p.getX() + num, (int) p.getY()),
					direction.LEFT, num, canvas, universe);
		}
	}
}
