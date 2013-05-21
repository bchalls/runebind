package com.ravine.runebind.cards;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.ravine.runebind.cards.Card.Face;

public class ItemCard extends Card {
	
	// 0 = activate, 1 = discard, 2 = weapon
	private int activationType;
	private TextureRegion activationR;
	private int value;
	

	public ItemCard(int actType) {
		super(Type.item, 100, 20, "");
		activationType = actType;
		Texture tex = new Texture(Gdx.files.internal("data/MarketCardBack.png"));
		cardBackR = new TextureRegion(tex, 0, 0, 322, 500);
		tex = new Texture(Gdx.files.internal("data/ItemCardFront.png"));
		cardFrontR = new TextureRegion(tex, 0, 0, 322, 500);
		setWidth(cardFrontR.getRegionWidth());
		setHeight(cardFrontR.getRegionHeight());
		tex = new Texture(Gdx.files.internal("data/Item1.png"));
		contentR = new TextureRegion(tex, 0, 0, 315, 259);
		setOrigin(getWidth()/2, getOriginY());
		tex = new Texture(Gdx.files.internal("data/ActivationTypes.png"));
		activationR = new TextureRegion(tex, 50*activationType, 0, 50, 50);
		//setScale(0.5f);
	}
	
	public void draw(SpriteBatch batch, float parentAlpha) {
		if(face.equals(Face.front)) {
			batch.draw(contentR, getX(), getY()+(215*getScaleY()), getOriginX(), getOriginY(), contentR.getRegionWidth(), contentR.getRegionHeight(), getScaleX(), getScaleY(), getRotation());
			batch.draw(cardFrontR, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
			batch.draw(activationR, getX()+(5*getScaleX()), getY()+(205*getScaleY()), getOriginX(), getOriginY(), activationR.getRegionWidth(), activationR.getRegionHeight(), getScaleX(), getScaleY(), getRotation());
		} else {
			batch.draw(cardBackR, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
		}
	}
}
