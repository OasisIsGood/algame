package zelda.entity.characters;

import java.awt.Canvas;

import zelda.entity.DrawableImageSprite;

public class SwordedLink extends AbstractLink {
	public SwordedLink(Canvas defaultCanvas) {
		super(defaultCanvas);
		
		if (imageRight == null) 
			imageRight = new DrawableImageSprite("images/characters/linkGetSwordRight.gif", defaultCanvas, 35, 30, 5);
		if (imageLeft == null) 
			imageLeft = new DrawableImageSprite("images/characters/linkGetSwordLeft.gif", defaultCanvas, 35, 30, 5);
		if (imageFace == null) 
			imageFace = new DrawableImageSprite("images/characters/linkGetSwordFace.gif", defaultCanvas, 20, 35, 5);
		if (imageBack == null) 
			imageBack = new DrawableImageSprite("images/characters/linkGetSwordBack.gif", defaultCanvas, 20, 30, 5);
		/*if (imageSwording == null) 
			imageSwording = new DrawableImageSprite("images/characters/linkSwording.gif", defaultCanvas, 35, 35, 8);*/
	}
}
