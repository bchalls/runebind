package com.ravine.runebind.cards;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFontCache;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.action;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.scaleTo;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.ArrayList;

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
	protected String cardText, name;
	protected Face face;
    protected boolean activated;
	protected Vector2[] textTargets;
	private float initScaleX;
    protected ArrayList<Effect> effectList;
	
	public Card(Type type, int x, int y, String text, String name) {
		this.type = type;
		face = Face.back;
		setX(x);
		setY(y);
		font = new BitmapFont(Gdx.files.internal("data/font.fnt"), false);
		staticText = new BitmapFontCache(font);
		cardText = text;
		initScaleX = getScaleX();
        this.name = name;
        effectList = new ArrayList<Effect>();
        activated = false;
	}

    public void addEffect(Effect effect) {
        effectList.add(effect);
    }

	public Face getFace() { return face; }
	public void flip() { if(face.equals(Face.front)) { face = Face.back; } else { face = Face.front; } }
	public void flipCard() {
		initScaleX = getScaleX();
		addAction( sequence(scaleTo(0f,getScaleY(), 0.15f), 
				new Action() {
					@Override
					public boolean act( float delta ){
						flip();
						return true;
					}					
		}, scaleTo(initScaleX, getScaleY(), 0.15f)));
	}
	
	public Type getType() { return type; }

    public void refresh() { activated = false; }

    @Override
    public String toString() {
        return (name + " of " + type.toString() + "type");
    }

}
