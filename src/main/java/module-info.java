module uet.oop.bomberman {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    opens uet.oop.bomberman to javafx.fxml;
    exports uet.oop.bomberman;
}
