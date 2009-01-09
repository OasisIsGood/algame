package zelda.game;

import gameframework.game.GameUniverse;
import gameframework.game.MoveBlockerChecker;

public interface GameZeldaUniverse extends GameUniverse {

	public MoveBlockerChecker getMoveBlockerChecker();
	
}
