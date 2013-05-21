package com.ravine.runebind.board;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.ravine.runebind.RuneBind;

public class MarketTab extends Pullout {

	public MarketTab() {
		super(Location.left, 304, 3264, -304, 0);
		this.addListener(new InputListener() {
			private float oldY;
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				Gdx.app.log(RuneBind.LOG, "Pullout clicked");
				oldY = y;
				return true;
			}
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
			}
			@Override
			public void touchDragged(InputEvent event, float x, float y, int pointer) {
				translate(0,y-oldY);
				checkBounds();
				outVec.y = getY();
				inVec.y = getY();
			}
		});
	}
	
	private void checkBounds() {
		if(getY() > 0) setY(0);
		if(getY() < -(getHeight()-getStage().getHeight())) setY(-(getHeight()-getStage().getHeight()));
	}

	public void act(float delta) {
		super.act(delta);
		wrapper.setPosition(getX()+w+16, getStage().getHeight()/2);
	}
	
	public void draw(SpriteBatch batch, float parentAlpha) {
		batch.draw(corner1R, getX() + w - 16, getY() + (h-16));
		batch.draw(corner2R, getX() + w - 16, getY());
		for(int y = 0; y < h; y+=16) {
			for(int x = 0; x < ((y==0||y==h-16)?w-16:w); x+=16) {
				if(y == 0) {
					batch.draw(edge3R, getX() + x, getY() + y);
				}else if( y == h-16 ) {
					batch.draw(edge1R, getX() + x, getY() + y);
				}else {
					if(x == w-16) {
						batch.draw(edge2R, getX() + x, getY() + y);
					}else {
						batch.draw(middleR, getX() + x, getY() + y);
					}
				}
			}
		}
		wrapper.draw(batch, parentAlpha);
	}
}
