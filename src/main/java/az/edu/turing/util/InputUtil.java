package az.edu.turing.util;

import java.util.Scanner;

public class InputUtil {

    private InputUtil(){}

    public static String getText(String title) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(title + ": ");
        return scanner.nextLine();
    }

    public static int getNumber(String title) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(title + ": ");
        return scanner.nextInt();
    }
}
