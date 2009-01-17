package zelda.game;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class TextSaveBuilderReader implements SaveBuilderReader {
	
	int level = 0;
	int[] life = null;
	BufferedReader br;

	public TextSaveBuilderReader(File file) {
		try {
			br = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		};
	}

	@Override
	public int level() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int[] life() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void read() {
		// TODO Auto-generated method stub
		
	}

}
