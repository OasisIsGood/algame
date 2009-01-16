package zelda.rule;

import gameframework.game.IllegalMoveException;
import gameframework.game.MoveBlockerRuleApplierDefaultImpl;
import zelda.entity.characters.AbstractLink;
import zelda.entity.decors.Bush;

public class ZeldaMoveBlockers extends MoveBlockerRuleApplierDefaultImpl {
	
	public void moveBlockerRule(AbstractLink link, Bush bush) throws IllegalMoveException {
		throw new IllegalMoveException();
	}
}
