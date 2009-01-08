package zelda.entity;

import gameframework.base.MoveStrategyKeyboard;
import gameframework.game.GameMovableDriverDefaultImpl;
import gameframework.game.GameUniverse;
import gameframework.game.MoveBlockerChecker;
import gameframework.game.MoveBlockerCheckerDefaultImpl;

import java.awt.Canvas;
import java.awt.Point;

import zelda.base.LinkMoveStrategy;
import zelda.entity.characters.Link;
import zelda.rule.ZeldaMoveBlockers;

public class EntityFactory {
	//TODO Créer les fonctions de creation des entités
	public static void createLink(Point p,	Canvas canvas, GameUniverse universe){
		
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
}
