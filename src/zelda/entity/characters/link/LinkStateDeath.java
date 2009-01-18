package zelda.entity.characters.link;

import java.awt.Canvas;

import zelda.entity.DrawableImageSprite;

public class LinkStateDeath extends LinkStateAbstract {
	
	public LinkStateDeath(Canvas canvas, Link link) {
		super(link);
		if(image == null){ 
			image = new DrawableImageSprite("images/characters/LINK.gif", canvas, 25, 25, 8);
		}
		spriteSize = 24;
		spriteType = 11;
		strengh = 0;
	}

	@Override
	public void oneStepMoveHandler() {
		if(spriteNumber >= image.getNumberOfSprites() - 1)
			movable = false; // really dead now
	}
}
