package zelda.rule;

import gameframework.base.IntegerObservable;
import gameframework.game.GameUniverse;
import gameframework.game.OverlapRuleApplierDefaultImpl;

import java.awt.Canvas;
import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Vector;

import zelda.base.Sound;
import zelda.entity.characters.Ennemy;
import zelda.entity.characters.Guard;
import zelda.entity.characters.Link;
import zelda.entity.characters.ZeldaPrincess;
import zelda.entity.decors.Bush;
import zelda.entity.decors.SuperPotion;
import zelda.game.GameZeldaAWTImpl;
import zelda.game.GameZeldaImpl;

public class ZeldaOverlaps extends OverlapRuleApplierDefaultImpl {

	protected GameUniverse universe;
	protected Vector<Ennemy> vEnnemies = new Vector<Ennemy>();

	protected Point linkStartPos;
	protected Point ennemyStartPos;
	protected boolean manageLinkDeath;
	private IntegerObservable score;
	private IntegerObservable life;
	private IntegerObservable win;
	
	public ZeldaOverlaps(Point linkPos, Point ePos, IntegerObservable life, IntegerObservable score, IntegerObservable win, Canvas canvas) {
		linkStartPos = (Point) linkPos.clone();
		ennemyStartPos = (Point) ePos.clone();
		this.life = life;
		this.score = score;
		this.win = win;
	}
	
	@Override
	public void setUniverse(GameUniverse universe) {
		this.universe = universe;
	}

	//TODO Définir les surperpositions, les mêmes que pacman avec des différences
	
	public void overlapRule(Link link, Bush bush) {
		if(link.isSwording()) {
			score.setValue(score.getValue() + 5);
			universe.removeGameEntity(bush);
		} else {
			life.setValue(life.getValue() - 1);
		}
		//universe.addGameEntity(new Bomb(canvas, bush.getPosition()));
	}
	
	public void overlapRule(Link link, Guard guard) {
		if(link.isSwording()) {
			score.setValue(score.getValue() + 5);
			universe.removeGameEntity(guard);
		} else {
			life.setValue(life.getValue() - 1);
		}
	}
	
	public void overlapRule(Link link, SuperPotion superPotion) {
		life.setValue(GameZeldaImpl.NUMBER_OF_LIVES);
		universe.removeGameEntity(superPotion);
		try {
			Sound sound = new Sound(new File("sounds/explosion.wav"));
			sound.play();
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		}
	}
	
	public void overlapRule(Link link, ZeldaPrincess zelda) {
		win.setValue(GameZeldaAWTImpl.result.WIN.ordinal());
	}
}
