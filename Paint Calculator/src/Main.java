import java.util.Scanner;

public class Main {
    public static float calculateTriangle(float width, float height) {
        return (width * height) / 2;
    }

    public static float calculateSquOrRect(float width, float height) {
        return width * height;
    }

    public static float calculateCircle(float diameter) {
        return (float) ((diameter / 2) * Math.PI);
    }

    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        float areaToPaint = 0;
        float squareMetrePerLitre = 0;
        float paintPrice = 0;
        float litresInPaintCan = 0;
        String squareOrRectangular = "";
        float widthOfWall = 0;
        float heightOfWall = 0;
        float shapeWidth = 0;
        float shapeHeight = 0;

        while (true) {
            try {
                System.out.println("What is the square metre per litre listed on your paint can?");
                squareMetrePerLitre = Float.parseFloat(reader.next());
                if (squareMetrePerLitre <= 0) {
                    throw new RuntimeException();
                }
                break;
            } catch (Exception e) {
                System.out.println("Sorry, please enter a positive number."); //
            }
        }

        while (true) {
            try {
                System.out.println("What is the price of your paint can?");
                paintPrice = Float.parseFloat(reader.next());
                if (paintPrice <= 0) {
                    throw new RuntimeException();
                }
                break;
            } catch (Exception e) {
                System.out.println("Sorry, please enter a positive number."); //
            }
        }

        while (true) {
            try {
                System.out.println("How many litres of paint is in this paint can?");
                litresInPaintCan = Float.parseFloat(reader.next());
                if (litresInPaintCan <= 0) {
                    throw new RuntimeException();
                }
                break;
            } catch (Exception e) {
                System.out.println("Sorry, please enter a positive number."); //
            }
        }

        while (true) {
            System.out.println("Is the wall square or rectangular? Y/N");
            if (!reader.hasNext("y") && !reader.hasNext("n")) {
                System.out.println("Sorry, please answer with y or n.");
                reader.next();
            } else {
                squareOrRectangular = reader.next();
                break;
            }
        }

        while (true) {
            try {
                if (squareOrRectangular.equalsIgnoreCase("y")) {
                    while (true) {
                        try {
                            System.out.println("What is the width of the wall in metres?");
                            widthOfWall = Float.parseFloat(reader.next());
                            if (widthOfWall <= 0) {
                                throw new RuntimeException();
                            }
                            break;
                        } catch (Exception e) {
                            System.out.println("Sorry, please enter a positive number."); //
                        }
                    }
                    while (true) {
                        try {
                            System.out.println("What is the height of the wall in metres?");
                            heightOfWall = Float.parseFloat(reader.next());
                            if (heightOfWall <= 0) {
                                throw new RuntimeException();
                            }
                            break;
                        } catch (Exception e) {
                            System.out.println("Sorry, please enter a positive number."); //
                        }
                    }

                    areaToPaint = calculateSquOrRect(widthOfWall, heightOfWall);

                } else if (squareOrRectangular.equalsIgnoreCase("n")) {
                    System.out.println("How many shapes can the wall be split into? Shapes can be one of the following:" +
                            " Square, Rectangle, Circle, Triangle");

                    int numOfShapes = 0;

                    while (true) {
                        try {
                            numOfShapes = Integer.parseInt(reader.next());
                            if (numOfShapes <= 0) {
                                throw new RuntimeException();
                            }
                            break;
                        } catch (Exception e) {
                            System.out.println("Sorry, please enter a positive integer."); //
                        }
                    }
                    String[] shapesInWall = new String[numOfShapes];
                    String shape = "";
                    for (int x = 1; x < numOfShapes + 1; x++) {
                        while (true) {
                            try {
                                System.out.println("What shape is shape " + x + "?");
                                shape = reader.next();
                                if (!shape.equalsIgnoreCase("square") && !shape.equalsIgnoreCase("rectangle")
                                        && !shape.equalsIgnoreCase("triangle") && !shape.equalsIgnoreCase("circle")) {
                                    throw new RuntimeException();
                                }
                                break;
                            } catch (Exception e) {
                                System.out.println("Sorry, please enter a name one of the shapes listed above."); //
                            }
                        }

                        shapesInWall[x - 1] = shape;
                    }

                    for (int y = 1; y < shapesInWall.length + 1; y++) {
                        while (true) {
                            try {
                                System.out.println("What is the width of shape " + (y) + "?");
                                shapeWidth = Float.parseFloat(reader.next());
                                if (shapeWidth <= 0) {
                                    throw new RuntimeException();
                                }
                                break;
                            } catch (Exception e) {
                                System.out.println("Sorry, please enter a positive number."); //
                            }
                        }

                        while (true) {
                            try {
                                System.out.println("What is the height of shape " + (y) + "?");
                                shapeHeight = Float.parseFloat(reader.next());
                                if (shapeHeight <= 0) {
                                    throw new RuntimeException();
                                }
                                break;
                            } catch (Exception e) {
                                System.out.println("Sorry, please enter a positive number."); //
                            }
                        }


                        while (true) {

                            try {
                                switch (shapesInWall[y - 1].toLowerCase()) {
                                    case "square":
                                    case "rectangle":
//                                        widthOfWall += shapeWidth;
//                                        heightOfWall += shapeHeight;
                                        areaToPaint += calculateSquOrRect(shapeWidth, shapeHeight);
                                        break;
                                    case "triangle":
//                                        widthOfWall += shapeWidth;
//                                        heightOfWall += shapeHeight;
                                        areaToPaint += calculateTriangle(shapeWidth, shapeHeight);
                                        break;
                                    case "circle":
//                                        widthOfWall += shapeWidth;
//                                        heightOfWall += shapeHeight;
                                        areaToPaint += calculateCircle(shapeWidth);
                                        break;
                                    default:
                                        System.out.println("Sorry, that was not recognised. Please try again.");
                                }
                                widthOfWall += shapeWidth;
                                heightOfWall += shapeHeight;
                                break;
                            } catch (Exception e) {
                                System.out.println("Sorry, please enter the name of a shape listed above.");
                            }
                        }
                    }
                }
                break;
            } catch (Exception e) {
                System.out.println("Please ensure you answer with y or n.");
            }
        }

