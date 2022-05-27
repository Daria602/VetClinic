import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String line = """
                Welcome!
                Would you like to use CSV or Database approach?
                1 - CSV
                2 - DATABASE
                """;
        System.out.println(line);
        String answer = scanner.nextLine();

        switch (answer){
            case "1":

                break;
            case "2":
                Menu menu = new Menu();
                menu.start();
                break;

            default:
                System.out.println("Action not recognised.");
        }


    }
}
