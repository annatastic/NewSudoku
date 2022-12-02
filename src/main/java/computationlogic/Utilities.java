package computationlogic;

import problemdomain.SudokuGame;

public class Utilities {

    public static void copySudokuArray(int[][] oldArray, int[][] newArray) {
        for (int x = 0; x < SudokuGame.BOUNDARY; x++) {
            for (int y = 0; y < SudokuGame.BOUNDARY; y++) {
                newArray[x][y] = oldArray[x][y];
            }

        }
    }

    public static int[][] copyToNewArray(int[][] oldArray) {
        int[][] newArray = new int[SudokuGame.BOUNDARY][SudokuGame.BOUNDARY];

        for (int x = 0; x < SudokuGame.BOUNDARY; x++) {
            for (int y = 0; y < SudokuGame.BOUNDARY; y++) {
                newArray[x][y] = oldArray[x][y];
            }

        }
        return newArray;
    }

}

// look into using clone() method instead of these