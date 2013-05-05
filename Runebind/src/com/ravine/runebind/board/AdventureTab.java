package com.ravine.runebind.board;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class AdventureTab extends Pullout {

	public AdventureTab(int width, int height, int x, int y) {
		super(Location.top, width, height, x, y);
		// TODO Auto-generated constructor stub
	}
	
	public void draw(SpriteBatch batch, float parentAlpha) {
		drawTop(batch, parentAlpha);
	}

}
