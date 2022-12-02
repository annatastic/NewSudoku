package com.example.newsudoku;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import problemdomain.Coordinates;
import userinterface.TextField;

import java.io.IOException;
import java.util.HashMap;

public class HelloController {
    @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Button newGame;
    private HashMap<Coordinates, TextField> textFieldCoords;  //hashmap to store the text field of each tile on the board


    @FXML

    // switch from hello-view scene to gameplay scene by clicking newGame button
    public void onNewGameClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("gameplay.fxml"));
        stage = (Stage) ( (Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
}