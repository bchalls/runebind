package com.ravine.runebind;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.ravine.runebind.screens.GameScreen;
import com.ravine.runebind.screens.MenuScreen;
import com.ravine.runebind.screens.SplashScreen;

public class RuneBind extends Game {
	
	public static final String LOG = RuneBind.class.getSimpleName();
	
	public static final boolean DEV_MODE = true;
	
	public static SpriteBatch batch;
	
	protected FPSLogger fpsLogger;
	
	public SplashScreen getSplashScreen() {
		return new SplashScreen(this);
	}
	
	@Override
	public void create() {	
		Gdx.app.log(LOG, "Creating game on: " + Gdx.app.getType());
		
		fpsLogger = new FPSLogger();
		
		batch = new SpriteBatch();
		
		if(!DEV_MODE) {
			setScreen(getSplashScreen());
		} else {
			setScreen(new GameScreen(this));
		}
	}

	@Override
	public void dispose() {
		super.dispose();
		
	}

	@Override
	public void render() {		
		super.render();
		
		fpsLogger.log();
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
		Gdx.app.log(LOG, "Resizing screen to: " + width + " x " + height);
		
		if( getScreen() == null)
		{
			if(!DEV_MODE)
				setScreen(new SplashScreen(this));
			else
				setScreen(new MenuScreen(this));
		}
	}

	@Override
	public void pause() {
		super.pause();
	}

	@Override
	public void resume() {
		super.resume();
	}
}
