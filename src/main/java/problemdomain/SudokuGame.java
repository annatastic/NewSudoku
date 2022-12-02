package problemdomain;

import computationlogic.Utilities;
import constants.GameState;

public class SudokuGame {
   private final GameState gameState;
   private final int[][] gridState;

    public static final int BOUNDARY = 9;

    // constructor assigns passed values to gameState and gridState
  public SudokuGame(GameState game, int[][] grid) {
       gameState = game;
       gridState = grid;
    }

   public GameState getGameState() {
        return gameState;
    }

    public int[][] getCopyOfGridState(){
        return Utilities.copyToNewArray(gridState);
    }
    // ^^^^ most likely can be replaced with clone() method?
}
