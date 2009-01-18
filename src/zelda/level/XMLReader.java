package zelda.level;

import gameframework.game.GameUniverse;

import java.awt.Canvas;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Hashtable;
import java.util.Map;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import zelda.entity.EntityFactory;
import zelda.level.ZeldaGameLevel.direction;

public class XMLReader implements LevelReader {

	private Canvas canvas;
	private GameUniverse universe;
	private Element root;

	private Map<String, Method> map = new Hashtable<String, Method>();

	public XMLReader(Canvas canvas, GameUniverse universe, File file)
			throws IOException {
		this.canvas = canvas;
		this.universe = universe;

		

		Class<EntityFactory> c = EntityFactory.class;
		try {
			map.put("link", c.getDeclaredMethod("createLink", Point.class,
					Canvas.class, GameUniverse.class));
			map.put("zelda", c.getDeclaredMethod("createZelda", Point.class,
					Canvas.class, GameUniverse.class));
			map.put("potion", c.getDeclaredMethod("createPotion", Point.class,
					Canvas.class, GameUniverse.class));
			map.put("guard", c.getDeclaredMethod("createGuard", Point.class,
					Canvas.class, GameUniverse.class));
			map.put("bomb", c.getDeclaredMethod("createBomb", Point.class,
					Canvas.class, GameUniverse.class));
			map.put("bush", c.getDeclaredMethod("createBush", Point.class,
					Canvas.class, GameUniverse.class));
			map.put("superpotion", c.getDeclaredMethod("createSuperPotion",
					Point.class, Canvas.class, GameUniverse.class));
			map.put("tree", c.getDeclaredMethod("createTree", Point.class,
					Canvas.class, GameUniverse.class));
			map.put("hammer", c.getDeclaredMethod("createHammer", Point.class,
					Canvas.class, GameUniverse.class));
			map.put("wall", c.getDeclaredMethod("createWall", Point.class,
					direction.class, int.class, Canvas.class,
					GameUniverse.class));
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void read() throws IOException {
		NodeList nodeList = root.getChildNodes();
		int length = nodeList.getLength();
		for (int i = 0; i < length; ++i) {
			Node node = nodeList.item(i);
			Map<String, String> hashMap = new Hashtable<String, String>();
			NodeList list = node.getChildNodes();
			int size = list.getLength();
			for (int j = 0; j < size; ++j) {
				Element n = (Element) list.item(j);
				System.out.println(n.getNodeName().toLowerCase() + " et " + n.getNodeValue().toLowerCase());
				hashMap.put(n.getNodeName().toLowerCase(), n.getNodeValue()
						.toLowerCase());
			}
			System.out.println(hashMap);
			try {
				if (node.getNodeName().toLowerCase().equals("walls")) {
					map.get(node.getNodeName().toLowerCase()).invoke(
							null,
							new Point(Integer.parseInt(hashMap.get("x")),
									Integer.parseInt(hashMap.get("y"))),
							direction.valueOf(hashMap.get("direction")),
							Integer.parseInt(hashMap.get("length")), canvas,
							universe);
				} else {
					map.get(node.getNodeName().toLowerCase()).invoke(
							null,
							new Point(Integer.parseInt(hashMap.get("x")),
									Integer.parseInt(hashMap.get("y"))),
							canvas, universe);
				}
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
	}

}
