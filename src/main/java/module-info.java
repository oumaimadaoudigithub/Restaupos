module com.example.restauouma {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires java.sql;

    opens com.example.restauouma to javafx.fxml;
    exports com.example.restauouma;
}