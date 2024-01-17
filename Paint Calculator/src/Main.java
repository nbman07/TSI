import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        float areaToPaint = 0;
        float dimensionsOfWall = 0;
        System.out.println("What is the square metre per litre listed on your paint can?");
        float squareMetrePerLitre = Float.parseFloat(reader.nextLine());
        System.out.println("What is the price of a paint can?");
        float paintPrice = Float.parseFloat(reader.nextLine());
        
        System.out.println("How many litres of paint is in a paint can?");
        float litresInPot = Float.parseFloat(reader.nextLine());
        System.out.println("Is the room square or rectangular? Y/N");
        String squareOrRectangular = reader.nextLine();
        if (squareOrRectangular.equalsIgnoreCase("y")) {
            System.out.println("What is the width of the wall in metres?");
            float lengthOfWall = Float.parseFloat(reader.nextLine());
            System.out.println("What is the height of the wall in metres?");
            float heightOfWall = Float.parseFloat(reader.nextLine());
            dimensionsOfWall = lengthOfWall * heightOfWall;
            areaToPaint = dimensionsOfWall;

        } else if (squareOrRectangular.equalsIgnoreCase("n")) {
            System.out.println("How many shapes can the wall be split into? Shapes can be one of the following:" +
                    " Square, Rectangle, Circle, Triangle");
            int numOfShapes = Integer.parseInt(reader.nextLine());
            String[] shapesInWall = new String[numOfShapes];
            for (int x = 1; x < numOfShapes + 1; x++) {
                System.out.println("What shape is shape " + x + "?");
                String shape = reader.nextLine();
                shapesInWall[x - 1] = shape;
                System.out.println("What is the width of shape " + x + "?");
                float shapeWidth = Float.parseFloat(reader.nextLine());
                System.out.println("What is the height of shape " + x + "?");
                float shapeHeight = Float.parseFloat(reader.nextLine());
                switch (shape.toLowerCase()) {
                    case "square":
                    case "rectangle":
                        areaToPaint += shapeWidth * shapeHeight;
                    case "triangle":
                        areaToPaint += (shapeWidth * shapeHeight) / 2;
                    case "circle":
                        areaToPaint += (float) ((shapeWidth / 2) * (shapeWidth / 2) * Math.PI);

                }
            }

        }
        System.out.println("Do you have any windows or doors on this wall? Y/N");
        String doorOrWindow = reader.nextLine();
        if (doorOrWindow.equalsIgnoreCase("y")) {
            System.out.println("Measure the windows/doors");
            System.out.println("What is the width of the window/door in metres?");
            float widthOfWindowOrDoor = Float.parseFloat(reader.nextLine());
            System.out.println("What is the length window/door in metres?");
            float lengthOfWindowOrDoor = Float.parseFloat(reader.nextLine());
            float areaOfWindowOrDoor = widthOfWindowOrDoor * lengthOfWindowOrDoor;

            areaToPaint = dimensionsOfWall - areaOfWindowOrDoor;
        }
        System.out.println("Total area to paint: " + areaToPaint);
        float litresOfPaintNeeded = areaToPaint / squareMetrePerLitre;
        System.out.println(litresOfPaintNeeded + " litres of paint needed");
        int numOfPaintPots = (int) Math.ceil(litresOfPaintNeeded / litresInPot);
        System.out.println("Cost of " + numOfPaintPots + " paint pots is £" + (paintPrice * numOfPaintPots));
    }
}