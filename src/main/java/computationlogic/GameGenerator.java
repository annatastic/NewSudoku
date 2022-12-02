package computationlogic;

import problemdomain.Coordinates;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static problemdomain.SudokuGame.BOUNDARY;

public class GameGenerator {

  /*  public static int[][] getNewBoard() {
        return unfillBoard(fillBoard());
    }

    public static int[][] fillBoard() {
        Random random = new Random(System.currentTimeMillis());
        int[][] filledBoard = new int[BOUNDARY][BOUNDARY];

        for(int value = 1; value<=9; value++) {
            List<Coordinates> allocationTracker = new ArrayList<>();
            int allocations = 0;
            int interrupt = 0;

            int tries = 0;

            while(allocations < 9) {

                if (interrupt > 200) {
                    allocationTracker.forEach(coordinates -> {
                        filledBoard[coordinates.getX()][coordinates.getY()] = 0;
                    });

                    allocations = 0;
                    interrupt = 0;
                    allocationTracker.clear();
                    tries++;
                }
            }

        }
    }  */
}
