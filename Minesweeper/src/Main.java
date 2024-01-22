import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Key: U = unopened, F = flagged, B = blank");
        int numOfRowCols;
        System.out.println("How many rows/columns would you like to play with?");
        Scanner reader = new Scanner(System.in);
        while (true) {
            try {
                numOfRowCols = Integer.parseInt(reader.next());
                if (numOfRowCols <= 1) {
                    throw new Exception();
                }
                break;
            }catch (Exception e) {
                System.out.println("Please enter a positive integer bigger than 1.");
            }
        }

        Game newGame = new Game();
        newGame.StartGame(numOfRowCols, reader);
    }
}