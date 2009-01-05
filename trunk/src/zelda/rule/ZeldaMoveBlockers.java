package zelda.rule;

import zelda.entity.characters.Link;
import zelda.entity.decors.Bush;
import gameframework.game.IllegalMoveException;
import gameframework.game.MoveBlockerRuleApplierDefaultImpl;

public class ZeldaMoveBlockers extends MoveBlockerRuleApplierDefaultImpl {
	public void moveBlockerRule(Link link, Bush bush) throws IllegalMoveException {
		throw new IllegalMoveException();
	}
}
