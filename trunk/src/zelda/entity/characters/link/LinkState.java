package zelda.entity.characters.link;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

public interface LinkState {
	public void setLink(Link link);
	public void draw(Graphics g, Point pos);
	public void oneStepMoveHandler();
	public Rectangle getBoundingBox();
	public int getStrengh();
	public int getSpriteSize();
}
