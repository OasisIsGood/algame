package zelda;

import gameframework.game.GameLevel;

import java.io.File;
import java.util.ArrayList;

import zelda.game.GameZeldaAWTImpl;
import zelda.level.ZeldaGameLevel;
import zelda.level.ZeldaGameLevel1;

public class Main {
	public static void main(String[] args) {
		//GameZeldaImpl g = new GameZeldaImpl();
		GameZeldaAWTImpl g = new GameZeldaAWTImpl();
		ArrayList<GameLevel> levels = new ArrayList<GameLevel>();

		levels.add(new ZeldaGameLevel(g, new File("levels/level1")));
		//levels.add(new ZeldaGameLevel(g, new File("level1.xml")));
		levels.add(new ZeldaGameLevel1(g));
		//levels.add(new ZeldaGameLevel2(g));
		
		g.setLevels(levels);
		g.start();
//		g.pause();
	}
}
