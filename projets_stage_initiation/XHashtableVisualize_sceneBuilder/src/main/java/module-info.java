module com.example.xhashtablevisualize_scenebuilder {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.xhashtablevisualize_scenebuilder to javafx.fxml;
    exports com.example.xhashtablevisualize_scenebuilder;
}