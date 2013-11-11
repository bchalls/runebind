package com.ravine.runebind.turn;

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

    }

    public void addPlayer(String characterName) {
        playerList.add(new Player(characterName, playerList.size()+1, board));
    }


    /*
        Refresh - player.refreshCards
        Movement -  board.highlightAdjacent (player may move one tile) if not then
         player.setRest(0-4) (player may set aside 0-4 dice to discard fat) then
          diceManager.rollDice board.highlightPaths
        Adventure - player.adventure? yes: adventureDeck.draw(color)
         event: board.replaceEvent board.applyEvent board.replenish?
          encounter: player.resolve(card)
          challenge: player.resolve(card)
          unresolved challenge will be handled as an adventure
          pvp later
        Market - marketDeck*Town.push(marketDeckAll.pop) board.showMarketOptions
         buy item/ally: show current towns market cards. card.clicked? player.confirmBuy? playerHand.add(Card)
         sell card: player.showHand card.clicked? player.confirmSell
         heal: slider to select amount min(wounds,gold)
        Experience - if player has correct amount of experience needed for the number
         of players, show options for level up
     */
    public void handleStep() {

    }


}
