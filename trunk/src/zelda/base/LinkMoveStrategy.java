package zelda.base;

import gameframework.base.MoveStrategyKeyboard;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;

import zelda.entity.characters.link.Link;
import zelda.entity.characters.link.LinkState;

public class LinkMoveStrategy extends MoveStrategyKeyboard {
	Link link;

	public LinkMoveStrategy(Link link) {
		this.link = link;
	}

	@Override
	public void keyPressed(KeyEvent event) {
		if (!link.isDeath() || !link.isSingingOcarina()) {
			int keycode = event.getKeyCode();
			if (keycode != KeyEvent.VK_SPACE && !link.isFighting())
				super.keyPressed(event);
			switch (keycode) {
			case KeyEvent.VK_SPACE:
				link.setFighting(true);
				break;
			case KeyEvent.VK_ESCAPE:
				link.setState("LinkStateDeath");
				getSpeedVector().setDir(new Point(0, 0));
				break;
			case KeyEvent.VK_S:
				link.setState("LinkStateHaveSword");
				getSpeedVector().setDir(new Point(0, 0));
				break;
			case KeyEvent.VK_O:
				LinkState linkState = link.getState(); 
				link.setState("LinkStateSingingOcarina");
				getSpeedVector().setDir(new Point(0, 0));
				// TODO CE QUI SUIT FAIT TOUT BUGGER
				try {
					Sound sound = new Sound(new File("sounds/LOZ_Recorder.wav"));
					sound.play();
				} catch (FileNotFoundException e) {
					System.out
							.println("(ZeldaOverlaps) Explosion Sound file not found");
				}
				link.setState(linkState);
				break;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		super.keyReleased(e);
		int keycode = e.getKeyCode();
		if (keycode != KeyEvent.VK_SPACE)
			getSpeedVector().setDir(new Point(0, 0));
	}
}
