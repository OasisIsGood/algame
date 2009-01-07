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
	public static final int SPRITE_SIZE = 24;

	public Guard(Canvas defaultCanvas, Point pos) {
		if (image == null) {
			image = new DrawableImage("images/decor/z3_guard.gif", defaultCanvas);
		}
		setPosition(pos);
	}
	
	@Override
	public void draw(Graphics g) {
		g.drawImage(image.getImage(), (int) getPosition().getX(),
				(int) getPosition().getY(), SPRITE_SIZE, SPRITE_SIZE, null);
	}

	@Override
	public Rectangle getBoundingBox() {
		return (new Rectangle((int) getPosition().getX(), (int) getPosition().getY(),
				SPRITE_SIZE , SPRITE_SIZE));
	}

	@Override
	public void oneStepMoveHandler() {
		
	}
}
