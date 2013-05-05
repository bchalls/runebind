package com.ravine.runebind.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.ravine.runebind.RuneBind;

public abstract class AbstractScreen implements Screen {

	protected final RuneBind game;
	protected final Stage stage;
	protected OrthographicCamera camera;
	protected Rectangle viewport;
	
	protected static final int STAGE_WIDTH = 1392, STAGE_HEIGHT = 960;
	protected int vWidth = 800, vHeight = 600;
	protected float vAspect = (float) vWidth/(float) vHeight;
	protected float zoom = 1.0f;
	
	public AbstractScreen(RuneBind game) {
		this.game = game;
		viewport = new Rectangle(0, 0, vWidth, vHeight);
		this.stage = new Stage(STAGE_WIDTH, STAGE_HEIGHT, true);
		Gdx.input.setInputProcessor(stage);
	}
	
	public float getZoom() { return zoom; }
	public void setZoom(float val) { zoom = val; }
	public void changeZoom(float val) { zoom += val; }
	
	@Override
	public void render(float delta) {
		stage.act(delta);
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		stage.setViewport(viewport.width*zoom, viewport.height*zoom, true);
		
				
		stage.draw();

	}

	@Override
	public void resize(int width, int height) {
		
	}

	@Override
	public void show() {
	}

	@Override
	public void hide() {
		stage.clear();
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
	}

}
