package zelda.entity;

import gameframework.base.MoveStrategyKeyboard;
import gameframework.base.MoveStrategyRandom;
import gameframework.game.GameMovableDriverDefaultImpl;

import java.awt.Canvas;
import java.awt.Point;

import zelda.base.GuardMovableDriver;
import zelda.base.LinkMoveStrategy;
import zelda.entity.characters.Boss;
import zelda.entity.characters.Guard;
import zelda.entity.characters.ZeldaPrincess;
import zelda.entity.characters.link.Link;
import zelda.entity.decors.Bomb;
import zelda.entity.decors.Bush;
import zelda.entity.decors.Hammer;
import zelda.entity.decors.SuperPotion;
import zelda.entity.decors.Sword;
import zelda.entity.decors.Tree;
import zelda.game.GameZeldaUniverse;
import zelda.level.ZeldaGameLevel.direction;
import zelda.observer.EnnemyObserver;

/*
 * Class where we can create all the entity objects
 * Please respect the alphabetical order thxs !
 */
public class EntityFactory {

	private static final int SPRITE_SIZE = 20;

	public static void createBomb(Point p, Canvas canvas,
			GameZeldaUniverse universe) {
		universe.addGameEntity(new Bomb(canvas, new Point(p.x
				* SPRITE_SIZE, p.y * SPRITE_SIZE)));
	}

	public static void createBoss(Point p, Canvas canvas,
			GameZeldaUniverse universe) {
		universe.addGameEntity(new Boss(canvas, new Point(p.x
				* SPRITE_SIZE, p.y * SPRITE_SIZE)));
		EnnemyObserver.getInstance().setValue(EnnemyObserver.getInstance().getValue() + 1);
	}
	
	public static void createBush(Point p, Canvas canvas,
			GameZeldaUniverse universe) {
		universe.addGameEntity(new Bush(canvas, new Point(p.x
				* SPRITE_SIZE, p.y * SPRITE_SIZE)));
	}
	
	public static void createGuard(Point p, Canvas canvas,
			GameZeldaUniverse universe) {
		Guard guard = new Guard(canvas, new Point(p.x * SPRITE_SIZE, p.y
				* SPRITE_SIZE));
		GameMovableDriverDefaultImpl guardDriv = new GuardMovableDriver();
		MoveStrategyRandom ranStr = new MoveStrategyRandom();
		guardDriv.setStrategy(ranStr);
		guardDriv.setmoveBlockerChecker(universe.getMoveBlockerChecker());
		guard.setDriver(guardDriv);
		universe.addGameEntity(guard);
		EnnemyObserver.getInstance().setValue(EnnemyObserver.getInstance().getValue() + 1);
	}
	
	public static void createHammer(Point p, Canvas canvas,
			GameZeldaUniverse universe) {
		universe.addGameEntity(new Hammer(canvas, new Point(p.x
				* SPRITE_SIZE, p.y * SPRITE_SIZE)));
	}

	public static void createLink(Point p, Canvas canvas,
			GameZeldaUniverse universe) {

		Link link = new Link(canvas);
		link.setState("LinkStateNotArmed");
		GameMovableDriverDefaultImpl pacDriver = new GameMovableDriverDefaultImpl();
		MoveStrategyKeyboard keyStr = new LinkMoveStrategy(link);
		pacDriver.setStrategy(keyStr);
		pacDriver.setmoveBlockerChecker(universe.getMoveBlockerChecker());
		canvas.addKeyListener(keyStr);
		link.setDriver(pacDriver);
		link.setPosition(new Point(p.x * SPRITE_SIZE, p.y
				* SPRITE_SIZE));
		universe.addGameEntity(link);
	}
	
	public static void createPotion(Point p, Canvas canvas,
			GameZeldaUniverse universe) {
	}
		
	public static void createSuperPotion(Point p, Canvas canvas,
			GameZeldaUniverse universe) {
		universe.addGameEntity(new SuperPotion(canvas, new Point(p.x
				* SPRITE_SIZE, p.y * SPRITE_SIZE)));
	}
	
	public static void createSword(Point p, Canvas canvas,
			GameZeldaUniverse universe) {
		universe.addGameEntity(new Sword(canvas, new Point(p.x
				* SPRITE_SIZE, p.y * SPRITE_SIZE)));
	}
	
	public static void createTree(Point p, Canvas canvas,
			GameZeldaUniverse universe) {
	}

	public static void createWall(Point p, direction dir, int num,
			Canvas canvas, GameZeldaUniverse universe) {
		if (dir.equals(direction.UP)) {
			for (int i = num; i > 0; --i) {
				universe.addGameEntity(new Tree(canvas, new Point((int) p
						.getX()
						* SPRITE_SIZE,
						(int) ((p.getY() - i) * SPRITE_SIZE))));
			}
		}
		if (dir.equals(direction.DOWN)) {
			createWall(new Point((int) p.getX(), (int) p.getY() + num),
					direction.UP, num, canvas, universe);
		}
		if (dir.equals(direction.LEFT)) {
			for (int i = 0; i < num; ++i) {
				universe.addGameEntity(new Tree(canvas,
						new Point((int) (p.getX() - i) * SPRITE_SIZE,
								(int) (p.getY() * SPRITE_SIZE))));
			}
		}
		if (dir.equals(direction.RIGHT)) {
			createWall(new Point((int) p.getX() + num, (int) p.getY()),
					direction.LEFT, num, canvas, universe);
		}
	}
	
	public static void createZelda(Point p, Canvas canvas,
			GameZeldaUniverse universe) {
		universe.addGameEntity(new ZeldaPrincess(canvas, new Point(p.x
				* SPRITE_SIZE, p.y * SPRITE_SIZE)));
	}
}
