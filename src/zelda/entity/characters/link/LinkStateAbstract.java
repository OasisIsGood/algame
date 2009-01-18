package zelda.entity.characters.link;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import zelda.entity.DrawableImageSprite;

public abstract class LinkStateAbstract implements LinkState {

	protected DrawableImageSprite image = null;
	protected int spriteNumber = 0;
	protected int spriteType = 0;
	protected Link link = null;
	protected boolean movable = true;
	protected Point position = null;
	
	public LinkStateAbstract(Link link){
		if(link != null){
			this.link = link;
		}
		position = link.getPosition();
	}
	
	public void setLink(Link link) {
		this.link = link;
	}
	
	public abstract void draw(Graphics g, Point pos);
	public abstract Rectangle getBoundingBox();
	public abstract void oneStepMoveHandler();
	public abstract int getStrengh();
	public abstract int getSpriteSize();
}
