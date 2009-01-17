package zelda.entity.characters.link;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import zelda.entity.DrawableImageSprite;

public class LinkStateNotArmed extends LinkStateAbstract {
	
	private int STRENGH = 0;
	private int SpriteSize = 25;

	public LinkStateNotArmed(Canvas canvas, Link link) {
		super(link);
		if(image == null){ 
			image = new DrawableImageSprite("images/characters/LINK.gif", canvas, 25, 25, 8);
		}
		spriteType = 0;
		spriteNumber = 0;
	}

	@Override
	public void draw(Graphics g, Point pos) {
		position = pos;
		Point tmp = link.getLinkSpeedVector().getDir();
		movable = true;
		
		if (tmp.getX() == 1) {			// "droite"
			spriteType = 1;
		} else if (tmp.getX() == -1) { 	// "gauche"
			spriteType = 2;
		} else if (tmp.getY() == -1) { 	// "haut");
			spriteType = 3;
		} else if (tmp.getY() == 1) { 	// "bas");
			spriteType = 0;
		} else {  						// "de Face");
			spriteType = 0;
			spriteNumber = 0;
			movable = false;
		}
		if(link.isTimerRunning())
			spriteType += 6;
		
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
				SpriteSize, SpriteSize ));
	}

	@Override
	public void oneStepMoveHandler() {
		if (movable || link.isTimerRunning()) {
			spriteNumber++;
			spriteNumber = spriteNumber % image.getNumberOfSprites();
		}
	}
	
	@Override
	public int getStrengh() {
		return STRENGH;
	}

	@Override
	public int getSpriteSize() {
		return SpriteSize;
	}
}
