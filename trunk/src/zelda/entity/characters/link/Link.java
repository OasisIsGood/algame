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
	
	private static int SPRITE_SIZE = 24;
	private int STRENGH = 2;
	private Canvas canvas = null;
	private LinkState state  = null;
	
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
	
	public static int getSpriteSize() {
		return SPRITE_SIZE;
	}

	public int getStrengh() {
		return STRENGH;
	}
	
	public SpeedVector getLinkSpeedVector() {
		return getSpeedVector();
	}

	public void setState(LinkState state, Link link){
		state.setLink(link);
		this.state = state;
	}
	
	public void setState(String stateString){
		try {
			Class<?> linkStateClass = Class.forName("linkStates." + stateString);
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
	
	public boolean isTakingSword(){
		return (state instanceof LinkStateHaveSword); 
	}

	public boolean isDeath(){
		return (state instanceof LinkStateDeath); 
	}
}