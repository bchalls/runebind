package com.ravine.runebind.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
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
		this.stage = new Stage(800, 600, true);
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
		
		//stage.setViewport(viewport.width, viewport.height, true);
		
				
		stage.draw();

	}

	@Override
	public void resize(int width, int height) {
		float aspectRatio = (float)width/(float)height;
        float scale = 1f;
        Vector2 crop = new Vector2(0f, 0f);
        
        if(aspectRatio > vAspect)
        {
            scale = (float)height/(float)vHeight;
            crop.x = (width - vWidth*scale)/2f;
        }
        else if(aspectRatio < vAspect)
        {
            scale = (float)width/(float)vWidth;
            crop.y = (height - vHeight*scale)/2f;
        }
        else
        {
            scale = (float)width/(float)vWidth;
        }

        float w = (float)vWidth*scale;
        float h = (float)vHeight*scale;
        viewport = new Rectangle(0, 0, w, h);
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
