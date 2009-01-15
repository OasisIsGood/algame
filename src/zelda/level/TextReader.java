package zelda.level;

import gameframework.game.GameUniverse;

import java.awt.Canvas;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Hashtable;
import java.util.Map;

import zelda.entity.EntityFactory;
import zelda.game.GameZeldaUniverse;
import zelda.level.ZeldaGameLevel1.direction;

public class TextReader implements LevelReader {

	private Canvas canvas;
	private GameUniverse universe;
	private BufferedReader reader;

	// Associer le nom de l'objet instancié à une méthode
	private Map<String, Method> map = new Hashtable<String, Method>();

	public TextReader(Canvas canvas, GameUniverse universe, File file)
			throws FileNotFoundException {
		this.canvas = canvas;
		this.universe = universe;
		this.reader = new BufferedReader(new FileReader(file));

		Class<EntityFactory> c = EntityFactory.class;
		try {
			map.put("link", c.getDeclaredMethod("createLink", Point.class,
					Canvas.class, GameZeldaUniverse.class));
			map.put("zelda", c.getDeclaredMethod("createZelda", Point.class,
					Canvas.class, GameZeldaUniverse.class));
			map.put("potion", c.getDeclaredMethod("createPotion", Point.class,
					Canvas.class, GameZeldaUniverse.class));
			map.put("guard", c.getDeclaredMethod("createGuard", Point.class,
					Canvas.class, GameZeldaUniverse.class));
			map.put("bomb", c.getDeclaredMethod("createBomb", Point.class,
					Canvas.class, GameZeldaUniverse.class));
			map.put("bush", c.getDeclaredMethod("createBush", Point.class,
					Canvas.class, GameZeldaUniverse.class));
			map.put("superpotion", c.getDeclaredMethod("createSuperPotion",
					Point.class, Canvas.class, GameZeldaUniverse.class));
			map.put("tree", c.getDeclaredMethod("createTree", Point.class,
					Canvas.class, GameZeldaUniverse.class));
			map.put("hammer", c.getDeclaredMethod("createHammer", Point.class,
					Canvas.class, GameZeldaUniverse.class));
			map.put("boss", c.getDeclaredMethod("createBoss", Point.class,
					Canvas.class, GameZeldaUniverse.class));
			map.put("wall", c.getDeclaredMethod("createWall", Point.class,
					direction.class, int.class, Canvas.class,
					GameZeldaUniverse.class));
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void read() throws IOException {
		String line;
		int numberLine = 0;

		while ((line = reader.readLine()) != null) {
			++numberLine;
			String[] t = line.split(" ");
			if (t.length >= 3) {
				t[2] = t[2].toLowerCase();

				if (map.get(t[2]) != null) {
					try {
						if (t[2].equals("wall"))
							map.get(t[2]).invoke(
									null,
									new Point(Integer.parseInt(t[0]), Integer
											.parseInt(t[1])),
									direction.valueOf(t[3].toUpperCase()),
									Integer.parseInt(t[4]), canvas, universe);
						else
							map.get(t[2]).invoke(
									null,
									new Point(Integer.parseInt(t[0]), Integer
											.parseInt(t[1])), canvas, universe);
					} catch (NumberFormatException e) {
						System.out.println("Invalid file, line : "
								+ numberLine + "\n\tText is : " + line);
					} catch (IllegalArgumentException e) {
						System.out.println("Invalid file, line : "
								+ numberLine + "\n\tText is : " + line);
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					} catch (ArrayIndexOutOfBoundsException e) {
						System.out.println("Invalid file, line : "
								+ numberLine + "\n\tText is : " + line);
					}
				}
			} else if (!line.isEmpty())
				throw new IOException("Invalid file, line : " + numberLine
						+ "\n\tText is : " + line);
		}
	}
}
