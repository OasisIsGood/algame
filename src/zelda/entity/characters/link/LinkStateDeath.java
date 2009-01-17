package zelda.entity.characters.link;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import zelda.entity.DrawableImageSprite;

public class LinkStateDeath extends LinkStateAbstract {

	public LinkStateDeath(Canvas canvas, Link link) {
		super(link);
		if(image == null){ 
			image = new DrawableImageSprite("images/characters/LINK.gif", canvas, 25, 25, 8);
		}
		spriteType = 4;
		spriteNumber = 0;
	}

	@Override
	public void draw(Graphics g, Point pos) {
		position = pos;
		g.drawImage(image.getImage(), pos.x, pos.y, 
				pos.x + Link.getSpriteSize(), 
				pos.y + Link.getSpriteSize(),
				spriteNumber * image.getPixelsLenght(), 
				spriteType * image.getPixelsHeight(), 
				(spriteNumber + 1) * image.getPixelsLenght(), 
				(spriteType + 1) * image.getPixelsHeight(), null);
	}

	@Override
	public Rectangle getBoundingBox() {
		return (new Rectangle(position.x, position.y, 
				Link.getSpriteSize(), Link.getSpriteSize()));
	}

	@Override
	public void oneStepMoveHandler() {
		if (movable /*|| (timer.isRunning() && isSwording)*/) {
			spriteNumber++;
			spriteNumber = spriteNumber % image.getNumberOfSprites();
			if(spriteNumber >= image.getNumberOfSprites() - 1)
				movable = false; // ça y est il est vraiment mort là, il ne bouge plus
		}
	}
}
