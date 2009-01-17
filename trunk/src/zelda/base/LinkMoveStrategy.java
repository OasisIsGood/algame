package zelda.base;

import gameframework.base.MoveStrategyKeyboard;

import java.awt.Point;
import java.awt.event.KeyEvent;

import zelda.entity.characters.link.Link;

public class LinkMoveStrategy extends MoveStrategyKeyboard {
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
			link.setSwording(true);
			break;
		case KeyEvent.VK_ESCAPE:
			link.setState("LinkStateDeath"); 
			getSpeedVector().setDir(new Point(0, 0));
			break;
		case KeyEvent.VK_S:
			link.setState("LinkStateHaveSword"); 
			getSpeedVector().setDir(new Point(0, 0));
			break;
		case KeyEvent.VK_H:
			link.setState("LinkStateHaveHammer"); // TODO !!! 
			getSpeedVector().setDir(new Point(0, 0));
			break;
		case KeyEvent.VK_M:
			link.setState("LinkStateHaveMagicalBaguet"); // TODO !!! 
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
