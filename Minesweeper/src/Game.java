import java.util.Scanner;

public class Game {
    private boolean gameEnded;
    private int turn = 1;

    public void StartGame(int _numOfRowCols, Scanner _reader) {
        Board gameBoard = new Board(_numOfRowCols);

        while (!gameEnded) {

            gameBoard.generateBoard(turn);

            int boxInputX;
            int boxInputY;
            while (true) {

                System.out.println("Which box would you like to choose? X");
                try {
                    boxInputX = Integer.parseInt(_reader.next());

                    if (boxInputX > gameBoard.getBoardSize() || boxInputX <= 0) {
                        throw new Exception();
                    }
                    break;
                    // check for numbers - not decimal + not negative
                } catch (Exception e) {
                    System.out.println("Please try again. Enter a positive integer that is not bigger than the board size");
                }
            }
            while (true) {

                System.out.println("Which box would you like to choose? Y");
                try {
                    boxInputY = Integer.parseInt(_reader.next());

                    if (boxInputY > gameBoard.getBoardSize() || boxInputY <= 0) {
                        throw new Exception();
                    }
                    break;
                    // check for numbers - not decimal + not negative
                } catch (Exception e) {
                    System.out.println("Please try again. Enter a positive integer that is not bigger than the board size");
                }
            }

            String statusInput = "";
            while (true) {
                try {
                    System.out.println("What would you like to change the box to? (F)lagged or (C)licked");
                    statusInput = _reader.next();
                    if (!statusInput.equalsIgnoreCase("C") && !statusInput.equalsIgnoreCase("F")) {
                        throw new Exception();
                    }
                    break;
                } catch (Exception e) {
                    System.out.println("Please try again. In the format of 'F' or 'C'");
                }
            }
            playerTurn(boxInputX,boxInputY, statusInput, gameBoard);
            if (checkWin(gameBoard)) {
                System.out.println("You have won!");
                gameEnded = true;
            }
        }
    }

    private void playerTurn(int _boxInputX,int _boxInputY, String _statusInput, Board _gameBoard) {
        if (_statusInput.equalsIgnoreCase("C")) {
            if (_gameBoard.getTile(_boxInputX - 1, _boxInputY - 1).getHasMine()) {
                gameEnded = true;
                System.out.println("Game ended. You clicked on a mine.");
            } else {
                _gameBoard.openTile(_boxInputX - 1, _boxInputY - 1);
            }
        } else if (_statusInput.equalsIgnoreCase("F")) {
            _gameBoard.getTile(_boxInputX - 1, _boxInputY - 1).setBeenFlagged(true);
        }

        turn++;
    }
    private static boolean checkWin(Board _gameBoard) {
        int flagCount = 0;
        int clickCount = 0;
        for (int i = 0; i < _gameBoard.getBoardSize(); i++) {
            for (int j = 0; j < _gameBoard.getBoardSize(); j++) {
                if (_gameBoard.getTile(i, j).getStatus().equalsIgnoreCase("F")) {
                    flagCount += 1;
                }
                if (_gameBoard.getTile(i, j).getVisible()) {
                    clickCount += 1;
                }
            }
        }
        if (clickCount == _gameBoard.getBoardSize() * _gameBoard.getBoardSize()) {
            if (flagCount == _gameBoard.getNumOfMines()) {
                return true;
            }
        }
        return false;
//        return (clickCount + flagCount == _gameBoard.getBoardSize() * _gameBoard.getBoardSize()) &&
//                flagCount == _gameBoard.getNumOfMines();
    }
}
