package zelda.entity.characters.link;

import gameframework.base.Drawable;
import gameframework.base.Overlappable;
import gameframework.base.SpeedVector;
import gameframework.game.GameEntity;
import gameframework.game.GameMovable;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Constructor;

import javax.swing.Timer;

public class Link extends GameMovable implements Drawable, GameEntity,
		Overlappable {

	private Canvas canvas = null;
	private LinkState state = null;
	private boolean isFighting = false;
	private Timer timer;

	public Link(Canvas defaultCanvas) {
		canvas = defaultCanvas;
		timer = createTimer();
	}

	@Override
	public void draw(Graphics g) {
		state.draw(g, getPosition());
	}

	@Override
	public Rectangle getBoundingBox() {
		return state.getBoundingBox();
	}

	@Override
	public void oneStepMoveHandler() {
		state.oneStepMoveHandler();
	}

	public int getSpriteSize() {
		return state.getSpriteSize();
	}

	public int getStrengh() {
		return state.getStrengh();
	}

	public SpeedVector getLinkSpeedVector() {
		return getSpeedVector();
	}

	public void setFighting(boolean isFighting) {
		this.isFighting = isFighting;
		if (isFighting)
			timer.start();
	}

	public boolean isFighting() {
		return isFighting;
	}

	private Timer createTimer() {
		ActionListener action = new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				isFighting = false;
				timer.stop();
			}
		};
		return new Timer(300, action);
	}
	
	public boolean isTimerRunning() {
		return timer.isRunning();
	}

	public void setState(LinkState state) {
		state.setLink(this);
		this.state = state;
	}

	public void setState(String stateString) {
		try {
			Class<?> linkStateClass = Class
					.forName("zelda.entity.characters.link." + stateString);
			Constructor<?> make = linkStateClass.getConstructor(Canvas.class,
					Link.class);
			LinkState linkState = (LinkState) make.newInstance(canvas, this);
			this.state = linkState;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("(Class Link) : the " + stateString
					+ " don't exist!");
		}
	}

	public LinkState getState() {
		return state;
	}

	public boolean isNotArmed() {
		return (state instanceof LinkStateNotArmed);
	}

	public boolean isHaveSword() {
		return (state instanceof LinkStateHaveSword);
	}

	public boolean isDeath() {
		return (state instanceof LinkStateDeath);
	}
	
	public boolean isSingingOcarina() {
		return (state instanceof LinkStateSingingOcarina);
	}
}
