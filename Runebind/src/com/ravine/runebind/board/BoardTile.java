package com.ravine.runebind.board;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Actor;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.alpha;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.color;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.forever;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.ravine.runebind.RuneBind;

public class BoardTile extends Actor {
	
	public static enum TileType
	{
		plains,
		forest,
		hills,
		mountains,
		river,
		road,
		swamp,
		town
	}
	
	private TextureRegion typeRegion, hexRegion, hexRDark, hexRLight, adventureRegion;
	private TileType type;
	private boolean hasAdventure, adventureDone;
	private int adventureLevel;
	
	public BoardTile(TileType type, float x, float y, int advLevel)
	{
		super();
		this.type = type;
		this.setX(x);
		this.setY(y);
		this.setColor(Color.BLACK);
		//shapeRenderer = new ShapeRenderer();
		adventureLevel = advLevel;
		hasAdventure = adventureLevel>0 ? true:false;
		adventureDone = false;
		
		
		Texture terTex = new Texture(Gdx.files.internal("data/HexTerrain.png"));
		Texture hexTex = new Texture(Gdx.files.internal("data/Hex.png"));
		Texture hexLight = new Texture(Gdx.files.internal("data/HexLight.png"));
		hexRDark = new TextureRegion(hexTex);
		hexRLight = new TextureRegion(hexLight);
		hexRegion = hexRDark;
		
		if(hasAdventure) {
			Texture advTokenTex = new Texture(Gdx.files.internal("data/AdventureCounters.png"));
			adventureRegion = new TextureRegion(advTokenTex, 64*(adventureLevel-1), 0, 64, 64);
		}
		
		
		this.setBounds(x, y, 96, 96);
		switch(this.type)
		{
			case plains:
				typeRegion = new TextureRegion(terTex, 288, 0, 96, 96);
				break;
			case forest:
				typeRegion = new TextureRegion(terTex, 576, 0, 96, 96);
				break;
			case hills:
				typeRegion = new TextureRegion(terTex, 192, 0, 96, 96);
				break;
			case mountains:
				typeRegion = new TextureRegion(terTex, 480, 0, 96, 96);
				break;
			case river:
				typeRegion = new TextureRegion(terTex, 96, 0, 96, 96);
				break;
			case road:
				typeRegion = new TextureRegion(terTex, 0, 0, 96, 96);
				break;
			case swamp:
				typeRegion = new TextureRegion(terTex, 384, 0, 96, 96);
				break;
			case town:
				typeRegion = new TextureRegion(terTex, 672, 0, 96, 96);
				break;
		}		
		this.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				Gdx.app.log(RuneBind.LOG, "Tile down");
				hexRegion = hexRLight;				
				return true;
			}
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				Gdx.app.log(RuneBind.LOG, "Tile Up");
				hexRegion = hexRDark;				
			}
			
		});
	}

	public boolean getHasAdventure() { return (hasAdventure && !adventureDone); } 
	
	public void draw(SpriteBatch batch, float parentAlpha) {
		batch.draw(typeRegion, getX(), getY());
		batch.draw(hexRegion, getX(), getY());
		if(hasAdventure && !adventureDone) {
			batch.draw(adventureRegion, getX()+16, getY()+16);
		}
	}
	
}