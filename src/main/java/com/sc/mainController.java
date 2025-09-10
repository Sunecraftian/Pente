package com.sc;

import java.io.IOException;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Button;

public class mainController {

    public Button startButton, htpButton;

    @FXML
    private void start() throws IOException {
        App.setRoot("game");
    }

    @FXML
    private void htp() throws IOException {
        App.setRoot("howtoplay");
    }

    public void handleMouseEntered(Event e) {
        Button button = (Button) e.getSource(); // Passes ID to variable - for multiple IDs using one method

        // Highlight Button on Hover
        button.setStyle("-fx-text-fill: #009900; -fx-background-color: #080808;");
        button.setCursor(Cursor.HAND);

    }

    public void handleMouseExited(Event e) {
        Button button = (Button) e.getSource();

        // Revert Hover Highlight
        button.setStyle("-fx-text-fill: #009900; -fx-background-color: #282828;");
        button.setCursor(Cursor.DEFAULT);
    }
}
