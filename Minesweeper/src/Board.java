import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.Function;

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
        for (int y = 1; y < boardSize + 1; y++) {
            System.out.println("\n");
            System.out.print(y);
            for (int x = 1; x < boardSize + 1; x++) {
                if (board[x - 1][y - 1].getVisible()) {
                    System.out.print(" " + "|" + board[x - 1][y - 1].getStatus() + "|");
                } else {
                    System.out.print(" " + "|" + "U" + "|");
                }
            }
        }
        System.out.println("\n");
        for (int x = 1; x < boardSize + 1; x++) {
            System.out.print("   " + x);
        }
        System.out.println("\n");
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
                        board[r][c].setStatus("B");
                    } else {
                        board[r][c].setStatus(String.valueOf(count));
                    }
                }
            }
        }
    }

    public void openTile(int _x, int _y) {
        board[_x][_y].setVisible(true);
        if (board[_x][_y].getStatus().equalsIgnoreCase("B")) {
//            for (int i = _x - 1; i < _x + 2; _x++) {
//                for (int j = _y - 1; j < _y + 2; _y++) {
//                    if (board[i][j].getStatus().equalsIgnoreCase("B")) {
//                        board[i][j].setVisible(true);
//                    }
//                }
//            }
            for (int r = 0; r < boardSize; r++) {
                for (int c = 0; c < boardSize; c++) {
                    if (r == 0 && c == 0) { // top left corner
                        if (board[r + 1][c].getStatus().equalsIgnoreCase("B")) {
                            board[r + 1][c].setVisible(true);
                        }
                        if (board[r + 1][c + 1].getStatus().equalsIgnoreCase("B")) {
                            board[r + 1][c + 1].setVisible(true);
                        }
                        if (board[r][c + 1].getStatus().equalsIgnoreCase("B")) {
                            board[r][c + 1].setVisible(true);
                        }
                    }
                    if (r == boardSize - 1 && c == 0) { // top right corner
                        if (board[r - 1][c].getStatus().equalsIgnoreCase("B")) {
                            board[r - 1][c].setVisible(true);
                        }
                        if (board[r - 1][c + 1].getStatus().equalsIgnoreCase("B")) {
                            board[r - 1][c + 1].setVisible(true);
                        }
                        if (board[r][c + 1].getStatus().equalsIgnoreCase("B")) {
                            board[r][c + 1].setVisible(true);
                        }
                    }
                    if (r == boardSize - 1 && c == boardSize - 1) { // bottom right corner
                        if (board[r - 1][c].getStatus().equalsIgnoreCase("B")) {
                            board[r - 1][c].setVisible(true);
                        }
                        if (board[r - 1][c - 1].getStatus().equalsIgnoreCase("B")) {
                            board[r - 1][c - 1].setVisible(true);
                        }
                        if (board[r][c - 1].getStatus().equalsIgnoreCase("B")) {
                            board[r - 1][c - 1].setVisible(true);
                        }
                    }
                    if (r == 0 && c == boardSize - 1) { // bottom left corner
                        if (board[r + 1][c].getStatus().equalsIgnoreCase("B")) {
                            board[r + 1][c].setVisible(true);
                        }
                        if (board[r + 1][c - 1].getStatus().equalsIgnoreCase("B")) {
                            board[r + 1][c - 1].setVisible(true);
                        }
                        if (board[r][c - 1].getStatus().equalsIgnoreCase("B")) {
                            board[r][c - 1].setVisible(true);
                        }
                    }
                    if ((r != 0 && c != 0) && r != boardSize - 1 && c != boardSize - 1) { // middle
                        // (-1,0/+1,0)  (-1,-1/0,-1/+1,-1)  (-1,+1/0,+1/+1,+1)
                        if (board[r - 1][c].getStatus().equalsIgnoreCase("B")) {
                            board[r - 1][c].setVisible(true);
                        }
                        if (board[r + 1][c].getStatus().equalsIgnoreCase("B")) {
                            board[r + 1][c].setVisible(true);
                        }
                        if (board[r - 1][c + 1].getStatus().equalsIgnoreCase("B")) {
                            board[r - 1][c + 1].setVisible(true);
                        }
                        if (board[r][c + 1].getStatus().equalsIgnoreCase("B")) {
                            board[r][c + 1].setVisible(true);
                        }
                        if (board[r + 1][c + 1].getStatus().equalsIgnoreCase("B")) {
                            board[r + 1][c + 1].setVisible(true);
                        }
                        if (board[r - 1][c - 1].getStatus().equalsIgnoreCase("B")) {
                            board[r - 1][c - 1].setVisible(true);
                        }
                        if (board[r][c - 1].getStatus().equalsIgnoreCase("B")) {
                            board[r][c - 1].setVisible(true);
                        }
                        if (board[r + 1][c - 1].getStatus().equalsIgnoreCase("B")) {
                            board[r + 1][c - 1].setVisible(true);
                        }

                    }
                    if (c == 0 && r < boardSize - 1 && r > 0) { // top edge
                        if (board[r - 1][c].getStatus().equalsIgnoreCase("B")) {
                            board[r - 1][c].setVisible(true);
                        }
                        if (board[r - 1][c + 1].getStatus().equalsIgnoreCase("B")) {
                            board[r - 1][c + 1].setVisible(true);
                        }
                        if (board[r][c + 1].getStatus().equalsIgnoreCase("B")) {
                            board[r][c + 1].setVisible(true);
                        }
                        if (board[r + 1][c + 1].getStatus().equalsIgnoreCase("B")) {
                            board[r + 1][c + 1].setVisible(true);
                        }
                        if (board[r + 1][c].getStatus().equalsIgnoreCase("B")) {
                            board[r + 1][c].setVisible(true);
                        }
                    }

                    if (r == 0 && c < boardSize - 1 && c > 0) { // left edge
                        if (board[r][c - 1].getStatus().equalsIgnoreCase("B")) {
                            board[r][c - 1].setVisible(true);
                        }
                        if (board[r + 1][c - 1].getStatus().equalsIgnoreCase("B")) {
                            board[r + 1][c - 1].setVisible(true);
                        }
                        if (board[r + 1][c].getStatus().equalsIgnoreCase("B")) {
                            board[r + 1][c].setVisible(true);
                        }
                        if (board[r + 1][c + 1].getStatus().equalsIgnoreCase("B")) {
                            board[r + 1][c + 1].setVisible(true);
                        }
                        if (board[r][c + 1].getStatus().equalsIgnoreCase("B")) {
                            board[r][c + 1].setVisible(true);
                        }
                    }

                    if (r == boardSize - 1 && c < boardSize - 1 && c > 0) { // right edge
                        if (board[r][c - 1].getStatus().equalsIgnoreCase("B")) {
                            board[r][c - 1].setVisible(true);
                        }
                        if (board[r - 1][c - 1].getStatus().equalsIgnoreCase("B")) {
                            board[r - 1][c - 1].setVisible(true);
                        }
                        if (board[r - 1][c].getStatus().equalsIgnoreCase("B")) {
                            board[r - 1][c].setVisible(true);
                        }
                        if (board[r - 1][c + 1].getStatus().equalsIgnoreCase("B")) {
                            board[r - 1][c + 1].setVisible(true);
                        }
                        if (board[r][c + 1].getStatus().equalsIgnoreCase("B")) {
                            board[r][c + 1].setVisible(true);
                        }
                    }
                    if (c == boardSize - 1 && r < boardSize - 1 && r > 0) { // bottom edge
                        if (board[r - 1][c].getStatus().equalsIgnoreCase("B")) {
                            board[r - 1][c].setVisible(true);
                        }
                        if (board[r - 1][c - 1].getStatus().equalsIgnoreCase("B")) {
                            board[r - 1][c - 1].setVisible(true);
                        }
                        if (board[r][c - 1].getStatus().equalsIgnoreCase("B")) {
                            board[r][c - 1].setVisible(true);
                        }
                        if (board[r + 1][c - 1].getStatus().equalsIgnoreCase("B")) {
                            board[r + 1][c - 1].setVisible(true);
                        }
                        if (board[r + 1][c].getStatus().equalsIgnoreCase("B")) {
                            board[r + 1][c].setVisible(true);
                        }
                    }
                }
            }
        }
    }

    private boolean checkIfAlreadyExists(List<Integer> listOfMines, int xValue, int yValue) {
        for (int x = 0; x < listOfMines.size() * 2; x += 2) {
            return listOfMines.get(x) == xValue && listOfMines.get(x + 1) == yValue;
        }
        return true;
    }

//    @FunctionalInterface
//    interface Adjacent {
//        void apply();
//    }
//    public static void runAdjacentCheck(Function func) {
//        func.apply();
//    }
}
