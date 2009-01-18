package zelda.entity.characters.link;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import zelda.entity.DrawableImageSprite;

public abstract class LinkStateAbstract implements LinkState {

	protected Link link = null;
	protected Point position = null;
	protected DrawableImageSprite image = null;
	protected int spriteNumber = 0;
	protected int spriteType = 0;
	protected int strengh = 0;
	protected int spriteSize = 24;
	protected boolean movable = true;
	
	public LinkStateAbstract(Link link){
		if(link != null){
			this.link = link;
		}
		position = link.getPosition();
	}
	
	public void setLink(Link link) {
		this.link = link;
	}
	
	public int getStrengh() {
		return strengh;
	}
	
	public int getSpriteSize() {
		return spriteSize;
	}
	
	public void oneStepMoveHandler() {
		if (movable|| link.isTimerRunning()) {
			spriteNumber++;
			spriteNumber = spriteNumber % image.getNumberOfSprites();
		}
	}
	
	public Rectangle getBoundingBox() {
		return (new Rectangle(position.x, position.y, 
				spriteSize, spriteSize));
	}
	
	public void draw(Graphics g, Point pos) {
		position = pos;
		g.drawImage(image.getImage(), pos.x, pos.y, 
				pos.x + spriteSize, 
				pos.y + spriteSize,
				spriteNumber * image.getPixelsLenght(), 
				spriteType * image.getPixelsHeight(), 
				(spriteNumber + 1) * image.getPixelsLenght(), 
				(spriteType + 1) * image.getPixelsHeight(), null);
	}
}
