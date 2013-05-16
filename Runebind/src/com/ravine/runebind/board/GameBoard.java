package com.ravine.runebind.board;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.ravine.runebind.RuneBind;
import com.ravine.runebind.board.BoardTile.TileType;

public class GameBoard extends Group{

	private BoardTile tiles[];
	private float velX, velY;
	private boolean flinging;
	private Step step;
	private float width = 0, height = 0;
	
	public enum Step {
		movement,
		adventure,
		market,
		experience
	};
	
	public GameBoard( String levelFile) {				
		step = Step.movement;
		
		flinging = false;
		velX = 0.0f;
		velY = 0.0f;
		
		tiles = new BoardTile[182];
		
		setUpBoard(levelFile);
		
		registerNeighbors();
		
		setWidth(width+96);
		setHeight(height+96);
		Gdx.app.log(RuneBind.LOG, "width: " + getWidth() + ", height: " + getHeight());
		
		this.addListener(new ActorGestureListener(20, 0.4f, 1.1f, 0.0005f) {
			@Override
			public void zoom(InputEvent event, float initialDistance, float distance) {
				float ratio = distance/initialDistance;
				if((getWidth()*ratio >= getStage().getWidth()) && 
						(getHeight()*ratio) >= getStage().getHeight()) {
					if(!(Math.abs(getScaleX()-ratio) > 1.0))
					{
						setScale(ratio);
					}
				}
				
				Gdx.app.log(RuneBind.LOG, "ratio:" + getScaleX());
			}
			@Override
			public void touchDown(InputEvent event, float x, float y, int pointer, int button) {
				flinging = false;
				if(!step.equals(Step.movement))
					event.stop();
			}
			@Override
			public void fling(InputEvent event, float velocityX, float velocityY, int button) {
				if(Math.abs(velocityX) > 50.0f || Math.abs(velocityY) > 50.0f) {
					flinging = true;
					velX = velocityX;
					velY = velocityY;
					Gdx.app.log(RuneBind.LOG, "velX: " + velX + ", velY: " + velY);
				}
			}
			@Override
			public void pan(InputEvent event, float x, float y, float deltaX, float deltaY) {
				translate(deltaX, deltaY);
				checkBounds();
			}
		});
	}

	private void setUpBoard(String levelFile) {
		FileHandle board = Gdx.files.internal(levelFile);
		String[] lines = board.readString().split("\n");
		int curTile = 0;
		int yOffset = 0;
		int curLevel = 0, offset = 0;
		TileType curType = TileType.plains;
		
		for(int x = 0; x < lines.length; x++) {
			String curLine = lines[x];
			yOffset = (x % 2 != 0) ? 0 : 48;
			offset = 0;
			for(int y = 0; y < curLine.length(); y++) {				
				if(curLine.charAt(y) == 'F') {
					curType = TileType.forest;
				}
				else if(curLine.charAt(y) == 'H') {
					curType = TileType.hills;
				}
				else if(curLine.charAt(y) == 'M') {
					curType = TileType.mountains;
				}
				else if(curLine.charAt(y) == 'S') {
					curType = TileType.swamp;
				}
				else if(curLine.charAt(y) == 'R') {
					curType = TileType.road;
				}
				else if(curLine.charAt(y) == 'W') {
					curType = TileType.river;
				}
				else if(curLine.charAt(y) == 'T') {
					curType = TileType.town;
				}
				else if(curLine.charAt(y) == 'P') {
					curType = TileType.plains;
				}
				if(!(curLine.charAt(y) == '1' || curLine.charAt(y) == '2' || curLine.charAt(y) == '3' || curLine.charAt(y) == '4')) {
					curTile = (x*14)+(y-offset);
					tiles[curTile] = new BoardTile(curType, (y-offset)*96 + yOffset, (x*72), curLevel, y-offset,  x);
					Gdx.app.log(RuneBind.LOG, "pX: " + (y-offset) + ", pY: " + x);
					curLevel = 0;
					this.addActor(tiles[curTile]);
					width = tiles[curTile].getX() > width ? tiles[curTile].getX():width;
					height = tiles[curTile].getY() > height ? tiles[curTile].getY():height;
					if((y-offset) == 13) break;
				} else {
					curLevel = Integer.parseInt(String.valueOf(curLine.charAt(y)));
					offset++;
				}
			}
		}
	}
	
