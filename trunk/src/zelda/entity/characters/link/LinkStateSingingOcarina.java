package zelda.entity.characters.link;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import zelda.entity.DrawableImageSprite;

public class LinkStateSingingOcarina extends LinkStateAbstract {

	private int SpriteSize = 24;
	private int STRENGH = 0;
	
	public LinkStateSingingOcarina(Canvas canvas, Link link) {
		super(link);
		if(image == null){ 
			image = new DrawableImageSprite("images/characters/LINK.gif", canvas, 25, 25, 8);
		}
		spriteType = 10;
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
		if(position == null)
			System.out.println("POSITION NULLLLLLLLLLLLLLLLLLLLL SINGING");
		return (new Rectangle(position.x, position.y, 
				SpriteSize, SpriteSize));
	}

	@Override
	public void oneStepMoveHandler() {
		if (movable) {
			spriteNumber++;
			spriteNumber = spriteNumber % image.getNumberOfSprites();
			if(spriteNumber >= image.getNumberOfSprites() - 1)
				movable = false; // ça y est il a fini de chanter là, il ne bouge plus
			//link.setState("LinkStateNotArmed");
			// TODO GROSSE EXCEPTION LA !!!
			//link.setState(LinkStateNotArmed.class.getName()); TODO a essayer dans ce style
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
