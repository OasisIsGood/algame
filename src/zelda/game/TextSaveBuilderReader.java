package zelda.game;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

public class TextSaveBuilderReader implements SaveBuilderReader {

	int level = -1;
	int[] life = null;
	BufferedReader br;
	private Map<String, String> map = new Hashtable<String, String>();

	public TextSaveBuilderReader(File file) {
		try {
			br = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		;
	}

	@Override
	public int level() {
		try {
			return Integer.parseInt(map.get("LEVEL"));
		} catch (NumberFormatException e) {
			System.err.println("Error : Invalid Level !");
			return 0;
		} catch (NullPointerException e) {
			System.err.println("Error : Invalid Level !");
			return 0;
		}
	}

	@Override
	public int[] life() {
		if (life == null) {
			String s = map.get("LIFE");
			if (s != null) {
				String t[] = s.split(" ");
				life = new int[t.length];
				for (int i = 0; i < t.length; ++i) {
					try {
						life[i] = Integer.parseInt(t[i]);
					} catch (NumberFormatException e) {
						System.err.println("Error : Invalid Life !");
						life[i] = 0;
					}
				}
			}
		}
		return life;
	}

	@Override
	public void read() throws IOException {
		String line = "";
		int nbLine = 0;
		while ((line = br.readLine()) != null) {
			if (line.startsWith("LEVEL")) {
				map.put("LEVEL", line.substring("LEVEL ".length()).replaceAll(
						" ", ""));
			} else if (line.startsWith("LIFE")) {
				map.put("LIFE", line.substring("LIFE ".length()).replaceAll(
						" ", ""));
			}
			nbLine++;
		}
	}

}
