module com.example.newsudoku {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.newsudoku to javafx.fxml;
    exports com.example.newsudoku;
    exports problemdomain;
    opens problemdomain to javafx.fxml;
}