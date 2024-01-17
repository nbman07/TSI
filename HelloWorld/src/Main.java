import org.w3c.dom.ls.LSOutput;

public class Main {
    public static void main(String[] args) {

        String weekday = "";

        for (int d = 0; d < 31; d+=3) {
            switch (d % 7) {
                case 1:
                    weekday = "Monday";
                    break;
                case 2:
                    weekday = "Tuesday";
                    break;
                case 3:
                    weekday = "Wednesday";
                    break;
                case 4:
                    weekday = "Thursday";
                    break;
                case 5:
                    weekday = "Friday";
                    break;
                case 6:
                    weekday = "Saturday";
                    break;
                case 7:
                    weekday = "Sunday";
                    break;
            }
            System.out.println("Day " + d + ", " + weekday);
        }
    }
}