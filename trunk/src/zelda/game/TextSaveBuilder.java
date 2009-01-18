package zelda.game;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TextSaveBuilder implements SaveBuilder {

	BufferedWriter bw;
	
	public TextSaveBuilder(File file) {
		try {
			if(!file.exists())
				if(!file.createNewFile())
					throw new IOException(file.getName() + " (No such file or directory)");
			this.bw = new BufferedWriter(new FileWriter(file));
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}

	@Override
	public void level(int levelNumber) throws IOException {
		bw.write("LEVEL " + Integer.toString(levelNumber) + "\n");
	}

	@Override
	public void nbLife(int[] life) throws IOException {
		bw.write("LIFE ");
		for(int i = 0; i < life.length; ++i)
			bw.write(Integer.toString(life[i]) + " ");
		bw.write("\n");
	}

	@Override
	public void result() throws IOException {
		bw.flush();
	}
}
