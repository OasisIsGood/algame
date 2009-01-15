package zelda.entity;

import java.awt.Canvas;

import gameframework.base.DrawableImage;

public class DrawableImageSprite extends DrawableImage {

	private int pixelsLenght;
	private int pixelsHeight;
	private int numberOfSprites;

	public DrawableImageSprite(String filename, Canvas canvas, int imageLenght, int imageHeight, int nbSprites) {
		super(filename, canvas);
		setPixelsLenght(imageLenght); 	
		setPixelsHeight(imageHeight); 
		setNumberOfSprites(nbSprites);
	}

	public void setNumberOfSprites(int numberOfSprites) {
		this.numberOfSprites = numberOfSprites;
	}
	
	public int getNumberOfSprites() {
		return numberOfSprites;
	}

	public void setPixelsLenght(int pixelsLenght) {
		this.pixelsLenght = pixelsLenght;
	}
	
	public int getPixelsLenght() {
		return pixelsLenght;
	}

	public void setPixelsHeight(int pixelsHeight) {
		this.pixelsHeight = pixelsHeight;
	}

	public int getPixelsHeight() {
		return pixelsHeight;
	}
}
