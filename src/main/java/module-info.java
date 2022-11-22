module com.example.bloom {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    exports com.example.bloom;
    exports com.example.bloom.functional;

    opens com.example.bloom to javafx.fxml;
    opens com.example.bloom.controller to javafx.fxml;
    opens com.example.bloom.model;
    opens com.example.bloom.functional to javafx.fxml;
}