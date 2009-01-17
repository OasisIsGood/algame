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

/*
 * The princess to save !
 */
public class ZeldaPrincess extends GameMovable implements Drawable, GameEntity, Overlappable {

	public static final int SPRITE_SIZE = 25;
	protected static DrawableImageSprite image = null;
	protected Point position;
	private boolean movable = true;
	private int spriteNumber;
	private int spriteType;

	public ZeldaPrincess(Canvas defaultCanvas, Point pos) {
		if (image == null) {
			image = new DrawableImageSprite("images/characters/ZELDA.gif", defaultCanvas, 25, 25, 6);
		}
		spriteNumber = 0;
		spriteType = 0;
		position = pos;
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
	public Point getPosition() {
		return position;
	}
	
	public DrawableImageSprite getImage() {
		return image;
	}

	@Override
	public Rectangle getBoundingBox() {
		return (new Rectangle((int) getPosition().getX(), (int) getPosition().getY(),
				SPRITE_SIZE , SPRITE_SIZE));
	}

	@Override
	public void oneStepMoveHandler() {
		if(movable) {
			spriteNumber++;
			spriteNumber = spriteNumber % image.getNumberOfSprites();
		}
	}
}
