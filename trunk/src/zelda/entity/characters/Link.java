package zelda.entity.characters;

import java.awt.Canvas;
import zelda.entity.DrawableImageSprite;

public class Link extends AbstractLink {
	
	protected static DrawableImageSprite image = null;
	private static int STRENGH = 2;

	public Link(Canvas defaultCanvas) {
		super(defaultCanvas);
		if (image == null)
			image	  = new DrawableImageSprite("images/characters/LINK.gif", defaultCanvas, 35, 35, 8);
		spriteNumber = 0;
		spriteType = 0;
	}

	@Override
	public int strengh() {
		// TODO Auto-generated method stub
		return STRENGH;
	}
}
