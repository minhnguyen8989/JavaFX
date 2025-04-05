module org.example.menuapplication {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.menuapplication to javafx.fxml;
    exports org.example.menuapplication;
}