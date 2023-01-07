package userinterface.logic;

import problemdomain.Storage;
import problemdomain.SudokuGame;
import userinterface.UserInterface;

import java.io.IOException;


public class ControlLogic implements UserInterface.EventListener {

    private Storage storage;

    private UserInterface.View view;

    //constructor
    public ControlLogic(Storage storage, UserInterface.View view) {
        this.storage = storage;
        this.view = view;
    }

    //write to the storage whenever user inputs/deletes a number
    @Override
    public void onSudokuInput(int x, int y, int input) {
        try {
            SudokuGame gameData = storage.getGameData();
            int[][] newGridState = gameData.getCopyOfGridState();
            newGridState[x][y] = input;
            gameData = new SudokuGame(GameLogic.checkForCompletion(newGridState), newGridState);
            storage.updateGameData(gameData);
            view.updateSquare(x,y,input);

        } catch (IOException e) {

        }
    }


    @Override
    public void onDialogClick() {

    }

}
