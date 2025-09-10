package com.sc;

import java.io.IOException;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.SVGPath;

public class htpController {

    public Polygon rightPage, leftPage;
    public SVGPath exitX;
    public Pane page1, page2;

    @FXML
    private void back() throws IOException {
        App.setRoot(App.previousfxml);

    }

    public void turnRightPage() {
        leftPage.setVisible(true);
        rightPage.setVisible(false);

        page1.setVisible(false);
        page2.setVisible(true);
    }

    public void turnLeftPage() {
        rightPage.setVisible(true);
        leftPage.setVisible(false);

        page1.setVisible(true);
        page2.setVisible(false);

    }

    // #940000

    public void handleMouseEntered(Event e) {
        if (e.getSource() == rightPage) {
            rightPage.setFill(javafx.scene.paint.Color.rgb(10, 124, 235));
            rightPage.setCursor(Cursor.HAND);
        } else if (e.getSource() == leftPage) {
            leftPage.setFill(javafx.scene.paint.Color.rgb(10, 124, 235));
            leftPage.setCursor(Cursor.HAND);
        } else if (e.getSource() == exitX) {
            exitX.setFill(javafx.scene.paint.Color.rgb(116, 0, 0));
            exitX.setCursor(Cursor.HAND);
        }
    }

    public void handleMouseExited(Event e) {
        if (e.getSource() == rightPage) {
            rightPage.setFill(javafx.scene.paint.Color.rgb(30, 144, 255));
            rightPage.setCursor(Cursor.DEFAULT);
        } else if (e.getSource() == leftPage) {
            leftPage.setFill(javafx.scene.paint.Color.rgb(30, 144, 255));
            leftPage.setCursor(Cursor.DEFAULT);
        } else if (e.getSource() == exitX) {
            exitX.setFill(javafx.scene.paint.Color.rgb(148, 0, 0));
            exitX.setCursor(Cursor.DEFAULT);
        }
    }

}
