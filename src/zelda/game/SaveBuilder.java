package zelda.game;

import java.io.IOException;

public interface SaveBuilder {

	public void nbLife(int[] life) throws IOException;
	public void level(int levelNumber) throws IOException;
	public void result() throws IOException;
}
