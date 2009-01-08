package zelda.base;

import gameframework.base.MoveStrategyKeyboard;
import java.awt.Point;
import java.awt.event.KeyEvent;

import zelda.entity.characters.Link;

public class LinkMoveStrategy extends MoveStrategyKeyboard {
	//private SpeedVector currentMove = getSpeedVector();
	Link link;
	
	public LinkMoveStrategy(Link link) {
		this.link = link;
	}
	
	@Override
	public void keyPressed(KeyEvent event) {
		int keycode = event.getKeyCode();
		if(keycode != KeyEvent.VK_SPACE && !link.isSwording())
			super.keyPressed(event);
		switch (keycode) {
		case KeyEvent.VK_SPACE:
			link.swording();
			getSpeedVector().setDir(new Point(0, 0));
			break;
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		super.keyReleased(e);
		int keycode = e.getKeyCode();
		if(keycode != KeyEvent.VK_SPACE)
			getSpeedVector().setDir(new Point(0, 0));
	}
}
