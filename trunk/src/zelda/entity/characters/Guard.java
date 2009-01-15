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

public class Guard extends GameMovable implements Drawable, GameEntity,
		Overlappable {
	
	protected static DrawableImageSprite image = null;
	public static final int SPRITE_SIZE = 24;
	protected int spriteNumber = 0;
	protected int spriteType = 0;
	protected boolean movable = true;
	private boolean isSwording = false;

	public Guard(Canvas defaultCanvas, Point pos) {
		if (image == null) {
				image = new DrawableImageSprite("images/characters/UnGuard.gif", defaultCanvas, 30, 35, 6);
		}
		spriteNumber = 0;
		spriteType = 0;
		setPosition(pos);
	}
	
	@Override
	public void draw(Graphics g) {
		Point tmp = getSpeedVector().getDir();
		movable = true;
	
		if (tmp.getX() == 1) {			// "droite"
			spriteType = 1;
			//spriteNumber = 0;
		} else if (tmp.getX() == -1) { 	// "gauche"
			spriteType = 2;
			//spriteNumber = image.getNumberOfSprites()-1;
		} else if (tmp.getY() == -1) { 	// "haut");
			spriteType = 3;
			//spriteNumber = 0;
		} else if (tmp.getY() == 1) { 	// "bas");
			spriteType = 0;
			//spriteNumber = 0;
		} else {  						// "de Face");
			spriteType = 0;
			spriteNumber = 0;
			movable = false;
		}
		
		g.drawImage(
				image.getImage(),
				(int) getPosition().getX(),
				(int) getPosition().getY(),
				(int) getPosition().getX() + image.getSpriteSizeX(),				
				(int) getPosition().getY() + image.getSpriteSizeY(),
				spriteNumber 		* image.getSpriteSizeX(), 
				spriteType	 		* image.getSpriteSizeY(), 
				(spriteNumber + 1) 	* image.getSpriteSizeX(),
				(spriteType + 1) 	* image.getSpriteSizeY(), 
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
			if(isSwording ) {
				spriteNumber++;
				spriteNumber = spriteNumber % image.getNumberOfSprites();
				if (spriteNumber >= image.getNumberOfSprites() - 1) {
					//isSwording = false;
				}
			}
		}
	}
	
	public boolean isSwording() {
		return isSwording;
	}
	
	public void swording(boolean b) {
		isSwording = b;
	}
}
