module com.example.projekat1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.projekat1 to javafx.fxml;
    exports com.example.projekat1;
    exports com.example.kontroleri;
    opens com.example.kontroleri to javafx.fxml;
}