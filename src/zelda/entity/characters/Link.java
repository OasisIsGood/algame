package zelda.entity.characters;

import gameframework.base.Drawable;
import gameframework.base.DrawableImage;
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

public class Link extends GameMovable implements Drawable, GameEntity,
		Overlappable {

	public static int SPRITE_SIZE = 32;
	
	protected static DrawableImage image = null;
	protected Canvas defaultCanvas;
	protected int spriteNumber = 0;
	protected int spriteType = 0;
	protected boolean movable = true;
	private boolean isSwording = false;
	private Timer timer;
	private int spriteSizeX;
	private int spriteSizeY;

	public Link(Canvas defaultCanvas) {
		if (image == null) {
			image = new DrawableImage("images/characters/linkFace.gif",
							defaultCanvas);
			spriteSizeX = 25; // linkFace
			spriteSizeY = 25; // linkFace
			spriteNumber = 0;
			spriteType = 0;
		}
		timer = createTimer();
	}

	@Override
	public void draw(Graphics g) {
		Point tmp = getSpeedVector().getDir();
		movable = true;
	
		if (tmp.getX() == 1) {	
			//System.out.println("droite");
			//spriteType = 0;
		} else if (tmp.getX() == -1) {
			//System.out.println("gauche");
			//spriteType = 1;
		} else if (tmp.getY() == -1) {
			//System.out.println("haut");
			//spriteType = 2;
		} else if (tmp.getY() == 1) {
			//System.out.println("bas");
			//spriteType = 3;
		} else {  
			//System.out.println("de Face");
		//	image = new DrawableImage("images/characters/linkFace.gif",
					//defaultCanvas); // PAS POSSIBLE
			spriteNumber = 0;
			spriteType = 0;
			movable = false;
		}

		g.drawImage(
				image.getImage(),
				(int) getPosition().getX(),
				(int) getPosition().getY(),
				(int) getPosition().getX() + spriteSizeX,				
				(int) getPosition().getY() + spriteSizeY,
				spriteNumber * spriteSizeX, 
				spriteType * spriteSizeY, 
				(spriteNumber + 1) * spriteSizeX,
				(spriteType + 1) * spriteSizeY, 
				null);
	}

	@Override
	public Rectangle getBoundingBox() {
		return (new Rectangle((int) getPosition().getX(), 
				(int) getPosition().getY(), spriteSizeX, spriteSizeY));
	}

	@Override
	public void oneStepMoveHandler() {
		if(timer.isRunning() && isSwording) {
			spriteNumber++;
			spriteNumber = spriteNumber % 6;
		}
		else if (movable) {
			spriteNumber++;
			spriteNumber = spriteNumber % 7;
		}
	}

	public void swording() {
		//image  = new DrawableImage("images/characters/linkSword.gif",
		//		defaultCanvas);
		spriteSizeX = 25; // linkFace
		spriteSizeY = 25; // linkFace
		spriteNumber = 0;
		spriteType = 0;
		
		isSwording = true;
		timer.start();
	}

	public boolean isSwording() {
		return isSwording;
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
