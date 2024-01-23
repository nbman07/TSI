import org.example.minesweeper_gui.Board;
//import org.example.minesweeper_gui.Board.*;
import org.example.minesweeper_gui.Tile;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


public class UtilTest {
    @Test
    public void testBoardSize() {
        Board board = new Board(10);
        Assertions.assertEquals(10, board.getBoardSize(), "Board size is not 10.");
    }

    @Test
    public void testFillBoard() {
        Board board = new Board(10);
        board.fillBoard();
        for (int x = 0; x < board.getBoardSize(); x++) {
            for (int y = 0; y < board.getBoardSize(); y++) {
                Assertions.assertInstanceOf(Tile.class, board.getTile(x, y), "Board has not created new tiles for each box on board.");
            }
        }
    }

    @Test
    public void testNumberOfMinesFillingBoard() {
        Board board = new Board(10);
        board.fillBoard();
        board.fillBoardWithMines();
        Assertions.assertEquals((10 * 10 / 7 + 1), board.getNumOfMines(), ("Number of mines do not match"));
    }

    // Test that listOfMineLocations = number of mines
    @Test
    public void testNumberOfMinesInGrid() {
        Board board = new Board(10);
        board.fillBoard();
        board.fillBoardWithMines();
        int count = 0;
        for (int x = 0; x < board.getBoardSize(); x++) {
            for (int y = 0; y < board.getBoardSize(); y++) {
                if (board.getTile(x, y).getHasMine()) {
                    count += 1;
                }
            }
        }
    }

    @Test
    public void testFillBoardNumbers() {
        Board board = new Board(10);
        board.fillBoard();
        board.fillBoardNumbers();
        int count = 0;
        for (int y = 0; y < board.getBoardSize(); y++) {
            for (int x = 0; x < board.getBoardSize(); x++) {
                if (board.getTile(x, y).getStatus().equalsIgnoreCase("M")) {
                    count += 1;
                }
            }
        }
        Assertions.assertEquals(board.getNumOfMines(), count, "Mines in board are not equal.");
    }

    @Test
    public void testOpenTile() {
        Board board = new Board(10);
        board.fillBoard();
        int count = 0;
        for (int y = 0; y < board.getBoardSize(); y++) {
            for (int x = 0; x < board.getBoardSize(); x++) {
                board.openTile(x, y, null);
                if (board.getTile(x, y).getVisible()) {
                    count += 1;
                }
            }
        }
        Assertions.assertEquals(count, board.getBoardSize() * board.getBoardSize(), "Not all tiles are being made visible.");
    }

    @Test
    public void testCheckIfExists() {
        List<Integer> newList = new ArrayList<Integer>();
        Board board = new Board(10);
        newList.add(1);
        newList.add(2);
        newList.add(3);
        newList.add(4);
        Assertions.assertTrue(board.checkIfAlreadyExists(newList, 1, 2));
        Assertions.assertTrue(board.checkIfAlreadyExists(newList, 3, 4));
        Assertions.assertFalse(board.checkIfAlreadyExists(newList, 2, 3));
        Assertions.assertFalse(board.checkIfAlreadyExists(newList, 1, 3));
    }
}
