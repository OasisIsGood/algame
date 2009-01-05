package zelda.entity.characters;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import gameframework.base.Drawable;
import gameframework.base.DrawableImage;
import gameframework.base.Overlappable;
import gameframework.game.GameEntity;
import gameframework.game.GameMovable;

public class Link extends GameMovable implements Drawable, GameEntity, Overlappable {

	public static final int SPRITE_SIZE = 16;
	protected static DrawableImage image = null;
	protected Canvas defaultCanvas;
	protected int spriteNumber = 0;
	protected int spriteType = 0;
	protected boolean movable = true;
	
	public Link(Canvas defaultCanvas) {
		if (image == null) {
			image = new DrawableImage("images/characters/pac1.gif", defaultCanvas);
		}
	}
	
	@Override
	public void draw(Graphics g) {
		Point tmp = getSpeedVector().getDir();
		movable = true;
		if (tmp.getX() == 1) {
			spriteType = 4;
		} else if (tmp.getX() == -1) {
			spriteType = 5;
		} else if (tmp.getY() == 1) {
			spriteType = 7;
		} else if (tmp.getY() == -1) {
			spriteType = 6;
		} else {
			spriteType = 9;
			spriteNumber = 0;
			movable = false;
		}
		
		g.drawImage(image.getImage(), (int) getPosition().getX(),
				(int) getPosition().getY(), (int) getPosition().getX()
						+ SPRITE_SIZE,
				(int) getPosition().getY() + SPRITE_SIZE, spriteNumber * 32,
				spriteType * 32, (spriteNumber + 1) * 32,
				(spriteType + 1) * 32, null);
	}

	@Override
	public Rectangle getBoundingBox() {
		return (new Rectangle(0, 0, SPRITE_SIZE, SPRITE_SIZE));
	}

	@Override
	public void oneStepMoveHandler() {
		if (movable) {
			spriteNumber++;
			spriteNumber = spriteNumber % 6;
		}
	}

}
