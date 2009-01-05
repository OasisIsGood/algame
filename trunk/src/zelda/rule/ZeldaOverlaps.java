package zelda.rule;

import gameframework.base.IntegerObservable;
import gameframework.game.GameUniverse;
import gameframework.game.OverlapRuleApplierDefaultImpl;

import java.awt.Point;
import java.util.Vector;

import zelda.entity.characters.Ennemy;
import zelda.entity.characters.Link;
import zelda.entity.decors.Buisson;

public class ZeldaOverlaps extends OverlapRuleApplierDefaultImpl {

	protected GameUniverse universe;
	protected Vector<Ennemy> vEnnemies = new Vector<Ennemy>();

	protected Point linkStartPos;
	protected Point ennemyStartPos;
	protected boolean manageLinkDeath;
	private IntegerObservable score;
	private IntegerObservable life;
	
	public ZeldaOverlaps(Point linkPos, Point ePos,
			IntegerObservable life, IntegerObservable score) {
		linkStartPos = (Point) linkPos.clone();
		ennemyStartPos = (Point) ePos.clone();
		this.life = life;
		this.score = score;
	}
	
	@Override
	public void setUniverse(GameUniverse universe) {
		this.universe = universe;
	}

	//TODO Définir les surperpositions, les mêmes que pacman avec des différences
	
	public void overlapRule(Link link, Buisson buisson) {
		score.setValue(score.getValue() + 5);
		life.setValue(life.getValue() - 1);
		
		// pb on peut pas positionner
		//universe.removeGameEntity();
		//universe.addGameEntity();
		
	}
}
