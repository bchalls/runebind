package com.ravine.runebind.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.ravine.runebind.board.GameBoard;


public class Player extends Actor{
    private int tokenPosX, tokenPosY;
    private String characterName;
    private int curExp;
    private int maxHP, curDmg, maxFat, curFat;
    private int sJump, sSwim, sSneak, sClimb, sDiplomacy, sResist, sHide, sTinker;
    private TextureRegion portraitR, tokenR;
    private float tokenX, tokenY;
    private int playerNumber;
    private GameBoard board;
    private int gold;

    public Player(String name, int hp, int fat, int portX, int portY, int playerNumber, GameBoard board) {
        characterName = name;
        this.board = board;
        curExp = 0;
        maxHP = hp;
        curDmg = 0;
        maxFat = fat;
        curFat = 0;
        gold = 3;
        sJump = 0; sSneak = 0; sSwim = 0; sClimb = 0; sDiplomacy = 0; sResist = 0; sHide = 0; sTinker = 0;
        this.playerNumber = playerNumber;
        Texture tex = new Texture(Gdx.files.internal("data/portraits.png"));
        portraitR = new TextureRegion(tex, portX*256, portY*256, 256, 256);
        tex = new Texture(Gdx.files.internal("data/playerTokens.png"));
        tokenR = new TextureRegion(tex, portX*64, portY*64, 64, 64);
        tokenPosX = 0; tokenPosY = 0;
        tokenX = board.getTile(tokenPosX, tokenPosY).getX();
        tokenY = board.getTile(tokenPosX, tokenPosY).getY();
    }

    public void draw(SpriteBatch batch, float parentAlpha) {
            batch.draw(tokenR,tokenX + 16, tokenY + 16);
    }

    public void act(float delta) {
        super.act(delta);
        tokenX = board.getTile(tokenPosX, tokenPosY).getX();
        tokenY = board.getTile(tokenPosX, tokenPosY).getY();
    }

    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    public int getCurExp() {
        return curExp;
    }

    public void setCurExp(int curExp) {
        this.curExp = curExp;
    }

    public int getMaxHP() {
        return maxHP;
    }

    public void setMaxHP(int maxHP) {
        this.maxHP = maxHP;
    }

    public int getCurDmg() {
        return curDmg;
    }

    public void setCurDmg(int curDmg) {
        this.curDmg = curDmg;
    }

    public int getMaxFat() {
        return maxFat;
    }

    public void setMaxFat(int maxFat) {
        this.maxFat = maxFat;
    }

    public int getCurFat() {
        return curFat;
    }

    public void setCurFat(int curFat) {
        this.curFat = curFat;
    }

    public int getsJump() {
        return sJump;
    }

    public void setsJump(int sJump) {
        this.sJump = sJump;
    }

    public int getsSwim() {
        return sSwim;
    }

    public void setsSwim(int sSwim) {
        this.sSwim = sSwim;
    }

    public int getsSneak() {
        return sSneak;
    }

    public void setsSneak(int sSneak) {
        this.sSneak = sSneak;
    }

    public int getsClimb() {
        return sClimb;
    }

    public void setsClimb(int sClimb) {
        this.sClimb = sClimb;
    }

    public int getsDiplomacy() {
        return sDiplomacy;
    }

    public void setsDiplomacy(int sDiplomacy) {
        this.sDiplomacy = sDiplomacy;
    }

    public int getsResist() {
        return sResist;
    }

    public void setsResist(int sResist) {
        this.sResist = sResist;
    }

    public int getsHide() {
        return sHide;
    }

    public void setsHide(int sHide) {
        this.sHide = sHide;
    }

    public int getsTinker() {
        return sTinker;
    }

    public void setsTinker(int sTinker) {
        this.sTinker = sTinker;
    }

    public TextureRegion getPortraitR() {
        return portraitR;
    }

    public void setPortraitR(TextureRegion portraitR) {
        this.portraitR = portraitR;
    }

    public TextureRegion getTokenR() {
        return tokenR;
    }

    public void setTokenR(TextureRegion tokenR) {
        this.tokenR = tokenR;
    }

    public int getTokenPosX() {
        return tokenPosX;
    }

    public void setTokenPosX(int tokenPosX) {
        this.tokenPosX = tokenPosX;
    }

    public int getTokenPosY() {
        return tokenPosY;
    }

    public void setTokenPosY(int tokenPosY) {
        this.tokenPosY = tokenPosY;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public void setPlayerNumber(int playerNumber) {
        this.playerNumber = playerNumber;
    }

    public GameBoard getBoard() {
        return board;
    }

    public void setBoard(GameBoard board) {
        this.board = board;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }
}
