module com.example.bloom {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.example.bloom to javafx.fxml;
    exports com.example.bloom;
    opens com.example.bloom.controller to javafx.fxml;
    opens com.example.bloom.Model;
}