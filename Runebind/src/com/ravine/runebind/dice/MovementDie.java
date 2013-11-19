package com.ravine.runebind.dice;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.ravine.runebind.board.BoardTile.TileType;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: Brandon
 * Date: 8/18/13
 * Time: 2:22 PM
 */
public class MovementDie extends Die {

    private List<List<TileType>> terrainTypes;

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
        terrainTypes = new ArrayList<List<TileType>>();
        addSides();
    }

    private void addSides() {
        for(List<TileType> i : terrainTypes)
            i = new ArrayList<TileType>();
        terrainTypes.get(0).add(TileType.river);
        terrainTypes.get(0).add(TileType.road);
        terrainTypes[1].add(TileType.mountains);
        terrainTypes[1].add(TileType.road);
        terrainTypes[1].add(TileType.plains);
        terrainTypes[2].add(TileType.hills);
        terrainTypes[2].add(TileType.plains);
        terrainTypes[2].add(TileType.road);
        terrainTypes[3].add(TileType.hills);
        terrainTypes[3].add(TileType.plains);
        terrainTypes[3].add(TileType.road);
        terrainTypes[4].add(TileType.river);
        terrainTypes[4].add(TileType.forest);
        terrainTypes[5].add(TileType.river);
        terrainTypes[5].add(TileType.swamp);
    }

    @Override
    public int roll() {
        rand.setSeed(System.nanoTime());
        curSide = rand.nextInt(6);
        return curSide;
    }

    public ArrayList<TileType> getCurSideTypes() {
        return terrainTypes[curSide];
    }

    @Override
    public void setNumSides(int numSides) {
        this.numSides = numSides;
    }
}
