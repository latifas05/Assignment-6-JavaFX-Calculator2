module org.example.calculator2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.myapp to javafx.fxml;
    exports com.example.myapp;
}