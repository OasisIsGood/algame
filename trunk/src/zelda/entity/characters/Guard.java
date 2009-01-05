package zelda.entity.characters;

import gameframework.base.Overlappable;
import gameframework.base.Drawable;
import gameframework.base.DrawableImage;
import gameframework.game.GameEntity;
import gameframework.game.GameMovable;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

public class Guard extends GameMovable implements Drawable, GameEntity,
		Overlappable {
	protected static DrawableImage image = null;
	protected boolean active = true;
	private int spriteType = 0;
	private int spriteNumber = 0;
	private boolean movable;
	public static final int SPRITE_SIZE = 16;

	public void setAlive(boolean aliveState) {
		active  = aliveState;
	}

	public Guard(Canvas defaultCanvas) {
		if (image == null) {
			image = new DrawableImage("images/decor/z3_guard.gif", defaultCanvas);
		}
	}

	public void draw(Graphics g) {
		Point tmp = getSpeedVector().getDir();

/*		if (tmp.getX() == -1) {
			spriteType += 1;
		} else if (tmp.getY() == 1) {
			spriteType += 3;
		} else if (tmp.getY() == -1) {
			spriteType += 2;
		}
*/
		g.drawImage(image.getImage(), 
				(int) getPosition().getX(), 
				(int) getPosition().getY(), 
				(int) getPosition().getX() + SPRITE_SIZE,
				(int) getPosition().getY() + SPRITE_SIZE, 
				spriteNumber * 32, spriteType * 32, 
				(spriteNumber + 1) * 32, (spriteType + 1) * 32, 
				null);
	}

	@Override
	public void oneStepMoveHandler() {
		if (movable) {
			//spriteNumber++;
			//spriteNumber = spriteNumber % 6;
		}
	}

	public Rectangle getBoundingBox() {
		return (new Rectangle(0, 0, SPRITE_SIZE, SPRITE_SIZE));
	}
}
