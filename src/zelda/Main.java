package zelda;

import gameframework.game.GameLevel;

import java.util.ArrayList;

import zelda.game.GameZeldaAWTImpl;

public class Main {
	public static void main(String[] args) {
		//GameZeldaImpl g = new GameZeldaImpl();
		GameZeldaAWTImpl g = new GameZeldaAWTImpl();
		ArrayList<GameLevel> levels = new ArrayList<GameLevel>();

		levels.add(new ZeldaGameLevel(g));

		g.setLevels(levels);
		g.start();
//		g.pause();
	}
}
