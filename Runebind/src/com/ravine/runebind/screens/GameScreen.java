package com.ravine.runebind.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.ravine.runebind.RuneBind;
import com.ravine.runebind.board.*;
import com.ravine.runebind.cards.ItemCard;
import com.ravine.runebind.entity.Player;

import java.util.ArrayList;

public final class GameScreen extends AbstractScreen {
	
	private GameBoard board;
	private PlayerTab playerTab;
	private MarketTab marketTab;
	private AdventureTab adventureTab;
	private QuestTab questTab;
    private ArrayList<Player> playerArrayList;
    private Player player1;
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
					Gdx.app.log(RuneBind.LOG, "Camera Left");
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
				if(keycode == Input.Keys.F) {

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
		adventureTab = new AdventureTab(stage.getHeight());
		playerTab = new PlayerTab();
		marketTab = new MarketTab();
		questTab = new QuestTab(stage.getWidth(), 0);
        player1 = new Player("Varikas the Dead", 4, 4, 0, 0, 1, board);
	}
	
	public GameBoard getBoard() { return board; }
	
	@Override
	public void show() {
		super.show();

		stage.addActor(board);	
		stage.addActor(adventureTab);
		stage.addActor(playerTab);
		stage.addActor(marketTab);
		stage.addActor(questTab);
		adventureTab.addToStage();
		playerTab.addToStage();
		marketTab.addToStage();
		questTab.addToStage();
        stage.addActor(player1);
        /*for(Player cur: playerArrayList) {
            stage.addActor(cur);
        }*/
	}


	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
		//topPullout.setY(stage.getCamera().viewportHeight);
		//rightPullout.setX(stage.getWidth());
	}
}
