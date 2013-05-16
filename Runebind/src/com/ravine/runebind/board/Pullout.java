package com.ravine.runebind.board;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
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
	protected int w;
	protected int h;
	protected TextureRegion corner1R;
	protected TextureRegion corner2R;
	protected TextureRegion edge1R;
	protected TextureRegion edge2R;
	protected TextureRegion edge3R;
	protected TextureRegion middleR;
	private TextureRegionDrawable tabRUp, tabRDown;
	private Button tab;
	protected Table wrapper;
	private boolean isOut = false;
	protected Vector2 inVec, outVec;
	
	
	
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
		}else if(loc.equals(Location.left)) { 
			wrapper.setRotation(90);
		}
		else if(loc.equals(Location.right)) { 
			wrapper.setRotation(-90);
		}
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
						toFront();
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
	
	public boolean isOut() { return isOut; }
	
	public void act(float delta) {
		super.act(delta);
	}
}
