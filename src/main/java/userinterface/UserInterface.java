

//*** this interface is probably going to be deleted...  ***

package userinterface;

import problemdomain.SudokuGame;

public interface UserInterface {
    interface EventListener {
        void onSudokuInput(int x, int y, int input);
        void onDialogClick();
    }

    interface View {
        void setListener(EventListener listener);
        void updateSquare(int x, int y, int input);
        void updateBoard(SudokuGame game);
        void showDialog(String s);
    }
}
