package com.sc;

import java.io.IOException;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

public class gameController {

    public GridPane gameBoard;
    public Circle circle = new Circle(10);
    public Boolean player1Turn;

    public Label blackCaptures,
                 whiteCaptures,
                 winner;

    @FXML
    private void home() throws IOException {
        App.setRoot("main-menu");
    }

    @FXML
    private void htp() throws IOException {
        App.setRoot("howtoplay");
    }

    public void play() {
        gameBoard.setDisable(false); // Enable the game board
        gameBoard.getChildren().clear(); // Clear the game board
        whiteCaptures.setText("0"); // Reset the white captures
        blackCaptures.setText("0"); // Reset the black captures
        winner.setText("No Game Started"); // Reset the winner
        winner.setVisible(false); // Hide the winner
        player1Turn = false; // Reset the turn

        // Create the board
        Circle[][] pieces = new Circle[gameBoard.getRowCount()][gameBoard.getColumnCount()];
        for (int i = 0; i < gameBoard.getRowCount(); i++) {
            for (int j = 0; j < gameBoard.getColumnCount(); j++) {
                pieces[i][j] = new Circle(10);
                pieces[i][j].setId("piece" + i + j);
                pieces[i][j].setFill(Color.TRANSPARENT);
                gameBoard.add(pieces[i][j], i, j);
            }
        }

        // Add Hover Event Handler for each piece
        for (Node node : gameBoard.getChildren()) {
            node.setOnMouseEntered((MouseEvent t) -> {
                node.setStyle("-fx-stroke: black;");
            });
            node.setOnMouseExited((MouseEvent t) -> {
                node.setStyle("-fx-stroke: transparent;");
            });
        }

        pieces[9][9].setFill(Color.WHITE); // Place the white center piece
        pieces[9][9].setDisable(true);
    }

    // Event Handler for when a piece is clicked
    public void gridClickHandler(MouseEvent e) {
        Node node = e.getPickResult().getIntersectedNode();
        if (node != gameBoard) {
            if (player1Turn && ((Shape) node).getFill() == Color.TRANSPARENT) { // If it's player 1's turn and the slot is empty
                ((Shape) node).setFill(Color.WHITE);
                node.setDisable(true);
                player1Turn = false;
            } else if (!player1Turn && ((Shape) node).getFill() == Color.TRANSPARENT) { // If it's player 2's turn and the slot is empty
                ((Shape) node).setFill(Color.BLACK);
                node.setDisable(true);
                player1Turn = true;
            }

            checkCapture(node); // Check for a capture on placed piece

            if (checkWin(node)) { // Check for a win on placed piece
                String winnerText = !player1Turn ? "Player 1" : "Player 2";

                // Show the winner
                this.winner.setText(winnerText + " wins!");
                this.winner.setVisible(true);
                gameBoard.setDisable(true);
            }
        }
    }

    // =========================================================================== //

    public void checkCapture(Node startNode) {
        int startNodeIndex = startNode.getParent().getChildrenUnmodifiable().indexOf(startNode);
        Paint startNodeColor = ((Shape) startNode).getFill();

        int horizIncrement = -19,
            vertIncrement = -1,
            diagIncrement = -20,
            antidiagIncrement = -18;

        horizontalCapture(startNode, startNodeIndex, startNodeColor, horizIncrement); // Check for a Horizontal Capture
        verticalCapture(startNode, startNodeIndex, startNodeColor, vertIncrement); // Check for a Vertical Capture
        diagonalCapture(startNode, startNodeIndex, startNodeColor, diagIncrement); // Check for a Diagonal Capture
        antidiagonalCapture(startNode, startNodeIndex, startNodeColor, antidiagIncrement); // Check for an Anti-Diagonal Capture
        
    }

    public void horizontalCapture(Node startNode, int startNodeIndex, Paint startNodeColor, int horizIncrement) { 
        if (startNodeIndex + horizIncrement * 3 >= 0) captureLogic(startNode, startNodeIndex, startNodeColor, horizIncrement);
        if (startNodeIndex - horizIncrement * 3 <= 360) captureLogic(startNode, startNodeIndex, startNodeColor, -horizIncrement);

    }
    public void verticalCapture(Node startNode, int startNodeIndex, Paint startNodeColor, int vertIncrement) {
        if (startNodeIndex + vertIncrement * 3 >= 0) captureLogic(startNode, startNodeIndex, startNodeColor, vertIncrement);
        if (startNodeIndex - vertIncrement * 3 <= 360) captureLogic(startNode, startNodeIndex, startNodeColor, -vertIncrement);

    }
    public void diagonalCapture(Node startNode, int startNodeIndex, Paint startNodeColor, int diagIncrement) { 
        if (startNodeIndex + diagIncrement * 3 >= 0) captureLogic(startNode, startNodeIndex, startNodeColor, diagIncrement);
        if (startNodeIndex - diagIncrement * 3 <= 360) captureLogic(startNode, startNodeIndex, startNodeColor, -diagIncrement);

    }
    public void antidiagonalCapture(Node startNode, int startNodeIndex, Paint startNodeColor, int antidiagIncrement) { 
        if (startNodeIndex + antidiagIncrement * 3 >= 0) captureLogic(startNode, startNodeIndex, startNodeColor, antidiagIncrement);
        if (startNodeIndex - antidiagIncrement * 3 <= 360) captureLogic(startNode, startNodeIndex, startNodeColor, -antidiagIncrement);

    }

