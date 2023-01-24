package problemdomain;

import buildlogic.SudokuBuildLogic;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import userinterface.UIImplementation;
import userinterface.UserInterface;

import java.io.IOException;

public class SudokuApplication extends Application {
    private UserInterface.View uiImpl;
    @Override
    public void start(Stage primaryStage) throws IOException {
       // FXMLLoader fxmlLoader = new FXMLLoader(SudokuApplication.class.getResource("hello-view.fxml"));
        uiImpl = new UIImplementation(primaryStage);
        try{
            SudokuBuildLogic.build(uiImpl);
        }
        catch(IOException e){
            e.printStackTrace();
            throw e;
        }

     /*   Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setScene(scene);
        stage.show(); */
    }

    public static void main(String[] args) {
        launch(args);
    }
}