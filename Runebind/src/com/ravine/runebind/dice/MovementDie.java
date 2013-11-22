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
        terrainTypes.add(new ArrayList<TileType>());
        terrainTypes.add(new ArrayList<TileType>());
        terrainTypes.add(new ArrayList<TileType>());
        terrainTypes.add(new ArrayList<TileType>());
        terrainTypes.add(new ArrayList<TileType>());
        terrainTypes.add(new ArrayList<TileType>());
        addSides();
    }

    private void addSides() {
        terrainTypes.get(0).add(TileType.river);
        terrainTypes.get(0).add(TileType.road);
        terrainTypes.get(1).add(TileType.mountains);
        terrainTypes.get(1).add(TileType.road);
        terrainTypes.get(1).add(TileType.plains);
        terrainTypes.get(2).add(TileType.hills);
        terrainTypes.get(2).add(TileType.plains);
        terrainTypes.get(2).add(TileType.road);
        terrainTypes.get(3).add(TileType.hills);
        terrainTypes.get(3).add(TileType.plains);
        terrainTypes.get(3).add(TileType.road);
        terrainTypes.get(4).add(TileType.river);
        terrainTypes.get(4).add(TileType.forest);
        terrainTypes.get(5).add(TileType.river);
        terrainTypes.get(5).add(TileType.swamp);
    }

    @Override
    public int roll() {
        rand.setSeed(System.nanoTime());
        curSide = rand.nextInt(6);
        return curSide;
    }

    public ArrayList<TileType> getCurSideTypes() {
        return (ArrayList<TileType>) terrainTypes.get(curSide);
    }

    @Override
    public String toString() {
        return "Current side: " + curSide + ", Types: " + terrainTypes.get(curSide);
    }

    @Override
    public void setNumSides(int numSides) {
        this.numSides = numSides;
    }
}
