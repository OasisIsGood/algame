package zelda.entity.characters.link;

import gameframework.base.Drawable;
import gameframework.base.Overlappable;
import gameframework.base.SpeedVector;
import gameframework.game.GameEntity;
import gameframework.game.GameMovable;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.lang.reflect.Constructor;

public class Link extends GameMovable implements Drawable,
GameEntity, Overlappable {

	private Canvas canvas = null;
	private LinkState state  = null;
	private boolean isSwording = false;
	
	public Link(Canvas defaultCanvas) {
		canvas = defaultCanvas;
	}
	
	@Override
	public void draw(Graphics g) {
		state.draw(g, new Point((int) getPosition().getX(), (int) getPosition().getY()));
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
	
	public void setSwording(boolean isSwording) {
		this.isSwording = isSwording;
	}

	public boolean isSwording() {
		return isSwording;
	}

	public void setState(LinkState state, Link link){
		state.setLink(link);
		this.state = state;
	}
	
	public void setState(String stateString){
		try {
			Class<?> linkStateClass = Class.forName("zelda.entity.characters.link." + stateString);
			Constructor<?> make = linkStateClass.getConstructor(Canvas.class, Link.class);
			LinkState linkState = (LinkState) make.newInstance(canvas, this);
			this.state = linkState;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("(Class Link) : the " + stateString + " don't exist!");
		}
	}

	public LinkState getState(){
		return state;
	}
	
	public boolean isNotArmed(){
		return (state instanceof LinkStateNotArmed); 
	}
	
	public boolean isHaveSword(){
		return (state instanceof LinkStateHaveSword); 
	}

	public boolean isDeath(){
		return (state instanceof LinkStateDeath); 
	}
}
