package zelda.entity.decors;

import gameframework.base.Drawable;
import gameframework.base.Overlappable;
import gameframework.game.GameEntity;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import zelda.entity.DrawableImageSprite;

public class Sword implements Drawable, GameEntity, Overlappable {

	protected static DrawableImageSprite image = null;
	protected Point position;
	public static final int SPRITE_SIZE = 18;
	
	public Sword(Canvas defaultCanvas, Point pos) {
		if (image == null) {
			image = new DrawableImageSprite("images/decor/sword.gif", defaultCanvas, 90, 25, 1);
		}
		position = pos;
	}
	
	@Override
	public void draw(Graphics g) {
		g.drawImage(image.getImage(), (int) getPosition().getX(),
				(int) getPosition().getY(), SPRITE_SIZE, SPRITE_SIZE, null);
	}

	@Override
	public Point getPosition() {
		return position;
	}

	@Override
	public Rectangle getBoundingBox() {
		return (new Rectangle((int) position.getX(), (int) position.getY(),
				SPRITE_SIZE , SPRITE_SIZE));
	}

}
