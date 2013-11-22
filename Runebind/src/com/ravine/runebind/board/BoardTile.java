package com.ravine.runebind.board;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.ravine.runebind.RuneBind;
import com.ravine.runebind.dice.MovementDie;
import com.ravine.runebind.entity.Player;
import com.ravine.runebind.turn.TurnManager.Step;

import java.util.ArrayList;

/*
TODO: make a blue line for "through" tile path and a dot for "end" path
TODO: make path algorithm:
            board will get list of dice from dice manager
            board will send listDice to curPlayer.curTile
            for each die
                for each neighbor
                    tileType = dieType
                        add die to "used" queue/list
                        remove from listDice
                        highlight tile
                        if new list not empty then "through" else "end"
                        send new listDice to foundTile
                            for each die ... etc



 */

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
	private Polygon hex;
	private int posX, posY;
	private BoardTile[] neighbors;
	private boolean lite;
    private ArrayList<Player> players;
	
	public BoardTile(TileType type, float x, float y, int advLevel, int pX, int pY)
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
		lite = false;
		neighbors = new BoardTile[0];
		
		posX = pX;
		posY = pY;

        players  = new ArrayList<Player>();
		
		//Vector2[] vecList = {new Vector2(0f,24f),new Vector2(0f,72f),new Vector2(48f,96f),
		//		new Vector2(96f,72f),new Vector2(96f,24f),new Vector2(48f,0f)}; 
		
		hex = new Polygon(new float[]{0f,24f,
				0f,72f,
				48f,96f,
				96f,72f,
				96f,24f,
				48f,0f,
				0f, 24f});
		
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
				if(pointer == 0) {
					Gdx.app.log(RuneBind.LOG, "Tile down. pX: " + posX + ", pY: " + posY);
					/*toggleLight();
					for(int i = 0; i < neighbors.length; i++) {
						if(neighbors[i] != null) {
							neighbors[i].toggleLight();
						}
					} */
					return true;
				}
				return false;
			}
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				Gdx.app.log(RuneBind.LOG, "Tile Up");

                if(((GameBoard) getParent()).getTurnManager().getCurStep().equals(Step.movement))
                {
                    if(lite) {
                        ((GameBoard) getParent()).clearBoardHighlight();
                        ((GameBoard) getParent()).movePlayer(((GameBoard) getParent()).getCurPlayer(),get());
                    }

                }
                /*toggleLight();
				for(int i = 0; i < neighbors.length; i++) {
					if(neighbors[i] != null) {
						neighbors[i].toggleLight();
					}
				}  */
			}
			
		});
	}

    public void addPlayer(Player player)
    {
        players.add(player);
    }
    public void removePlayer(Player player)
    {
        players.remove(player);
    }

	@Override
	public Actor hit(float x, float y, boolean touchable) {
		if (touchable && this.getTouchable() != Touchable.enabled) return null;
		return hex.contains(x, y)?this:null;
	}
	
	/*
	 * Sets tile's neighbors from 0-5. Starting from left and moving clockwise
	 */
	public void setNeighbors(BoardTile[] neighbors) {
		this.neighbors = neighbors;
	}

    public BoardTile[] getNeighbors() {
        return neighbors;
    }
	
	public void toggleLight() {
		if(lite) {
			hexRegion = hexRDark;
		} else {
			hexRegion = hexRLight;
		}
		lite = !lite;
	}

    public void setLight(boolean tf) {
        lite = tf;
        if(lite) {
            hexRegion = hexRLight;
        } else {
            hexRegion = hexRDark;
        }
    }

    private BoardTile get()
    {
        return  this;
    }

    public TileType getType() {
        return type;
    }

	public int getPosX() { return posX; }
	public int getPosY() { return posY; }
	
	public boolean getHasAdventure() { return (hasAdventure && !adventureDone); } 
	
	public void draw(SpriteBatch batch, float parentAlpha) {
		batch.draw(typeRegion, getX(), getY());
		batch.draw(hexRegion, getX(), getY());
		if(hasAdventure && !adventureDone) {
			batch.draw(adventureRegion, getX()+16, getY()+16);
		}
	}
 /*
    board will get list of dice from dice manager
    board will send listDice to curPlayer.curTile
    for each die
        for each typeOnDie
            for each neighbor
                tileType = curType
                    add die to "used" queue/list
                    remove from listDice
                    highlight tile
                    if new list not empty then "through" else "end"
                    send new listDice to foundTile
                    for each die ... etc
    */
    public void makePath(ArrayList<MovementDie> diceList, BoardTile startTile) {
        ArrayList<MovementDie> usedDice = new ArrayList<MovementDie>();
        ArrayList<MovementDie> newList = new ArrayList<MovementDie>(diceList);
        for(MovementDie die : diceList) {
            for(TileType t : die.getCurSideTypes()) {
                for(BoardTile tile : getNeighbors())
                    if(tile != null && !tile.equals(startTile)) {
                        if(tile.getType().equals(t) || tile.getType().equals(TileType.town)) {
                            usedDice.add(die);
                            newList.remove(die);
                            tile.setLight(true);
                            tile.makePath(newList, startTile);
                        }
                    }
            }
        }


    }
	
}
