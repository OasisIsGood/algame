package zelda.game;

import java.io.IOException;

public interface SaveBuilderReader {

	void read() throws IOException;
	int level();
	int[] life();

}
