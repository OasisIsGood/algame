package zelda.base;

import gameframework.base.MoveStrategyKeyboard;
import gameframework.base.SpeedVector;

import java.awt.Point;
import java.awt.event.KeyEvent;

public class LinkMoveStrategy extends MoveStrategyKeyboard {
	private SpeedVector currentMove = getSpeedVector();
	
	@Override
	public void keyPressed(KeyEvent event) {
		super.keyPressed(event);
		int keycode = event.getKeyCode();
		switch (keycode) {
		case KeyEvent.VK_SPACE:
			// TODO Ici Link doit être averti du fait de devoir frapper
			System.out.println(currentMove.getDir());
			System.out.println("Tape avec ton épée mon petit !");
			break;
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		super.keyReleased(e);
		currentMove.setDir(new Point(0, 0));
	}
}
