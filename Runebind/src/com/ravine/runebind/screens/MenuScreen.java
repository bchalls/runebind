package com.ravine.runebind.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Scaling;
import com.ravine.runebind.RuneBind;

public class MenuScreen extends AbstractScreen {

	private Image bgImage;
	private Drawable[][] buttonDraws;
	private Button[] buttons;

	
	public MenuScreen(RuneBind game) {
		super(game);
	}
	
	@Override
	public void show() {
		super.show();
		
		Texture tempTex = new Texture(Gdx.files.internal("data/menuBG.png"));
		Drawable tempDrawable = new TextureRegionDrawable(new TextureRegion(tempTex));
		
		bgImage = new Image(tempDrawable, Scaling.stretch);
		bgImage.setFillParent(true);
		stage.addActor(bgImage);
		
		buttonDraws = new Drawable[3][2];
		tempTex = new Texture(Gdx.files.internal("data/menuButtons.png"));
		for( int y = 0; y < 3; y++ ) {
			for( int x = 0; x < 2; x++) {
				buttonDraws[y][x] = new TextureRegionDrawable(new TextureRegion(tempTex, x*300, y*100, 300, 100));
			}
		}
		
		buttons = new Button[3];
		for( int i = 0; i < 3; i++) {
			buttons[i] = new Button(buttonDraws[i][0], buttonDraws[i][1]);
			buttons[i].setPosition(stage.getWidth()/2 - 150, stage.getHeight()/2 - (i*100));
		}
		buttons[0].addListener(new InputListener() {
			
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				buttons[0].toggle();
				Gdx.app.log(RuneBind.LOG, "button clicked");
				game.setScreen(new GameScreen(game));
			}
			
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				buttons[0].toggle();
				Gdx.app.log(RuneBind.LOG, "button clicked");
				return true;
			}
		} );
		stage.addActor(buttons[0]);
		stage.addActor(buttons[1]);
		stage.addActor(buttons[2]);
	}
}
