package zelda.entity.characters.link;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Point;

import zelda.entity.DrawableImageSprite;

public class LinkStateNotArmed extends LinkStateAbstract {

	public LinkStateNotArmed(Canvas canvas, Link link) {
		super(link);
		if(image == null){ 
			image = new DrawableImageSprite("images/characters/LINK.gif", canvas, 25, 25, 8);
		}
		spriteType = 0;
	}

	@Override
	public void draw(Graphics g, Point pos) {
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
			//spriteNumber = 0;
			movable = false;
		}
		if(link.isTimerRunning())
			spriteType += 6;
		
		super.draw(g,pos);
	}
}
