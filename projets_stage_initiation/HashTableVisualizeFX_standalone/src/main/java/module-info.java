module com.example.hashtablevisualizefx_standalone {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.hashtablevisualizefx_standalone to javafx.fxml;
    exports com.example.hashtablevisualizefx_standalone;
    exports Data;
    opens Data to javafx.fxml;


    exports presentation;
    opens presentation to javafx.fxml;

    exports service;
    opens service to javafx.fxml;
}