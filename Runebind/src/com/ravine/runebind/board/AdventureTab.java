package com.ravine.runebind.board;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.ravine.runebind.RuneBind;

public class AdventureTab extends Pullout {

	public AdventureTab(float x, float y) {
		super(Location.top, 1536, 256, x, y);
		this.addListener(new InputListener() {
			private float oldX;
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				Gdx.app.log(RuneBind.LOG, "Pullout clicked");
				oldX = x;
				return true;
			}
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
			}
			@Override
			public void touchDragged(InputEvent event, float x, float y, int pointer) {
				translate(x-oldX,0);
				checkBounds();
				outVec.x = getX();
				inVec.x = getX();
			}
		});
	}
	
	private void checkBounds() {
		if(getX() > 0) setX(0);
		if(getX() < -(getWidth()-getStage().getWidth())) setX(-(getWidth()-getStage().getWidth()));
	}

	public void act(float delta) {
		super.act(delta);
		wrapper.setPosition(getStage().getWidth()/2, getY()-16);
	}
	
	public void draw(SpriteBatch batch, float parentAlpha) {
		batch.draw(corner1R, getX(), getY());
		batch.draw(corner2R, getX() + w-16, getY());
		for(int y = 0; y < h; y+=16) {
			for(int x = 0; x < (y==0?w-32:w); x+=16) {
				if(y == 0) {
					batch.draw(edge2R, getX() + (x+16), getY() + y);
				}else {
					if(x == 0) {
						batch.draw(edge1R, getX() + x, getY() + y);
					}else if(x == w-16) {
						batch.draw(edge3R, getX() + x, getY() + y);
					}else {
						batch.draw(middleR, getX() + x, getY() + y);
					}
				}
			}
		}
		wrapper.draw(batch, parentAlpha);
	}

}