        String doorOrWindow = "";

        while (true) {
            try {
                System.out.println("Do you have any windows or doors on this wall? Y/N");
                doorOrWindow = reader.next();
                if (!doorOrWindow.equalsIgnoreCase("y") && !doorOrWindow.equalsIgnoreCase("n")) {
                    throw new RuntimeException();
                }
                break;
            } catch (Exception e) {
                System.out.println("Sorry, please answer with y or n."); //
            }
        }
        while (true) {
            try {
                if (doorOrWindow.equalsIgnoreCase("y")) {
                    float widthOfWindowOrDoor = 0;
                    float heightOfWindowOrDoor = 0;

                    System.out.println("Measure the windows/doors");

                    while (true) {
                        try {
                            System.out.println("What is the width of the window/door in metres?");
                            widthOfWindowOrDoor = Float.parseFloat(reader.next());
                            System.out.println(widthOfWall);
                            System.out.println(shapeWidth);
                            if ((widthOfWindowOrDoor <= 0) || (widthOfWindowOrDoor >= widthOfWall)) {
                                throw new RuntimeException();
                            }
                            break;
                        } catch (Exception e) {
                            System.out.println("Sorry, please enter a positive number and ensure that the window/door is not smaller than the wall.");
                        }
                    }

                    while (true) {
                        try {
                            System.out.println("What is the length window/door in metres?");
                            heightOfWindowOrDoor = Float.parseFloat(reader.next());
                            if (heightOfWindowOrDoor <= 0 || (heightOfWindowOrDoor >= heightOfWall)) {
                                throw new RuntimeException();
                            }
                            break;
                        } catch (Exception e) {
                            System.out.println("Sorry, please enter a positive number and ensure that the window/door is smaller than the wall.");
                        }
                    }

                    float areaOfWindowOrDoor = widthOfWindowOrDoor * heightOfWindowOrDoor;

                    areaToPaint -= areaOfWindowOrDoor;
                }
                break;
            } catch (Exception e) {
                System.out.println("Please answer with y or n.");
            }
        }

        System.out.println("Total area to paint: " + areaToPaint + "m^2");

        float litresOfPaintNeeded = areaToPaint / squareMetrePerLitre;
        System.out.println(litresOfPaintNeeded + " litres of paint needed");

        int numOfPaintPots = (int) Math.ceil(litresOfPaintNeeded / litresInPaintCan);

        System.out.println("Cost of " + numOfPaintPots + " paint pots is £" + (paintPrice * numOfPaintPots));
    }
}