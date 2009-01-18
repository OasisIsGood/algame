package zelda.entity.characters.link;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import zelda.entity.DrawableImageSprite;

public class LinkStateDeath extends LinkStateAbstract {

	private int SpriteSize = 24;
	private int STRENGH = 0;
	
	public LinkStateDeath(Canvas canvas, Link link) {
		super(link);
		if(image == null){ 
			image = new DrawableImageSprite("images/characters/LINK.gif", canvas, 25, 25, 8);
		}
		spriteType = 11;
		position = link.getPosition();
	}

	@Override
	public void draw(Graphics g, Point pos) {
		position = pos;
		g.drawImage(image.getImage(), pos.x, pos.y, 
				pos.x + SpriteSize, 
				pos.y + SpriteSize,
				spriteNumber * image.getPixelsLenght(), 
				spriteType * image.getPixelsHeight(), 
				(spriteNumber + 1) * image.getPixelsLenght(), 
				(spriteType + 1) * image.getPixelsHeight(), null);
	}

	@Override
	public Rectangle getBoundingBox() {
		return (new Rectangle(position.x, position.y, 
				SpriteSize, SpriteSize));
	}

	@Override
	public void oneStepMoveHandler() {
		if (movable) {
			spriteNumber++;
			spriteNumber = spriteNumber % image.getNumberOfSprites();
			if(spriteNumber >= image.getNumberOfSprites() - 1)
				movable = false; // ça y est il est vraiment mort là, il ne bouge plus
		}
	}

	@Override
	public int getStrengh() {
		return STRENGH ;
	}

	@Override
	public int getSpriteSize() {
		return SpriteSize;
	}
}
