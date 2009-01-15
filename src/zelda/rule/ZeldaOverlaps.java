package zelda.rule;

import gameframework.base.IntegerObservable;
import gameframework.game.GameUniverse;
import gameframework.game.OverlapRuleApplierDefaultImpl;

import java.awt.Canvas;
import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;

import zelda.base.Sound;
import zelda.entity.characters.Boss;
import zelda.entity.characters.Guard;
import zelda.entity.characters.Link;
import zelda.entity.characters.ZeldaPrincess;
import zelda.entity.decors.Bomb;
import zelda.entity.decors.Bush;
import zelda.entity.decors.SuperPotion;
import zelda.entity.decors.Sword;
import zelda.game.GameZeldaAWTImpl;
import zelda.game.GameZeldaImpl;

public class ZeldaOverlaps extends OverlapRuleApplierDefaultImpl {

	protected GameUniverse universe;
	//protected Vector<Ennemy> vEnnemies = new Vector<Ennemy>();

	protected Point linkStartPos;
	protected Point ennemyStartPos;
	protected boolean manageLinkDeath;
	private IntegerObservable score;
	private IntegerObservable life;
	private IntegerObservable win;
	private Canvas canvas;

	public ZeldaOverlaps(Point linkPos, Point ePos, IntegerObservable life,
			IntegerObservable score, IntegerObservable win, Canvas canvas) {
		linkStartPos = (Point) linkPos.clone();
		ennemyStartPos = (Point) ePos.clone();
		this.life = life;
		this.score = score;
		this.win = win;
		this.canvas = canvas;
	}

	@Override
	public void setUniverse(GameUniverse universe) {
		this.universe = universe;
	}

	public void overlapRule(Link link, Bush bush) {
		if (link.isSwording()) {
			score.setValue(score.getValue() + 5);
			universe.removeGameEntity(bush);
			universe.addGameEntity(new Bomb(canvas, bush.getPosition()));
		} else {
			life.setValue(life.getValue() - 1);
		}
	}

	public void overlapRule(Link link, Guard guard) {
		guard.swording(true);
		if (link.isSwording()) {
			score.setValue(score.getValue() + 5);
			universe.removeGameEntity(guard);
		} else {
			life.setValue(life.getValue() - 5);
		}
	}

	public void overlapRule(Link link, SuperPotion superPotion) {
		life.setValue(GameZeldaImpl.NUMBER_OF_LIVES);
		universe.removeGameEntity(superPotion);
		try {
			Sound sound = new Sound(new File("sounds/explosion.wav"));
			sound.play();
		} catch (FileNotFoundException e) {
			System.out.println("Sound file not found");
		}
	}

	public void overlapRule(Link link, ZeldaPrincess zelda) {
		score.setValue(score.getValue() + 100);
		win.setValue(GameZeldaAWTImpl.result.WIN.ordinal());
	}
	
	public void overlapRule(Link link, Sword sword) {
		link.takingSword(true);
		universe.removeGameEntity(sword);
	}
	
	public void overlapRule(Link link, Boss boss) {
		boss.swording(true);
		System.out.println("Bouh");
		if (link.isSwording()) {
			if(link.isTakingSword())
				boss.isAttacked(Link.SWORD_STRENGH);
			else
				boss.isAttacked(Link.HAND_STRENGH);
			if(boss.isDead()) {
				score.setValue(score.getValue() + 40);
				universe.removeGameEntity(boss);
			}
		} else {
			life.setValue(life.getValue() - 5);
		}
	}
}
