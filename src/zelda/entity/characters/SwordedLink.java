package zelda.entity.characters;

import java.awt.Canvas;

public class SwordedLink extends AbstractLink {
	private static int STRENGH = 5;
	
	public SwordedLink(Canvas defaultCanvas) {
		super(defaultCanvas);
	}

	@Override
	public int strengh() {
		return STRENGH;
	}
}
