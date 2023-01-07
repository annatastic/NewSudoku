package userinterface;

import constants.GameState;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import problemdomain.Coordinates;
import problemdomain.SudokuGame;

import java.util.HashMap;


public class UIImplementation implements UserInterface.View,
        EventHandler<KeyEvent> {

    private /*final*/ Stage stage;
    private /*final*/ Group root;
    private HashMap<Coordinates, SudokuTextField> textFieldCoords;  //hashmap to store the text field of each tile on the board
                                                                // key = coordinate, value = textfield
    private UserInterface.EventListener listener;

    //since there are multiple GUI screens, you should put these in a separate file and make them public (so as not to redefine them in multiple GUIs)
    private static final double WINDOW_Y = 732;
    private static final double WINDOW_X = 668;
    private static final double BOARDPADDING = 50;
    private static final double BOARD_X_AND_Y = 576;

    private static final Color WINDOW_BG_COLOR = Color.rgb(171,131, 185);
    private static final Color BOARD_BG_COLOR = Color.rgb(243,243,243);

    public UIImplementation(Stage stage) {
        this.stage = stage;
        this.root = new Group();
        this.textFieldCoords = new HashMap<>();
        initializeUI();
    }

    private void initializeUI() {
        drawBackground(root);
        drawSudokuBoard(root);
        drawTextFields(root);
        drawGridLines(root);
        stage.show();
    }

    private void drawGridLines(Group root) {
        int xAndY = 114;  //where we start to draw the grid lines
        int index = 0;
        while (index < 8) {
            int thickness;
            if (index == 2 || index == 5) {   //where the thicker dividing lines are
                thickness = 3;
            }
            else {
                thickness =2;
            }
            Rectangle verticalLine = getLine(xAndY + 64 * index, BOARDPADDING, BOARD_X_AND_Y, thickness);
            Rectangle horizLine = getLine(BOARDPADDING, xAndY+ 64 * index, thickness, BOARD_X_AND_Y);

            root.getChildren().addAll(verticalLine, horizLine);

            index++;

        }
    }

    private Rectangle getLine(double x, double y, double height, double width) {
        Rectangle line = new Rectangle();

        line.setX(x);
        line.setY(y);
        line.setHeight(height);
        line.setWidth(width);

        line.setFill(Color.BLACK);
        return null;
    }

    private void drawTextFields(Group root) {
        final int xOrigin = 50;
        final int yOrigin = 50;
        final int changeInXAndY = 64;

        for (int xIndex = 0; xIndex < 9; xIndex++){
            for(int yIndex = 0; yIndex < 9; yIndex++) {
                int x = xOrigin + xIndex * changeInXAndY;
                int y = yOrigin + yIndex * changeInXAndY;

                SudokuTextField tile = new SudokuTextField(xIndex, yIndex);

                styleSudokuTile(tile, x, y);

                tile.setOnKeyPressed(this);

                textFieldCoords.put(new Coordinates(xIndex, yIndex), tile);
                root.getChildren().add(tile);
            }
        }
    }

    private void styleSudokuTile(SudokuTextField tile, double x, double y) {
        Font numberFont = new Font(32);

        tile.setFont(numberFont);
        tile.setAlignment(Pos.CENTER);
        tile.setLayoutX(x);
        tile.setLayoutY(y);
        tile.setPrefHeight(64);
        tile.setPrefWidth(64);
        tile.setBackground(Background.EMPTY); /* this makes the background of a tile transparent; therefore
                                                it must use the background color of BOARD_BG_COLOR */


    }

    private void drawSudokuBoard(Group root) {
        Rectangle boardBG = new Rectangle();
        boardBG.setX(BOARDPADDING);
        boardBG.setY(BOARDPADDING);
        boardBG.setWidth(BOARD_X_AND_Y);
        boardBG.setHeight(BOARD_X_AND_Y);
        boardBG.setFill(BOARD_BG_COLOR);
        root.getChildren().addAll(boardBG);

    }

    private void drawBackground(Group root) {
        Scene scene = new Scene(root, WINDOW_X, WINDOW_Y);
        scene.setFill(WINDOW_BG_COLOR);
        stage.setScene(scene);
    }

    // what happens when you press a key
    // I think I could add another case to this method to use a specific key to open the solution screen
    @Override
    public void handle(KeyEvent event) {

        if (event.getEventType() == KeyEvent.KEY_PRESSED) {
            if (event.getText().matches("[0-9]")) {
                int value = Integer.parseInt(event.getText());  // value is the number that the user entered
                handleInput(value, event.getSource()); // source is the UI element that was clicked on (the tile)
            }

            else if (event.getCode() == KeyCode.BACK_SPACE) {
                handleInput(0, event.getSource());
            }

            else {
                ((TextField) event.getSource()).setText("");
            }
        }

        event.consume();
    }

    private void handleInput(int i, Object source) {
        listener.onSudokuInput(
                ((SudokuTextField) source).getX(),
                ((SudokuTextField) source).getY(),
                i );
    }

    @Override
    public void setListener(UserInterface.EventListener listener) {
        this.listener = listener;
    }

    @Override
    public void updateSquare(int x, int y, int input) {
        SudokuTextField tile = textFieldCoords.get(new Coordinates(x,y));

        String value = Integer.toString(input);

        if(value.equals("0")) value = "";

        tile.textProperty().setValue(value);

    }

    @Override
    public void updateBoard(SudokuGame game) {

        for (int xIndex = 0; xIndex <9; xIndex++) {
            for (int yIndex = 0; yIndex <9; yIndex++) {
                TextField tile = textFieldCoords.get(new Coordinates(xIndex, yIndex));

                String value = Integer.toString(game.getCopyOfGridState()[xIndex][yIndex] );

                if (value.equals("0")) value = "";

                tile.setText(value);

                if (game.getGameState() == GameState.NEW) {
                    if (value.equals("")) {
                        tile.setStyle("-fx-opacity: 1;");
                        tile.setDisable(false);  //if user starts a new game, enables the empty tiles
                    }

                    else {
                        tile.setStyle("-fx-opacity: 0.8;");
                        tile.setDisable(true);
                    }
                }

            }
        }

    }

    //when the game has been solved!
    @Override
    public void showDialog(String s) {
        Alert dialog = new Alert(Alert.AlertType.CONFIRMATION, s, ButtonType.OK);
        dialog.showAndWait();

        if (dialog.getResult() == ButtonType.OK)  listener.onDialogClick();
    }

    @Override
    public void showError(String s) {
        Alert dialog = new Alert(Alert.AlertType.ERROR, s, ButtonType.OK);
        dialog.showAndWait();
    }
}
