package zelda.entity.characters;

import gameframework.base.Drawable;
import gameframework.base.Overlappable;
import gameframework.game.GameEntity;
import gameframework.game.GameMovable;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import zelda.entity.DrawableImageSprite;

public class Link extends GameMovable implements Drawable, GameEntity,
		Overlappable {

	public static int SPRITE_SIZE = 32;
	
	protected Canvas defaultCanvas;
	protected int spriteNumber = 0;
	protected int spriteType = 0;
	protected boolean movable = true;
	private Timer timer;
	private boolean isSwording = false;
	private boolean takeSword = false;
	private boolean isDeathing = false;

	protected static DrawableImageSprite image = null;
	protected static DrawableImageSprite imageFace = null;
	protected static DrawableImageSprite imageRight = null;
	protected static DrawableImageSprite imageLeft = null;
	protected static DrawableImageSprite imageBack = null;
	protected static DrawableImageSprite imageGetSwordRight = null;
	protected static DrawableImageSprite imageGetSwordLeft = null;
	protected static DrawableImageSprite imageGetSwordFace = null;
	protected static DrawableImageSprite imageGetSwordBack = null;
	protected static DrawableImageSprite imageDeath = null;

	public Link(Canvas defaultCanvas) {
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
		if (imageGetSwordRight == null) 
			imageGetSwordRight = new DrawableImageSprite("images/characters/linkGetSwordRight.gif", defaultCanvas, 35, 30, 5);
		if (imageGetSwordLeft == null) 
			imageGetSwordLeft = new DrawableImageSprite("images/characters/linkGetSwordLeft.gif", defaultCanvas, 35, 30, 5);
		if (imageGetSwordFace == null) 
			imageGetSwordFace = new DrawableImageSprite("images/characters/linkGetSwordFace.gif", defaultCanvas, 20, 35, 5);
		if (imageGetSwordBack == null) 
			imageGetSwordBack = new DrawableImageSprite("images/characters/linkGetSwordBack.gif", defaultCanvas, 20, 30, 5);
		if (imageDeath == null) 
			imageDeath = new DrawableImageSprite("images/characters/linkDeath.gif", defaultCanvas, 25, 30, 6);
		
		spriteNumber = 0;
		spriteType = 0;
		timer = createTimer();
	}

	@Override
	public void draw(Graphics g) {
		Point tmp = getSpeedVector().getDir();
		movable = true;
	
		if(isDeathing) {
			image = imageDeath;
		}
		else if (tmp.getX() == 1) {	
			//System.out.println("droite");
			if(takeSword)
				image = imageGetSwordRight;
			else
				image = imageRight;
		} else if (tmp.getX() == -1) {
			//System.out.println("gauche");
			if(takeSword)
				image = imageGetSwordLeft;
			else
				image = imageLeft;
		} else if (tmp.getY() == -1) {
			//System.out.println("haut");
			if(takeSword)
				image = imageGetSwordBack;
			else
				image = imageBack;
		} else if (tmp.getY() == 1) {
			//System.out.println("bas");
			if(takeSword)
				image = imageGetSwordFace;
			else
				image = imageFace;
		} else {  
			//System.out.println("de Face");
			if(takeSword)
				image = imageGetSwordFace;
			else
				image = imageFace;
			spriteNumber = 0;
			movable = false;
		}

		g.drawImage(
				image.getImage(),
				(int) getPosition().getX(),
				(int) getPosition().getY(),
				(int) getPosition().getX() + image.getSpriteSizeX(),				
				(int) getPosition().getY() + image.getSpriteSizeY(),
				spriteNumber * image.getSpriteSizeX(), 
				spriteType * image.getSpriteSizeY(), 
				(spriteNumber + 1) * image.getSpriteSizeX(),
				(spriteType + 1) * image.getSpriteSizeY(), 
				null);
	}

	@Override
	public Rectangle getBoundingBox() {
		return (new Rectangle((int) getPosition().getX(), 
				(int) getPosition().getY(), image.getSpriteSizeX(),image.getSpriteSizeY()));
	}

	@Override
	public void oneStepMoveHandler() {
		if(movable || (timer.isRunning() && isSwording)) {
			spriteNumber++;
			spriteNumber = spriteNumber % image.getNumberOfSprites();
		}
	}

	public void swording() {
		spriteNumber = 0;
		isSwording = true;
		timer.start();
	}

	public boolean isSwording() {
		return isSwording;
	}
	
	public void takingSword(boolean haveSword) {
		takeSword = haveSword;
	}
	
	public boolean isTakingSword() {
		return takeSword;
	}
	
	public void deathing() {
		spriteNumber = 0;
		isDeathing = true;
	}
	
	public boolean isDeathing() {
		return isDeathing;
	}

	private Timer createTimer() {
		ActionListener action = new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				isSwording = false;
				timer.stop();
				spriteNumber = 0;
			}
		};
		return new Timer(600, action);
	}
}