    public void captureLogic(Node startNode, int startNodeIndex, Paint startNodeColor, int increment) {
        int captureCount = 0;
        Node currentNode;
        ArrayList<Node> captureNodes = new ArrayList<Node>();
        int currentNodeIndex = startNodeIndex;
        Paint currentNodeColor;

        currentNodeIndex = startNodeIndex + increment * 3;
        currentNode = startNode.getParent().getChildrenUnmodifiable().get(currentNodeIndex);
        currentNodeColor = ((Shape) currentNode).getFill();

        if (currentNodeColor == startNodeColor) {
            for (int j = 1; j < 3; j++) {
                currentNodeIndex = currentNodeIndex - increment;
                currentNode = startNode.getParent().getChildrenUnmodifiable().get(currentNodeIndex);
                currentNodeColor = ((Shape) currentNode).getFill();

                if (currentNodeColor == startNodeColor || currentNodeColor == Color.TRANSPARENT)
                    break;
                captureCount++;
                captureNodes.add(currentNode);
                if (captureCount == 2) {
                    for (Node node : captureNodes) {
                        ((Shape) node).setFill(Color.TRANSPARENT);
                        node.setDisable(false);
                        
                        switch(currentNodeColor.toString()) {
                            case "0xffffffff": // White Captured
                                whiteCaptures.setText(Integer.toString(Integer.parseInt(whiteCaptures.getText()) + 1));
                                break;
                            case "0x000000ff": // Black Captured
                                blackCaptures.setText(Integer.toString(Integer.parseInt(blackCaptures.getText()) + 1));
                                break;
                        }

                        
                    }
                }
            }
        }
    }


    // =========================================================================== //
    public Boolean checkWin(Node startNode) {
        int startNodeIndex = startNode.getParent().getChildrenUnmodifiable().indexOf(startNode);
        Paint startNodeColor = ((Shape) startNode).getFill();

        int horizIncrement = -19,
            vertIncrement = -1,
            diagIncrement = -20,
            antidiagIncrement = -18;

        
        if (Integer.parseInt(whiteCaptures.getText().toString()) >= 10 || Integer.parseInt(blackCaptures.getText().toString()) >= 10) {
            return true;
        }


        if (!horizontalWin(startNode, startNodeIndex, startNodeColor, horizIncrement)) { // Check Horizontal Win
            if (!verticalWin(startNode, startNodeIndex, startNodeColor, vertIncrement)) { // Check Vertical Win
                if (!diagonalWin(startNode, startNodeIndex, startNodeColor, diagIncrement)) { // Check Diagonal Win
                    if (!antidiagonalWin(startNode, startNodeIndex, startNodeColor, antidiagIncrement)) { // Check Anti-Diagonal Win

                        return false;
                    }
                }
            }
        }
        

        return true;
    }

    public Boolean horizontalWin(Node startNode, int startNodeIndex, Paint startNodeColor, int horizIncrement) {
        return winLogic(startNode, startNodeIndex, startNodeColor, horizIncrement);
    }
    public Boolean verticalWin(Node startNode, int startNodeIndex, Paint startNodeColor, int vertIncrement) {
        return winLogic(startNode, startNodeIndex, startNodeColor, vertIncrement);
    }
    public Boolean diagonalWin(Node startNode, int startNodeIndex, Paint startNodeColor, int diagIncrement) {
        return winLogic(startNode, startNodeIndex, startNodeColor, diagIncrement);
    }
    public Boolean antidiagonalWin(Node startNode, int startNodeIndex, Paint startNodeColor, int antidiagIncrement) {
        return winLogic(startNode, startNodeIndex, startNodeColor, antidiagIncrement);
    }

    public Boolean winLogic(Node startNode, int startNodeIndex, Paint startNodeColor, int increment) {
        int pieceCount = 1;
        Node currentNode;
        int currentNodeIndex = startNodeIndex;
        Paint currentNodeColor;

        while (true) {
            if (startNodeIndex + increment * pieceCount >= 0 && startNodeIndex + increment * pieceCount <= 360) {
                currentNodeIndex = startNodeIndex + increment * pieceCount; // Get Index of Node to Check
                currentNode = startNode.getParent().getChildrenUnmodifiable().get(currentNodeIndex); // Get Node toCheck
                currentNodeColor = ((Shape) currentNode).getFill();

                if (currentNodeColor == startNodeColor) {
                    pieceCount++; // Increase Piece Count
                    if (pieceCount == 5)
                        return true; // Win Condition
                } else {
                    if (increment > 0)
                        return false;
                    increment *= -1;
                }
            } else {
                return false;
            }
        }
    }

}