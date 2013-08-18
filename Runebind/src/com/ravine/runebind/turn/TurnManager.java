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

    private enum Step {
        refresh,
        movement,
        adventure,
        market,
        experience
    }

    private GameBoard board;
    private ArrayList<Player> playerList;


}
