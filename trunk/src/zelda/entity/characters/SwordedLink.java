package zelda.entity.characters;

import java.awt.Canvas;

import zelda.entity.DrawableImageSprite;

public class SwordedLink extends AbstractLink {
	private static int STRENGH = 5;
	
	public SwordedLink(Canvas defaultCanvas) {
		super(defaultCanvas);
		if (image == null)
			image	  = new DrawableImageSprite("images/characters/LINKSWORD.gif", defaultCanvas, 35, 35, 8);
		spriteNumber = 0;
		spriteType = 0;
	}

	@Override
	public int strengh() {
		return STRENGH;
	}
}
