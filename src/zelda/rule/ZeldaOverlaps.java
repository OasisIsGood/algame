package zelda.rule;

import gameframework.base.IntegerObservable;
import gameframework.base.Overlap;
import gameframework.base.Overlappable;
import gameframework.game.GameUniverse;
import gameframework.game.OverlapRuleApplierDefaultImpl;

import java.awt.Canvas;
import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Method;
import java.util.Vector;

import zelda.base.Sound;
import zelda.entity.characters.AbstractLink;
import zelda.entity.characters.Boss;
import zelda.entity.characters.Guard;
import zelda.entity.characters.Link;
import zelda.entity.characters.SwordedLink;
import zelda.entity.characters.ZeldaPrincess;
import zelda.entity.decors.Bomb;
import zelda.entity.decors.Bush;
import zelda.entity.decors.SuperPotion;
import zelda.entity.decors.Sword;
import zelda.game.GameZeldaAWTImpl;
import zelda.game.GameZeldaImpl;
import zelda.observer.EnnemyObserver;

public class ZeldaOverlaps extends OverlapRuleApplierDefaultImpl {

	protected GameUniverse universe;

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

	public void overlapRule(AbstractLink link, Bush bush) {
		if (link.isSwording()) {
			score.setValue(score.getValue() + 5);
			universe.removeGameEntity(bush);
			universe.addGameEntity(new Bomb(canvas, bush.getPosition()));
		} else {
			life.setValue(life.getValue() - 1);
		}
	}

	public void overlapRule(AbstractLink link, Guard guard) {
		guard.swording(true);
		if (link.isSwording()) {
			EnnemyObserver.getInstance().setValue(
					EnnemyObserver.getInstance().getValue() - 1);
			score.setValue(score.getValue() + 5);
			universe.removeGameEntity(guard);
		} else {
			life.setValue(life.getValue() - 5);
		}
	}

	public void overlapRule(AbstractLink link, SuperPotion superPotion) {
		life.setValue(GameZeldaImpl.NUMBER_OF_LIVES);
		universe.removeGameEntity(superPotion);
		try {
			Sound sound = new Sound(new File("sounds/explosion.wav"));
			sound.play();
		} catch (FileNotFoundException e) {
			System.out.println("Sound file not found");
		}
	}

	public void overlapRule(AbstractLink link, ZeldaPrincess zelda) {
		if (EnnemyObserver.getInstance().getValue() <= 0) {
			score.setValue(score.getValue() + 100);
			win.setValue(GameZeldaAWTImpl.result.WIN.ordinal());
		}
	}

	public void overlapRule(AbstractLink link, Sword sword) {
		link = new SwordedLink(canvas);
		universe.removeGameEntity(sword);
	}

	public void overlapRule(AbstractLink link, Boss boss) {
		boss.swording(true);
		if (link.isSwording()) {
			boss.isAttacked(link.strengh());
			if (boss.isDead()) {
				EnnemyObserver.getInstance().setValue(
						EnnemyObserver.getInstance().getValue() - 1);
				score.setValue(score.getValue() + 40);
				universe.removeGameEntity(boss);
			}
		} else {
			life.setValue(life.getValue() - 5);
		}
	}
	
	/*public void overlapRule(Link link, Boss boss) {
		overlapRule((AbstractLink)link, boss);
	}
	
	public void overlapRule(SwordedLink link, Boss boss) {
		overlapRule((AbstractLink)link, boss);
	}*/
	
	@Override
	public void applyOverlapRules(Vector<Overlap> overlaps) {
		for (Overlap col : overlaps) {
			try {
				applySpecificOverlapRule(col.getOverlappable1(), col.getOverlappable2());
			} catch (Exception e) {
			}
		}
	}

	private void applySpecificOverlapRule(Overlappable e1, Overlappable e2) throws Exception {
		Object[] param = new Object[2];
		Class<?>[] paramClass = new Class[2];
		Class<?> receiverClass = this.getClass();
		param[0] = e1;
		if(e1.getClass().equals(Link.class) || e1.getClass().equals(SwordedLink.class))
			paramClass[0] = e1.getClass().getSuperclass();
		else
			paramClass[0] = e1.getClass();
		param[1] = e2;
		paramClass[1] = e2.getClass();
		Method m = null;
		try {
			m = receiverClass.getMethod("overlapRule", paramClass);
			m.invoke(this, param);
		} catch (Exception e) {
			Class<?> tmpclass = paramClass[0];
			Object tmpobject = param[0];
			paramClass[0] = paramClass[1];
			paramClass[1] = tmpclass;
			param[0] = paramClass[1];
			param[1] = tmpobject;
			m = receiverClass.getMethod("overlapRule", paramClass);
			m.invoke(this, param);
		}
	}
}
