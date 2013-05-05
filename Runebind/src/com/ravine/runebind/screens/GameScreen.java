package com.ravine.runebind.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.ravine.runebind.RuneBind;
import com.ravine.runebind.board.GameBoard;
import com.ravine.runebind.board.Pullout;

public class GameScreen extends AbstractScreen {
	
	private GameBoard board;
	private Pullout topPullout, bottomPullout, leftPullout, rightPullout;
	//private BoardController boardController;
	//private InputMultiplexer inputM;
	
	public GameScreen(RuneBind game) {
		super(game);
		board = new GameBoard("data/Map.txt");
		//boardController = new BoardController(board);
		stage.addListener(new InputListener() {
			@Override
			public boolean keyUp(InputEvent event, int keycode) {
				if(keycode == Input.Keys.UP) {
					board.translate(0, -5);
					Gdx.app.log(RuneBind.LOG, "Camera Up");
				}
				if(keycode == Input.Keys.DOWN) {
					board.translate(0, 5);
					Gdx.app.log(RuneBind.LOG, "Camera Down");
				}
				if(keycode == Input.Keys.LEFT) {
					board.translate(5, 0);
					Gdx.app.log(RuneBind.LOG, "Camera Left");;
				}
				if(keycode == Input.Keys.RIGHT) {
					board.translate(-5, 0);
					Gdx.app.log(RuneBind.LOG, "Camera Right");
				}
				if(keycode == Input.Keys.X && zoom > .1) {
					//zoom -= .1f;
					board.setScale(board.getScaleX()-0.1f, board.getScaleY()-0.1f);
					Gdx.app.log(RuneBind.LOG, "zoom++");
				}
				if(keycode == Input.Keys.Z && zoom < 3.0) {
					//zoom += .1f;
					board.setScale(board.getScaleX()+0.1f, board.getScaleY()+0.1f);
					Gdx.app.log(RuneBind.LOG, "zoom--");
				}
				return true;
			}
		});
		//Gdx.input.setInputProcessor(defaultControl);
		topPullout = new Pullout(Pullout.Location.top, 800, 400, 0, 500);
		bottomPullout = new Pullout(Pullout.Location.bottom, 800, 400, 0, 0);
		leftPullout = new Pullout(Pullout.Location.left, 400, 800, 0, -100);
	}
	
	public GameBoard getBoard() { return board; }
	
	@Override
	public void show() {
		super.show();
		stage.addActor(board);	
		stage.addActor(topPullout);
		stage.addActor(bottomPullout);
		stage.addActor(leftPullout);
	}
	
	@Override
	public void resize(int width, int height) {
			
	}
}
