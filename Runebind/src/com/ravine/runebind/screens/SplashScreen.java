package com.ravine.runebind.screens;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.delay;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeIn;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeOut;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.utils.*;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Scaling;
import com.ravine.runebind.RuneBind;

public class SplashScreen extends AbstractScreen {

	private Image splashImage;
	
	public SplashScreen(RuneBind game) {
		super(game);		
	}

	@Override
	public void show() {
		super.show();
		
		Texture splashTexture = new Texture(Gdx.files.internal("data/Splash.png"));
		Drawable splashDrawable = new TextureRegionDrawable(new TextureRegion(splashTexture));
		
		splashImage = new Image(splashDrawable, Scaling.stretch);
		splashImage.setFillParent(true);
		
		splashImage.getColor().a = 0.0f;
		splashImage.addAction( sequence(fadeIn(0.75f), delay(2.0f), fadeOut(0.75f), 
				new Action() {
					@Override
					public boolean act( float delta ){
						game.setScreen(new MenuScreen( game ));
						return true;
					}					
		}));
		
		stage.addActor(splashImage);

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		super.dispose();
	}

}
