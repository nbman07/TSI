package org.example.minesweeper_gui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        VBox root = new VBox(30);
        root.setAlignment(Pos.CENTER);
        Label welcomeTitle = new Label("Welcome to Minesweeper!");
        Label boardSizeLabel = new Label("How many rows/columns would you like to play with?");
        TextField boardSizeInput = new TextField();
        Button submitButton = new Button("Submit");
        submitButton.setDefaultButton(true);
        Label warningLabel = new Label("");

        submitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    if (Integer.parseInt(boardSizeInput.getText()) > 1 && Integer.parseInt(boardSizeInput.getText()) < 21) {
                        playGame(stage, Integer.parseInt(boardSizeInput.getText()));
                    } else {
                        warningLabel.setText("Please try again. Enter a number between 1 and 21.");
                    }
                } catch (Exception e) {
                    warningLabel.setText("Please try again. Enter a number between 1 and 21.");
                }
            }
        });
        root.getChildren().addAll(welcomeTitle, boardSizeLabel, boardSizeInput, submitButton, warningLabel);
        Scene scene = new Scene(root, 500, 500);
        stage.setTitle("Minesweeper");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    private void playGame(Stage _stage, int _boardSize) {
        Game newGame = new Game();
        newGame.startGame(_stage, _boardSize);
    }
}