package com.ravine.runebind.dice;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: Brandon
 * Date: 8/18/13
 * Time: 1:48 PM
 */
public abstract class Die {

    protected int numSides;
    protected Texture image;
    protected TextureRegion[] sideImage;
    protected int x, y, curSide;
    protected Random rand;


    public abstract int roll();
    public abstract void setNumSides(int numSides);


}
