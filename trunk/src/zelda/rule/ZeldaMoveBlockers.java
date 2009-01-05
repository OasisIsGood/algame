package zelda.rule;

import zelda.entity.characters.Link;
import zelda.entity.decors.Buisson;
import gameframework.game.IllegalMoveException;
import gameframework.game.MoveBlockerRuleApplierDefaultImpl;

public class ZeldaMoveBlockers extends MoveBlockerRuleApplierDefaultImpl {
	public void moveBlockerRule(Link link, Buisson buisson) throws IllegalMoveException {
		throw new IllegalMoveException();
	}
}
