package zelda.entity.decors;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import gameframework.base.Drawable;
import gameframework.base.DrawableImage;
import gameframework.game.GameEntity;
import gameframework.game.MoveBlocker;

public class Tree implements Drawable, MoveBlocker, GameEntity {

	protected static DrawableImage image = null;
	protected Point position;
	public static final int SPRITE_SIZE_X = 16;
	public static final int SPRITE_SIZE_Y = 16;

	public Tree(Canvas defaultCanvas, Point pos) {
		if (image == null) {
			image = new DrawableImage("images/decor/wall.gif", defaultCanvas);
		}
		position = pos;
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(image.getImage(), (int) getPosition().getX(),
				(int) getPosition().getY(), SPRITE_SIZE_X, SPRITE_SIZE_Y, null);
	}

	@Override
	public Rectangle getBoundingBox() {
		return (new Rectangle((int) position.getX(), (int) position.getY(),
				SPRITE_SIZE_X, SPRITE_SIZE_Y));
	}
	
	public Point getPosition() {
		return position;
	}
	
	public int getSpriteSizeX() {
		return SPRITE_SIZE_X;
	}
	
	public int getSpriteSizeY() {
		return SPRITE_SIZE_Y;
	}
}
