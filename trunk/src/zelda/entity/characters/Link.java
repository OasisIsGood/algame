package zelda.entity.characters;

import java.awt.Canvas;

import zelda.entity.DrawableImageSprite;

public class Link extends AbstractLink {
	
	//private boolean takeSword = false;
	
	public static int STRENGH = 2;
	
	public Link(Canvas defaultCanvas) {
		super(defaultCanvas);
		if (image == null)
			image	  = new DrawableImageSprite("images/characters/linkFace.gif", defaultCanvas, 25, 25, 7);
		if (imageFace == null) 
			imageFace = new DrawableImageSprite("images/characters/linkFace.gif", defaultCanvas, 25, 25, 7);
		if (imageRight == null) 
			imageRight = new DrawableImageSprite("images/characters/linkRight.gif", defaultCanvas, 25, 25, 5);
		if (imageLeft == null) 
			imageLeft = new DrawableImageSprite("images/characters/linkLeft.gif", defaultCanvas, 25, 25, 5);
		if (imageBack == null) 
			imageBack = new DrawableImageSprite("images/characters/linkBack.gif", defaultCanvas, 20, 25, 10);
		/*if (imageGetSwordRight == null) 
			imageGetSwordRight = new DrawableImageSprite("images/characters/linkGetSwordRight.gif", defaultCanvas, 35, 30, 5);
		if (imageGetSwordLeft == null) 
			imageGetSwordLeft = new DrawableImageSprite("images/characters/linkGetSwordLeft.gif", defaultCanvas, 35, 30, 5);
		if (imageGetSwordFace == null) 
			imageGetSwordFace = new DrawableImageSprite("images/characters/linkGetSwordFace.gif", defaultCanvas, 20, 35, 5);
		if (imageGetSwordBack == null) 
			imageGetSwordBack = new DrawableImageSprite("images/characters/linkGetSwordBack.gif", defaultCanvas, 20, 30, 5);
		if (imageSwording == null) 
			imageSwording = new DrawableImageSprite("images/characters/linkSwording.gif", defaultCanvas, 35, 35, 8);*/
		
	}
	
	/*public void takingSword(boolean haveSword) {
		takeSword = haveSword;
	}
	
	public boolean isTakingSword() {
		return takeSword;
	}*/

	
}
