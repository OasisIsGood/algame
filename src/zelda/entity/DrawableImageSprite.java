package zelda.entity;

import java.awt.Canvas;

import gameframework.base.DrawableImage;

public class DrawableImageSprite extends DrawableImage {

	private int spriteSizeX;
	private int spriteSizeY;
	private int numberOfSprites;

	public DrawableImageSprite(String filename, Canvas canvas, int spriteX, int spriteY, int nbSprites) {
		super(filename, canvas);
		setSpriteSizeX(spriteX); 	
		setSpriteSizeY(spriteY); 
		setNumberOfSprites(nbSprites);
	}

	public void setNumberOfSprites(int numberOfSprites) {
		this.numberOfSprites = numberOfSprites;
	}

	public int getNumberOfSprites() {
		return numberOfSprites;
	}
	
	public void setSpriteSizeX(int spriteSizeX) {
		this.spriteSizeX = spriteSizeX;
	}

	public int getSpriteSizeX() {
		return spriteSizeX;
	}

	public void setSpriteSizeY(int spriteSizeY) {
		this.spriteSizeY = spriteSizeY;
	}

	public int getSpriteSizeY() {
		return spriteSizeY;
	}
}