	private void registerNeighbors() {
		for(int i = 0; i < tiles.length; i++) {
			BoardTile[] curNeigh = new BoardTile[6];
			
			if(tiles[i].getPosY()%2 == 0) {
				if(tiles[i].getPosY() == 12) {
					if(tiles[i].getPosX() == 0) {
						curNeigh[0] = null;
						curNeigh[3] = tiles[i+1];
						curNeigh[4] = tiles[i-13];
					}else if(tiles[i].getPosX() == 13) {
						curNeigh[0] = tiles[i-1];
						curNeigh[3] = null;
						curNeigh[4] = null;
					} else {
						curNeigh[0] = tiles[i-1];
						curNeigh[3] = tiles[i+1];
						curNeigh[4] = tiles[i-13];
					}
					curNeigh[1] = null;
					curNeigh[2] = null;
					curNeigh[5] = tiles[i-14];
				} else if(tiles[i].getPosY() == 0){
					if(tiles[i].getPosX() == 0) {
						curNeigh[0] = null;
						curNeigh[2] = tiles[i+15];
						curNeigh[3] = tiles[i+1];
					}else if(tiles[i].getPosX() == 13) {
						curNeigh[0] = tiles[i-1];
						curNeigh[2] = null;
						curNeigh[3] = null;
					} else {
						curNeigh[0] = tiles[i-1];
						curNeigh[2] = tiles[i+15];
						curNeigh[3] = tiles[i+1];
					}
					curNeigh[1] = tiles[i+14];
					curNeigh[4] = null;
					curNeigh[5] = null;					
				} else {
					if(tiles[i].getPosX() == 0) {
						curNeigh[0] = null;
						curNeigh[2] = tiles[i+15];
						curNeigh[3] = tiles[i+1];
						curNeigh[4] = tiles[i-13];
					}else if(tiles[i].getPosX() == 13) {
						curNeigh[0] = tiles[i-1];
						curNeigh[2] = null;
						curNeigh[3] = null;
						curNeigh[4] = null;
					} else {
						curNeigh[0] = tiles[i-1];
						curNeigh[2] = tiles[i+15];
						curNeigh[3] = tiles[i+1];
						curNeigh[4] = tiles[i-13];
					}
					curNeigh[1] = tiles[i+14];
					curNeigh[5] = tiles[i-14];
				}
				
			}else {
				if(tiles[i].getPosX() == 0) {
					curNeigh[0] = null;
					curNeigh[1] = null;
					curNeigh[2] = tiles[i+14];
					curNeigh[3] = tiles[i+1];
					curNeigh[4] = tiles[i-14];
					curNeigh[5] = null;					
				}else if(tiles[i].getPosX() == 13) {
					curNeigh[0] = tiles[i-1];
					curNeigh[1] = tiles[i+13];
					curNeigh[2] = tiles[i+14];
					curNeigh[3] = null;
					curNeigh[4] = tiles[i-14];
					curNeigh[5] = tiles[i-15];	
				} else {
					curNeigh[0] = tiles[i-1];
					curNeigh[1] = tiles[i+13];
					curNeigh[2] = tiles[i+14];
					curNeigh[3] = tiles[i+1];
					curNeigh[4] = tiles[i-14];
					curNeigh[5] = tiles[i-15];
				}
			}
			tiles[i].setNeighbors(curNeigh);
		}
	}
	
	private void checkBounds() {
		if(getX() > 0) setX(0);
		if(getY() > 0) setY(0);
		if(getX() < (getStage().getWidth()-(getWidth()*getScaleX()))) setX(getStage().getWidth()-(getWidth()*getScaleX()));
		if(getY() < (getStage().getHeight()-(getHeight()*getScaleY()))) setY(getStage().getHeight()-(getHeight()*getScaleY()));
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
		if(flinging) {
			velX *= 0.92f;
			velY *= 0.92f;
			this.translate(velX * delta, velY * delta);
			if(Math.abs(velX) < 0.01f) velX = 0;
			if(Math.abs(velY) < 0.01f) velY = 0;
			//if( velX == 0 && velY == 0) flinging = false;
		}
		checkBounds();
	}
}
