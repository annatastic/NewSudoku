package userinterface;

import problemdomain.SudokuGame;

public interface UserInterface {
    interface EventListener {   //like a controller/presenter
        void onSudokuInput(int x, int y, int input);
        void onDialogClick();
    }

    interface View {  //binds to the user interface
        void setListener(UserInterface.EventListener listener);
        void updateSquare(int x, int y, int input);
        void updateBoard(SudokuGame game);
        void showDialog(String s);
        void showError(String s);
    }
}





