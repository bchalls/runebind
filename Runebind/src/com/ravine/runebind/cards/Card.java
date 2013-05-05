package com.ravine.runebind.cards;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFontCache;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Card extends Actor {
	
	public static enum Type {
		item,
		ally,
		enemy,
		event,
		encounter,
		hero
	};
	public static enum Face {
		front,
		back
	};
	
	protected Type type;
	protected TextureRegion cardFrontR, cardBackR, contentR;
	protected BitmapFont font;
	protected BitmapFontCache staticText;
	protected String cardText;
	protected Face face;
	protected Vector2[] textTargets;
	
	public Card(Type type, int x, int y, int imageX, int imageY, String text) {
		this.type = type;
		face = Face.back;
		setX(x);
		setY(y);
		font = new BitmapFont(Gdx.files.internal("data/font.fnt"), false);
		Texture tex = new Texture(Gdx.files.internal("data/MarketCardBack.png"));
		if(type.equals(Type.item) || type.equals(Type.ally)) {
			cardBackR = new TextureRegion(tex, 0, 0, 322, 500);
		}
		staticText = new BitmapFontCache(font);
		staticText.addMultiLineText(text, x, y);
		
		
	}
	
	public Face getFace() { return face; }
	public void flipImage() { if(face.equals(Face.front)) { face = Face.back; } else { face = Face.front; } }
	
	public Type getType() { return type; }
	

}
