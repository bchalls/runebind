package com.ravine.runebind.turn;

import com.badlogic.gdx.Gdx;
import com.ravine.runebind.RuneBind;
import com.ravine.runebind.board.GameBoard;
import com.ravine.runebind.entity.Player;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Brandon
 * Date: 8/18/13
 * Time: 1:41 PM
 */
public class TurnManager {

    public enum Step {
        refresh,
        movement,
        adventure,
        market,
        experience
    }

    private GameBoard board;
    private ArrayList<Player> playerList;
    private Player curPlayer;

    private Step curStep;

    public TurnManager(GameBoard board) {
        this.board = board;
        curStep = Step.refresh;
        playerList = new ArrayList<Player>();

    }

    public void addPlayer(Player p) {
        playerList.add(p);
        Gdx.app.log(RuneBind.LOG, "Adding player: " + p.getName());
        if(playerList.size() == 1) {
            curPlayer = playerList.get(0);
        }
    }

    public Step getCurStep() {
        return  curStep;
    }

    public Player getCurPlayer() {
        return curPlayer;
    }


    /*
        Refresh - player.refreshCards -- One time step
        Movement -  board.highlightAdjacent (player may move one tile) if not then
         player.setRest(0-4) (player may set aside 0-4 dice to discard fat) then
          diceManager.rollDice board.highlightPaths -- Does not need a loop. Use flags for each part of step
        Adventure - player.adventure? yes: adventureDeck.draw(color)
         event: board.replaceEvent board.applyEvent board.replenish?
          encounter: player.resolve(card)
          challenge: player.resolve(card)
          unresolved challenge will be handled as an adventure
          pvp later
        Story - add just to allow for the addition of expansions
        Market - marketDeck*Town.push(marketDeckAll.pop) board.showMarketOptions
         buy item/ally: show current towns market cards. card.clicked? player.confirmBuy? playerHand.add(Card)
         sell card: player.showHand card.clicked? player.confirmSell
         heal: slider to select amount 0 - min(wounds,gold)
        Experience - if player has correct amount of experience needed for the number
         of players, show options for level up
     */
    public void handleStep() {
        switch (curStep) {
            case refresh:
                Gdx.app.log(RuneBind.LOG, "Starting refresh step");
                curPlayer.refreshCards();
                curStep = Step.movement;
                Gdx.app.log(RuneBind.LOG, "Starting movement step");
                board.movePlayer(curPlayer, board.getTile(9, 7));
                break;
            case movement:
                break;
            case adventure:
                break;
            case market:
                break;
            case experience:
                break;
        }

    }


}
