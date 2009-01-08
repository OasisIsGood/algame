package zelda.entity.characters;

import gameframework.base.Drawable;
import gameframework.base.DrawableImage;
import gameframework.base.Overlappable;
import gameframework.game.GameEntity;
import gameframework.game.GameMovable;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class Link extends GameMovable implements Drawable, GameEntity,
		Overlappable {

	public static int SPRITE_SIZE = 30;
	
	protected static DrawableImage image = null;
	protected Canvas defaultCanvas;
	protected int spriteNumber = 0;
	protected int spriteType = 0;
	protected boolean movable = true;
	private boolean isSwording = false;

	Timer timer;

	public Link(Canvas defaultCanvas) {
		if (image == null) {
			//image = new DrawableImage("images/characters/zeldaa.gif",
					//defaultCanvas);
			image = new DrawableImage("images/characters/zeldaSword.gif",
							defaultCanvas);
			spriteType = 0;
		}
		timer = createTimer();
	}

	@Override
	public void draw(Graphics g) {
		//Point tmp = getSpeedVector().getDir();
		movable = true;
	
		/*if (tmp.getX() == 1) {
			spriteType = 0;
		} else if (tmp.getX() == -1) {
			spriteType = 1;
		} else if (tmp.getY() == -1) {
			spriteType = 2;
		} else if (tmp.getY() == 1) {
			spriteType = 3;
		} else {  
			spriteType = 3;
			spriteNumber = 0;
			movable = false;
		}*/

		g.drawImage(
				image.getImage(),
				(int) getPosition().getX(),
				(int) getPosition().getY(),
				(int) getPosition().getX() + SPRITE_SIZE,				
				(int) getPosition().getY() + SPRITE_SIZE,
				spriteNumber * 30, 
				spriteType * 40, 
				(spriteNumber + 1) * 30,
				(spriteType + 1) * 40, 
				null);
	}

	@Override
	public Rectangle getBoundingBox() {
		return (new Rectangle((int) getPosition().getX(), 
				(int) getPosition().getY(), SPRITE_SIZE, SPRITE_SIZE));
	}

	@Override
	public void oneStepMoveHandler() {
		if(timer.isRunning()) {
			spriteNumber++;
			spriteNumber = spriteNumber % 6;
		}
		/*else if (movable) {
			spriteNumber++;
			spriteNumber = spriteNumber % 3;
		}*/
	}

	public void swording() {
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
