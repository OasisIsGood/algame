package zelda.entity.decors;

import gameframework.base.Drawable;
import gameframework.base.DrawableImage;
import gameframework.game.GameEntity;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Point;

public class NextLevelImage implements Drawable, GameEntity {

	protected static DrawableImage image = null;
	public static final int SPRITE_SIZE = 40;
	private Point position;
	
	public NextLevelImage(Canvas defaultCanvas, Point pos) {
		if (image == null) {
			image = new DrawableImage("images/background/nextLevel.gif", defaultCanvas);
		}
		position = pos;
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(image.getImage(), (int) getPosition().getX(),
				(int) getPosition().getY(), SPRITE_SIZE, SPRITE_SIZE, null);
	}
	
	public Point getPosition() {
		return position;
	}
}
