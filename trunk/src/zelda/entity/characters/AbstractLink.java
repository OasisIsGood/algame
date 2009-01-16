package zelda.entity.characters;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import zelda.entity.DrawableImageSprite;

import gameframework.base.Drawable;
import gameframework.base.Overlappable;
import gameframework.game.GameEntity;
import gameframework.game.GameMovable;

public abstract class AbstractLink extends GameMovable implements Drawable,
		GameEntity, Overlappable {

	public static final int SPRITE_SIZE = 35;

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
					"images/characters/LINK.gif", defaultCanvas, 35, 35, 8);
		spriteNumber = 0;
		spriteType = 0;
		timer = createTimer();
	}

	@Override
	public void draw(Graphics g) {
		Point tmp = getSpeedVector().getDir();
		movable = true;

		/*if (isDeathing) {
			image = imageDeath;
		} else */if (tmp.getX() == 1) {
			// System.out.println("droite");

		} else if (tmp.getX() == -1) {
			// System.out.println("gauche");

		} else if (tmp.getY() == -1) {
			// System.out.println("haut");
			
		} else if (tmp.getY() == 1) {
			// System.out.println("bas");
	
		} else {
			// System.out.println("de Face");

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

	public void swording() {
		spriteNumber = 0;
		timer.start();
	}

	public boolean isSwording() {
		return isSwording;
	}
}