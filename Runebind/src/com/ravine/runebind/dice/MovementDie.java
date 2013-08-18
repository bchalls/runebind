package com.ravine.runebind.dice;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: Brandon
 * Date: 8/18/13
 * Time: 2:22 PM
 */
public class MovementDie extends Die {

    public MovementDie()
    {
        image = new Texture(Gdx.files.internal("data/Terrain.png"));
        numSides = 6;
        sideImage = new TextureRegion[numSides];
        for(int i = 0; i < numSides; i++)
        {
            sideImage[i] = new TextureRegion(image, 64*i, 0);
        }
        rand = new Random(System.nanoTime());
        curSide = rand.nextInt(6);
    }

    @Override
    public int roll() {
        rand.setSeed(System.nanoTime());
        curSide = rand.nextInt(6);
        return curSide;
    }

    @Override
    public void setNumSides(int numSides) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
