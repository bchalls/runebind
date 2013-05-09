package com.ravine.runebind.board;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.ravine.runebind.RuneBind;

public class Pullout extends Actor {

	public static enum Location {
		top,
		bottom,
		left,
		right
	};
	
	private Location loc;
	private int locMod = -1;
	private int w, h;
	private TextureRegion corner1R, corner2R, edge1R, edge2R, edge3R, middleR;
	private TextureRegionDrawable tabRUp, tabRDown;
	private Button tab;
	private Table wrapper;
	private boolean isOut = false;
	private Vector2 inVec, outVec;
	
	
	
	public Pullout(Location location, int width, int height, float x, float y) {
		super();
		Texture pulloutTex = new Texture(Gdx.files.internal("data/pullout.png"));
		Texture tabTex = new Texture(Gdx.files.internal("data/pullTab.png"));
		loc = location;
		this.w = width;
		this.h = height;
		setX(x);
		setY(y);
		inVec = new Vector2(x, y);
		setWidth(w);
		setHeight(h);
		if(loc.equals(Location.left)) {
			corner1R = new TextureRegion(pulloutTex, 16, 0, 16, 16);
			corner2R = new TextureRegion(pulloutTex, 16, 16, 16, 16);
			edge1R = new TextureRegion(pulloutTex, 32, 0, 16, 16);
			edge2R = new TextureRegion(pulloutTex, 48, 16, 16, 16);
			edge3R = new TextureRegion(pulloutTex, 32, 16, 16, 16);
			outVec = new Vector2(0, y);
		}else if(loc.equals(Location.bottom)) {
			corner1R = new TextureRegion(pulloutTex, 0, 0, 16, 16);
			corner2R = new TextureRegion(pulloutTex, 16, 0, 16, 16);
			edge1R = new TextureRegion(pulloutTex, 48, 0, 16, 16);
			edge2R = new TextureRegion(pulloutTex, 32, 0, 16, 16);
			edge3R = new TextureRegion(pulloutTex, 48, 16, 16, 16);
			outVec = new Vector2(x, 0);
		}else if(loc.equals(Location.top)) {
			corner1R = new TextureRegion(pulloutTex, 0, 16, 16, 16);
			corner2R = new TextureRegion(pulloutTex, 16, 16, 16, 16);
			edge1R = new TextureRegion(pulloutTex, 48, 0, 16, 16);
			edge2R = new TextureRegion(pulloutTex, 32, 16, 16, 16);
			edge3R = new TextureRegion(pulloutTex, 48, 16, 16, 16);
			outVec = new Vector2(x, y-h);
		}
		if(loc.equals(Location.right)) {
			corner1R = new TextureRegion(pulloutTex, 0, 0, 16, 16);
			corner2R = new TextureRegion(pulloutTex, 0, 16, 16, 16);
			edge1R = new TextureRegion(pulloutTex, 32, 0, 16, 16);
			edge2R = new TextureRegion(pulloutTex, 48, 0, 16, 16);
			edge3R = new TextureRegion(pulloutTex, 32, 16, 16, 16);
			outVec = new Vector2(x-w, y);
		}
		middleR = new TextureRegion(pulloutTex, 32, 5, 16, 16);
		tabRUp = new TextureRegionDrawable(new TextureRegion(tabTex, 0, 0, 128, 32));
		tabRDown = new TextureRegionDrawable(new TextureRegion(tabTex, 128, 0, 128, 32));
		tab = new Button(tabRUp, tabRDown);
		wrapper = new Table();
		wrapper.add(tab);
		//wrapper.setHeight(tabR.getTopHeight());
		//wrapper.setWidth(tab.getWidth());
		wrapper.setTransform(true);
		wrapper.setOrigin(0, 0);
		if(loc.equals(Location.bottom)) { 
			wrapper.setRotation(180);
			locMod = 1;
		}else if(loc.equals(Location.left)) { 
			wrapper.setRotation(90);
			locMod = 1;
		}
		else if(loc.equals(Location.right)) { 
			wrapper.setRotation(-90);
			locMod = -1;
		}
		this.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				if(x >= getX() && x <= getX() + w && y >= getY() && y <= getY() + h) {
					Gdx.app.log(RuneBind.LOG, "Pullout clicked");
					return true;
				}
				return false;
			}
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
			}
			@Override
			public void touchDragged(InputEvent event, float x, float y, int pointer) {
			}
		});
		wrapper.addListener(new ClickListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				if(x >= tab.getX() && x <= (tab.getX()+ tab.getWidth())
						&& y >= tab.getY() && y <= (tab.getY() + tab.getHeight())) {
					tab.toggle();
					Gdx.app.log(RuneBind.LOG, "Pullout tab 1down");
					return true;
				}
				return false;
			}
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				if(x >= tab.getX() && x <= (tab.getX()+ tab.getWidth())
						&& y >= tab.getY() && y <= (tab.getY() + tab.getHeight())) {
					tab.toggle();
					if(isOut) {
						addAction(Actions.moveTo(inVec.x, inVec.y, 0.075f));
					}else {
						addAction(Actions.moveTo(outVec.x, outVec.y, 0.075f));
					}
					isOut = !isOut;
					Gdx.app.log(RuneBind.LOG, "Pullout toggle");
				}
			}
			@Override
			public void touchDragged(InputEvent event, float x, float y, int pointer) {
				if(!(x >= tab.getX() || x <= (tab.getX()+ tab.getWidth())
						&& y >= tab.getY() && y <= (tab.getY() + tab.getHeight()))) {
					tab.toggle();
				}
			}
		});
		
		
	}
	
	public void addToStage() {
		this.getStage().addActor(wrapper);
	}
	
	public void rePosition() { 
		
	}
	
	public void act(float delta) {
		super.act(delta);
		if(loc.equals(Location.top)) {
			wrapper.setPosition(getStage().getWidth()/2, getY()+(locMod*16));
		}else if(loc.equals(Location.bottom)) {
			wrapper.setPosition(getStage().getWidth()/2, getY()+h+16);
		}else if(loc.equals(Location.left)) {
			wrapper.setPosition(getX()+w+16, getStage().getHeight()/2);
		}else if(loc.equals(Location.right) ) { 
			wrapper.setPosition(getX()+(locMod*16), getStage().getHeight()/2);
		}
	}
	
	public void draw(SpriteBatch batch, float parentAlpha) {
		if(loc.equals(Location.top)) { drawTop(batch, parentAlpha); }
		else if(loc.equals(Location.bottom)) { drawBottom(batch, parentAlpha); }
		else if(loc.equals(Location.left)) { drawLeft(batch, parentAlpha); }
		else if(loc.equals(Location.right)) { drawRight(batch, parentAlpha); }
	}
	
	protected void drawTop(SpriteBatch batch, float parentAlpha) {
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
	protected void drawBottom(SpriteBatch batch, float parentAlpha) {
		batch.draw(corner1R, getX(), getY() + (h-16));
		batch.draw(corner2R, getX() + w-16, getY() + (h-16));
		for(int y = 0; y < h; y+=16) {
			for(int x = 0; x < (y==(h-16)?w-32:w); x+=16) {
				if(y == (h-16)) {
					batch.draw(edge2R, getX() + (x+16), getY() + y);
				}else if(y != h) {
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
	protected void drawLeft(SpriteBatch batch, float parentAlpha) {
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
	protected void drawRight(SpriteBatch batch, float parentAlpha) {
		batch.draw(corner1R, getX(), getY() + (h-16));
		batch.draw(corner2R, getX(), getY());
		for(int y = 0; y < h; y+=16) {
			for(int x = 0; x < ((y==0||y==h-16)?w-16:w); x+=16) {
				if(y == 0) {
					batch.draw(edge3R, getX() + x+16, getY() + y);
				}else if( y == h-16 ) {
					batch.draw(edge1R, getX() + x+16, getY() + y);
				}else {
					if(x == 0) {
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
