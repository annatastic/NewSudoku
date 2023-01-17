package computationlogic;

import problemdomain.Coordinates;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static problemdomain.SudokuGame.BOUNDARY;

public class GameGenerator {
    public static int[][] getNewBoard() {
        return unfillBoard(fillBoard());
    }

    public static int[][] fillBoard() {
        Random random = new Random(System.currentTimeMillis());
        int[][] filledBoard = new int[BOUNDARY][BOUNDARY];

        for(int value = 1; value<=BOUNDARY; value++) {
            List<Coordinates> allocationTracker = new ArrayList<>();
            int allocations = 0;
            int interrupt = 0;

            int tries = 0; //attempts

            while(allocations < 9) {

                if (interrupt > 200) {  // failsafe
                    allocationTracker.forEach(coordinates -> {
                        filledBoard[coordinates.getX()][coordinates.getY()] = 0;
                    });

                    interrupt = 0;
                    allocations = 0;
                    allocationTracker.clear();
                    tries++;

                    if (tries > 500) {   // last ditch failsafe
                        clearArray(filledBoard);
                        tries = 0;
                        value = 1;
                    }
                }

                int X = random.nextInt(BOUNDARY);
                int Y = random.nextInt(BOUNDARY);

                if (filledBoard[X][Y] == 0) {  //if that square is "empty", fill it
                    filledBoard[X][Y] = value;

                    if (GameLogic.sudokuIsInvalid(filledBoard)) {  //check if that last insert made the puzzle invalid
                        filledBoard[X][Y] = 0;
                        interrupt++;
                    }
                    else {
                        allocationTracker.add(new Coordinates(X, Y));
                        allocations++;
                    }
                }
            }
        }
        return filledBoard;

    }


    public static int[][] unfillBoard(int[][] filledBoard) {
        Random random = new Random(System.currentTimeMillis());

        boolean solvable = false;
        int[][] puzzle = new int[BOUNDARY][BOUNDARY];  //solvableArray

        while (!solvable) {
            Utilities.copySudokuArray(filledBoard, puzzle);

            int index = 0;

        // remove 40 random values from puzzle
            while (index < 40) {
                int x = random.nextInt(BOUNDARY);
                int y = random.nextInt(BOUNDARY);

                if (puzzle[x][y] != 0 ) {
                    puzzle[x][y] = 0;
                    index++;
                }
            }
        // check if the resulting puzzle is solvable
            int[][] toBeSolved = new int[BOUNDARY][BOUNDARY];
            Utilities.copySudokuArray(puzzle, toBeSolved);
            solvable = SudokuSolver.puzzleIsSolvable(toBeSolved);

        }
        return puzzle;
    }



    private static void clearArray(int[][] filledBoard) {
        for (int x = 0; x < BOUNDARY; x++) {
            for(int y = 0; y < BOUNDARY; y++){
                filledBoard[x][y] = 0;
            }
        }
    }
}
