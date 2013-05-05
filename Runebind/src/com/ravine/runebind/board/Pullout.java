package com.ravine.runebind.board;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
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
	private int locMod = 1;
	private int w, h;
	private TextureRegion corner1R, corner2R, edge1R, edge2R, edge3R, middleR;
	private TextureRegionDrawable tabR;
	private Button tab;
	private Table wrapper;
	private boolean isOut = false;
	
	
	public Pullout(Location location, int width, int height, int x, int y) {
		Texture pulloutTex = new Texture(Gdx.files.internal("data/pullout.png"));
		Texture tabTex = new Texture(Gdx.files.internal("data/pullTab.png"));
		loc = location;
		this.w = width;
		this.h = height;
		setX(x);
		setY(y);
		if(loc.equals(Location.left)) {
			corner1R = new TextureRegion(pulloutTex, 16, 0, 16, 16);
			corner2R = new TextureRegion(pulloutTex, 16, 16, 16, 16);
			edge1R = new TextureRegion(pulloutTex, 32, 0, 16, 16);
			edge2R = new TextureRegion(pulloutTex, 48, 16, 16, 16);
			edge3R = new TextureRegion(pulloutTex, 32, 16, 16, 16);
		}
		if(loc.equals(Location.bottom)) {
			corner1R = new TextureRegion(pulloutTex, 0, 0, 16, 16);
			corner2R = new TextureRegion(pulloutTex, 16, 0, 16, 16);
			edge1R = new TextureRegion(pulloutTex, 48, 0, 16, 16);
			edge2R = new TextureRegion(pulloutTex, 32, 0, 16, 16);
			edge3R = new TextureRegion(pulloutTex, 48, 16, 16, 16);
		}
		if(loc.equals(Location.top)) {
			corner1R = new TextureRegion(pulloutTex, 0, 16, 16, 16);
			corner2R = new TextureRegion(pulloutTex, 16, 16, 16, 16);
			edge1R = new TextureRegion(pulloutTex, 48, 0, 16, 16);
			edge2R = new TextureRegion(pulloutTex, 32, 16, 16, 16);
			edge3R = new TextureRegion(pulloutTex, 48, 16, 16, 16);
		}
		if(loc.equals(Location.right)) {
			corner1R = new TextureRegion(pulloutTex, 0, 0, 16, 16);
			corner2R = new TextureRegion(pulloutTex, 0, 16, 16, 16);
			edge1R = new TextureRegion(pulloutTex, 32, 0, 16, 16);
			edge2R = new TextureRegion(pulloutTex, 48, 0, 16, 16);
			edge3R = new TextureRegion(pulloutTex, 32, 16, 16, 16);
		}
		middleR = new TextureRegion(pulloutTex, 32, 5, 16, 16);
		tabR = new TextureRegionDrawable(new TextureRegion(tabTex));
		tab = new Button(tabR);
		wrapper = new Table();
		wrapper.add(tab);
		//wrapper.setHeight(tabR.getTopHeight());
		//wrapper.setWidth(tab.getWidth());
		wrapper.setTransform(true);
		wrapper.setOrigin(0, wrapper.getPrefHeight()/4);
		if(loc.equals(Location.bottom)) { 
			wrapper.setRotation(180);
			locMod = -1;
		}
	}
	
	public void rePosition() { 
		
	}
	
	public void act(float delta) {
		super.act(delta);
		wrapper.setPosition(getStage().getWidth()/2, getY() + (locMod *wrapper.getPrefHeight()/2));
	}
	
	public void draw(SpriteBatch batch, float parentAlpha) {
		if(loc.equals(Location.top)) { drawTop(batch, parentAlpha); }
		if(loc.equals(Location.bottom)) { drawBottom(batch, parentAlpha); }
	}
	
	protected void drawTop(SpriteBatch batch, float parentAlpha) {
		batch.draw(corner1R, getX(), getY() + tab.getHeight());
		batch.draw(corner2R, getX() + w-16, getY() + tab.getHeight());
		for(int y = 0; y < h-16; y+=16) {
			for(int x = 0; x < w; x+=16) {
				if(y == 0) {
					batch.draw(edge2R, getX() + (x+16), getY() + (y + tab.getHeight()));
				}else if(y != 0) {
					if(x == 0) {
						batch.draw(edge1R, getX() + x, getY() + (y + tab.getHeight()));
					}else if(x == w-16) {
						batch.draw(edge3R, getX() + x, getY() + (y + tab.getHeight()));
					}else {
						batch.draw(middleR, getX() + x, getY() + (y + tab.getHeight()));
					}
				}
				if(y == 0 && x == w-48) break;
			}
		}
		wrapper.draw(batch, parentAlpha);
	}
	protected void drawBottom(SpriteBatch batch, float parentAlpha) {
		batch.draw(corner1R, getX(), getY() - tab.getHeight());
		batch.draw(corner2R, getX() + w-16, getY() - tab.getHeight());
		for(int y = 0; y < h-16; y+=16) {
			for(int x = 0; x < w; x+=16) {
				if(y == 0) {
					batch.draw(edge2R, getX() + (x+16), getY() - (y + tab.getHeight()));
				}else if(y != 0) {
					if(x == 0) {
						batch.draw(edge1R, getX() + x, getY() - (y + tab.getHeight()));
					}else if(x == w-16) {
						batch.draw(edge3R, getX() + x, getY() - (y + tab.getHeight()));
					}else {
						batch.draw(middleR, getX() + x, getY() - (y + tab.getHeight()));
					}
				}
				if(y == 0 && x == w-48) break;
			}
		}
		wrapper.draw(batch, parentAlpha);
			
	}
	protected void drawLeft(SpriteBatch batch, float parentAlpha) {
		batch.draw(corner1R, (getX()+ w-16) - tab.getWidth(), getY() + h-16);
		batch.draw(corner2R, (getX() + w-16) - tab.getWidth(), getY());
		for(int y = 0; y < h - 16; y+=16) {
			for(int x = 0; x < (y==0?w-16:w); x+=16) {
				if(y == 0) {
					batch.draw(edge3R, getX() + x, getY() + y);
				}else if(y != 0) {
					if(x == 0) {
						batch.draw(edge1R, getX() + x, getY() + (y + tab.getHeight()));
					}else if(x == w-16) {
						batch.draw(edge3R, getX() + x, getY() + (y + tab.getHeight()));
					}else {
						batch.draw(middleR, getX() + x, getY() + (y + tab.getHeight()));
					}
				}
				if(y == 0 && x == w-48) break;
			}
		}
		wrapper.draw(batch, parentAlpha);
	}
	protected void drawRight() {
		
	}
	
}
