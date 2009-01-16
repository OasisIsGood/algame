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

public abstract class AbstractLink extends GameMovable implements Drawable,
		GameEntity, Overlappable {

	public static final int SPRITE_SIZE = 26;


	protected int spriteNumber = 0;
	protected int spriteType = 0;
	protected boolean isDeathing = false;
	protected boolean movable = true;

	protected static DrawableImageSprite image = null;

	private boolean isSwording = false;
	protected Timer timer;
	protected Canvas defaultCanvas;

	public AbstractLink(Canvas defaultCanvas) {
		super();

		if (image == null)
			image = new DrawableImageSprite(
					"images/characters/LINK.gif", defaultCanvas, 25, 25, 8);
		spriteNumber = 0;
		spriteType = 0;
		timer = createTimer();
	}

	@Override
	public void draw(Graphics g) {
		Point tmp = getSpeedVector().getDir();
		movable = true;
		
		if (isDeathing) {
			spriteType = 4;
			movable = false;
		} else if (tmp.getX() == 1) {	// "droite"
			spriteType = 1;
		} else if (tmp.getX() == -1) { 	// "gauche"
			spriteType = 2;
		} else if (tmp.getY() == -1) { 	// "haut");
			spriteType = 3;
		} else if (tmp.getY() == 1) { 	// "bas");
			spriteType = 0;
		} else {  						// "de Face");
			spriteType = 0;
			spriteNumber = 0;
			movable = false;
		}
		
		g.drawImage(image.getImage(), (int) getPosition().getX(),
				(int) getPosition().getY(), (int) getPosition().getX()
						+ SPRITE_SIZE,
				(int) getPosition().getY() + SPRITE_SIZE, spriteNumber
						* image.getPixelsLenght(), spriteType
						* image.getPixelsHeight(), (spriteNumber + 1)
						* image.getPixelsLenght(), (spriteType + 1)
						* image.getPixelsHeight(), null);
	}

	@Override
	public Rectangle getBoundingBox() {
		return (new Rectangle((int) getPosition().getX(), (int) getPosition()
				.getY(), SPRITE_SIZE, SPRITE_SIZE));
	}

	@Override
	public void oneStepMoveHandler() {
		if (movable || (timer.isRunning() && isSwording)) {
			spriteNumber++;
			spriteNumber = spriteNumber % image.getNumberOfSprites();
		}
	}

	public void deathing() {
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

	public void swording() {
		isSwording = true;
		timer.start();
	}

	public boolean isSwording() {
		return isSwording;
	}
	
	public abstract int strengh();
}