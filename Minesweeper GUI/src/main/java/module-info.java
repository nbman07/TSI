module org.example.minesweeper_gui {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens org.example.minesweeper_gui to javafx.fxml;
    exports org.example.minesweeper_gui;
}