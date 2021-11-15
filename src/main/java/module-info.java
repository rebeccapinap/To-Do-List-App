module ucf.assignments {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;

    opens ucf.assignments to javafx.fxml;
    exports ucf.assignments;
}