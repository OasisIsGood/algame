package zelda.game;

import gameframework.game.GameDefaultImpl;
import gameframework.game.GameEntity;
import gameframework.game.GameUniverse;
import gameframework.game.GameUniverseDefaultImpl;
import gameframework.game.MoveBlockerChecker;
import gameframework.game.OverlapProcessor;

import java.util.Iterator;


public class GameZeldaUniverseDefaultImpl implements GameZeldaUniverse {
	
	protected GameUniverse universe;
	protected MoveBlockerChecker obs;

	public GameZeldaUniverseDefaultImpl(MoveBlockerChecker obs, OverlapProcessor col) {
		universe = new GameUniverseDefaultImpl(obs, col);
		this.obs = obs;
	}
	
	@Override
	public MoveBlockerChecker getMoveBlockerChecker() {
		return obs;
	}

	@Override
	public void addGameEntity(GameEntity gameEntity) {
		universe.addGameEntity(gameEntity);
	}

	@Override
	public void allOneStepMoves() {
		universe.allOneStepMoves();
	}

	@Override
	public Iterator<GameEntity> gameEntities() {
		return universe.gameEntities();
	}

	@Override
	public void processAllOverlaps() {
		universe.processAllOverlaps();
	}

	@Override
	public void removeGameEntity(GameEntity gameEntity) {
		universe.removeGameEntity(gameEntity);
	}

}
