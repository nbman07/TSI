package org.example.minesweeper_gui;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;

import java.util.Objects;

public class Game {
    private boolean gameEnded;
    private int turn = 1;

    public void startGame(Stage _stage, int _numOfRowCols) {
        turn = 1;
        Board newBoard = new Board(_numOfRowCols);
        newBoard.generateBoard(turn);

        Label turnLabel = new Label("Turn: 1");
        turnLabel.setLayoutX(newBoard.getBoardSize() * 50 + 50);
        turnLabel.setLayoutY(150);
//        turnLabel.setText("Turn: " + turn);


        Group root = new Group();
        for (int i = 0; i < newBoard.getBoardSize(); i++) {
            for (int j = 0; j < newBoard.getBoardSize(); j++) {
//                Button newButton = new Button(newBoard.getTile(i, j).getStatus());
                Button newButton = new Button(" ");
                newButton.setPrefSize(50, 50);
                newButton.setLayoutX(i * 50);
                newButton.setLayoutY(j * 50);
                int finalI = i;
                int finalJ = j;
                newButton.setOnMouseClicked(event ->
                {
                    if (event.getButton() == MouseButton.PRIMARY) {
                        if (newBoard.getTile(finalI, finalJ).getHasMine()) {
                            newButton.setText(newBoard.getTile(finalI,finalJ).getStatus());
                            gameEnded = true;
                            Label gameEnd = new Label("Game ended. You clicked on a mine.");
                            gameEnd.setLayoutX(100);
                            gameEnd.setLayoutY(newBoard.getBoardSize() * 50 + 50);
                            root.getChildren().add(gameEnd);
                        } else {
                            newButton.setText(newBoard.getTile(finalI, finalJ).getStatus());
                            newBoard.getTile(finalI,finalJ).setBeenFlagged(false);
//                            newBoard.getTile(finalI, finalJ).setVisible(true);
//                            for (Node child : root.getChildren()) {
//                                if (Objects.equals(newButton.getText(), " ")) {
//                                    if (newBoard.getTile(finalI,finalJ).getStatus().equals(" ")) {
//
//                                        try {
//                                            Button otherButtons = (Button) child;
//                                            System.out.println("Blank found?");
//                                            otherButtons.setDisable(true);
//
//                                        } catch (Exception e) {
//                                            System.out.println("Exception caught");
//                                        }
//                                    }
//                                }
//                            }
                            newBoard.openTile(finalI, finalJ, newButton);
                        }
                        newButton.setDisable(true);
                    } else if (event.getButton() == MouseButton.SECONDARY) {
                        newBoard.getTile(finalI, finalJ).setBeenFlagged(true);
                        newButton.setText("F");
                    }
                    turn++;
                    if (checkWin(newBoard)) {
                        Label gameEnd = new Label("You won!");
                        gameEnd.setLayoutX(50);
                        gameEnd.setLayoutY(newBoard.getBoardSize() * 50 + 50);
                        root.getChildren().add(gameEnd);
                    }
                    turnLabel.setText("Turn: " + turn);
                });
                root.getChildren().add(newButton);
            }
        }

        root.getChildren().add(turnLabel);
        Button retryButton = new Button("Retry");
        retryButton.setLayoutX(newBoard.getBoardSize() * 50 + 50);
        retryButton.setLayoutY(50);
        root.getChildren().add(retryButton);

        Scene scene = new Scene(root, 500, 500);
        _stage.setScene(scene);
        _stage.show();

        retryButton.setOnMouseClicked(event -> {
            root.getChildren().clear();
            startGame(_stage, _numOfRowCols);
        });
    }

    private static boolean checkWin(Board _gameBoard) {
        int flagCount = 0;
        int clickCount = 0;
        for (int i = 0; i < _gameBoard.getBoardSize(); i++) {
            for (int j = 0; j < _gameBoard.getBoardSize(); j++) {
                if (_gameBoard.getTile(i, j).getBeenFlagged()) {
                    flagCount += 1;
                }
                if (_gameBoard.getTile(i, j).getVisible()) {
                    clickCount += 1;
                }
            }
        }
        if (_gameBoard.getNumOfMines() == flagCount) {
            return (flagCount + clickCount == _gameBoard.getBoardSize() * _gameBoard.getBoardSize());
        }
        return false;
    }
}
