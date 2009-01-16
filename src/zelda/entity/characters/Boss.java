package zelda.entity.characters;

import gameframework.base.Drawable;
import gameframework.base.Overlappable;
import gameframework.game.GameEntity;
import gameframework.game.GameMovable;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import zelda.entity.DrawableImageSprite;

public class Boss extends GameMovable implements Drawable, GameEntity,
		Overlappable {
	
	public static final int SPRITE_SIZE = 30;
	protected static DrawableImageSprite image = null;
	protected int spriteNumber = 0;
	protected int spriteType = 0;
	protected boolean movable = true;
	private boolean isSwording = false;
	private int life = 20;

	public Boss(Canvas defaultCanvas, Point pos) {
		if (image == null) {
				image = new DrawableImageSprite("images/characters/boss1.gif", defaultCanvas, 50, 50, 7);
		}
		spriteNumber = 0;
		spriteType = 0;
		setPosition(pos);
	}
	
	@Override
	public void draw(Graphics g) {
		g.drawImage(
				image.getImage(),
				(int) getPosition().getX(),
				(int) getPosition().getY(),
				(int) getPosition().getX() + SPRITE_SIZE,				
				(int) getPosition().getY() + SPRITE_SIZE,
				spriteNumber 		* image.getPixelsLenght(), 
				spriteType	 		* image.getPixelsHeight(), 
				(spriteNumber + 1) 	* image.getPixelsLenght(),
				(spriteType + 1) 	* image.getPixelsHeight(),
				null);
	}

	@Override
	public Rectangle getBoundingBox() {
		return (new Rectangle((int) getPosition().getX(), (int) getPosition().getY(),
				SPRITE_SIZE , SPRITE_SIZE));
	}

	@Override
	public void oneStepMoveHandler() {
		if (movable) {
			spriteNumber++;
			spriteNumber = spriteNumber % image.getNumberOfSprites();
		}
	}
	
	public boolean isSwording() {
		return isSwording;
	}
	
	public void swording(boolean b) {
		isSwording = b;
	}
	
	public int getLife() {
		return life;
	}
	
	public void isAttacked(int lostLife) {
		life  -= lostLife;
	}
	
	public boolean isDead() {
		return getLife() <= 0;
	}
}
