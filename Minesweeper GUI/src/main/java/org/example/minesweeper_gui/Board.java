package org.example.minesweeper_gui;

import javafx.scene.control.Button;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private final int boardSize;
    private final Tile[][] board; //[x][y] = Tile

    private int numOfMines;

    public int getBoardSize() {
        return boardSize;
    }

    public Board(int _boardSize) {
        this.boardSize = _boardSize;
        board = new Tile[_boardSize][_boardSize];
    }

    public int getNumOfMines() {
        return numOfMines;
    }

    public void generateBoard(int _turn) {
        if (_turn == 1) {
            fillBoard();
            fillBoardWithMines();
            fillBoardNumbers();
        }
    }

    public void fillBoard() {
        // generate normal tiles (non mines)
        for (int y = 0; y < boardSize; y++) {
            for (int x = 0; x < boardSize; x++) {
                Tile newTile = new Tile(false);
                board[x][y] = newTile;
            }
        }
    }

    public void fillBoardWithMines() {
        ArrayList<Integer> listOfMineLocations = new ArrayList<>();
        // calculate how many mines - more if bigger grid
        numOfMines = (int) (boardSize * boardSize / 7) + 1; // hard code for now
        System.out.println("Mines:" + numOfMines);

        while (listOfMineLocations.size() < numOfMines * 2) {
            int randomXValue = 1 + (int) (Math.random() * ((boardSize - 1) + 1)) - 1;
            int randomYValue = 1 + (int) (Math.random() * ((boardSize - 1) + 1)) - 1;
            board[randomXValue][randomYValue].setHasMine(true);
            board[randomXValue][randomYValue].setStatus("M");

            if (listOfMineLocations.isEmpty()) {
                listOfMineLocations.add(randomXValue);
                listOfMineLocations.add(randomYValue);
            } else {
                if (!checkIfAlreadyExists(listOfMineLocations, randomXValue, randomYValue)) {
                    listOfMineLocations.add(randomXValue);
                    listOfMineLocations.add(randomYValue);
                }
            }
        }
    }

    public Tile getTile(int _x, int _y) {
        return board[_x][_y];
    }

    public void fillBoardNumbers() {

        for (int r = 0; r < boardSize; r++) {
            for (int c = 0; c < boardSize; c++) {
                int count = 0;
                if (r == 0 && c == 0) { // top left corner
                    if (board[r + 1][c].getHasMine()) {
                        count += 1;
                    }
                    if (board[r + 1][c + 1].getHasMine()) {
                        count += 1;
                    }
                    if (board[r][c + 1].getHasMine()) {
                        count += 1;
                    }
                }
                if (r == boardSize - 1 && c == 0) { // top right corner
                    if (board[r - 1][c].getHasMine()) {
                        count += 1;
                    }
                    if (board[r - 1][c + 1].getHasMine()) {
                        count += 1;
                    }
                    if (board[r][c + 1].getHasMine()) {
                        count += 1;
                    }
                }
                if (r == boardSize - 1 && c == boardSize - 1) { // bottom right corner
                    if (board[r - 1][c].getHasMine()) {
                        count += 1;
                    }
                    if (board[r - 1][c - 1].getHasMine()) {
                        count += 1;
                    }
                    if (board[r][c - 1].getHasMine()) {
                        count += 1;
                    }
                }
                if (r == 0 && c == boardSize - 1) { // bottom left corner
                    if (board[r + 1][c].getHasMine()) {
                        count += 1;
                    }
                    if (board[r + 1][c - 1].getHasMine()) {
                        count += 1;
                    }
                    if (board[r][c - 1].getHasMine()) {
                        count += 1;
                    }
                }
                if ((r != 0 && c != 0) && r != boardSize - 1 && c != boardSize - 1) { // middle
                    // (-1,0/+1,0)  (-1,-1/0,-1/+1,-1)  (-1,+1/0,+1/+1,+1)
                    if (board[r - 1][c].getHasMine()) {
                        count += 1;
                    }
                    if (board[r + 1][c].getHasMine()) {
                        count += 1;
                    }
                    if (board[r - 1][c + 1].getHasMine()) {
                        count += 1;
                    }
                    if (board[r][c + 1].getHasMine()) {
                        count += 1;
                    }
                    if (board[r + 1][c + 1].getHasMine()) {
                        count += 1;
                    }
                    if (board[r - 1][c - 1].getHasMine()) {
                        count += 1;
                    }
                    if (board[r][c - 1].getHasMine()) {
                        count += 1;
                    }
                    if (board[r + 1][c - 1].getHasMine()) {
                        count += 1;
                    }

                }
                if (c == 0 && r < boardSize - 1 && r > 0) { // top edge
                    if (board[r - 1][c].getHasMine()) {
                        count += 1;
                    }
                    if (board[r - 1][c + 1].getHasMine()) {
                        count += 1;
                    }
                    if (board[r][c + 1].getHasMine()) {
                        count += 1;
                    }
                    if (board[r + 1][c + 1].getHasMine()) {
                        count += 1;
                    }
                    if (board[r + 1][c].getHasMine()) {
                        count += 1;
                    }
                }

                if (r == 0 && c < boardSize - 1 && c > 0) { // left edge
                    if (board[r][c - 1].getHasMine()) {
                        count += 1;
                    }
                    if (board[r + 1][c - 1].getHasMine()) {
                        count += 1;
                    }
                    if (board[r + 1][c].getHasMine()) {
                        count += 1;
                    }
                    if (board[r + 1][c + 1].getHasMine()) {
                        count += 1;
                    }
                    if (board[r][c + 1].getHasMine()) {
                        count += 1;
                    }
                }

                if (r == boardSize - 1 && c < boardSize - 1 && c > 0) { // right edge
                    if (board[r][c - 1].getHasMine()) {
                        count += 1;
                    }
                    if (board[r - 1][c - 1].getHasMine()) {
                        count += 1;
                    }
                    if (board[r - 1][c].getHasMine()) {
                        count += 1;
                    }
                    if (board[r - 1][c + 1].getHasMine()) {
                        count += 1;
                    }
                    if (board[r][c + 1].getHasMine()) {
                        count += 1;
                    }
                }
                if (c == boardSize - 1 && r < boardSize - 1 && r > 0) { // bottom edge
                    if (board[r - 1][c].getHasMine()) {
                        count += 1;
                    }
                    if (board[r - 1][c - 1].getHasMine()) {
                        count += 1;
                    }
                    if (board[r][c - 1].getHasMine()) {
                        count += 1;
                    }
                    if (board[r + 1][c - 1].getHasMine()) {
                        count += 1;
                    }
                    if (board[r + 1][c].getHasMine()) {
                        count += 1;
                    }
                }

                if (!board[r][c].getHasMine()) {
                    if (count == 0) {
                        board[r][c].setStatus(" ");
                    } else {
                        board[r][c].setStatus(String.valueOf(count));
                    }
                }
            }
        }
    }

    public void openTile(int _x, int _y, Button _button) {
        board[_x][_y].setVisible(true);
//        _button.setDisable(true);
    }

    public boolean checkIfAlreadyExists(List<Integer> listOfMines, int xValue, int yValue) {
        for (int x = 0; x < listOfMines.size(); x += 2) {
            if (listOfMines.get(x) == xValue && listOfMines.get(x+1) == yValue) {
                return true;
            }
        }
        return false;
    }

//    @FunctionalInterface
//    interface Adjacent {
//        void apply();
//    }
//    public static void runAdjacentCheck(Function func) {
//        func.apply();
//    }
}
