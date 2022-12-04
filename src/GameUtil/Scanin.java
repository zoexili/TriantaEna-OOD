package GameUtil;

import java.util.Scanner;

public class Scanin {
    static Scanner scan = new Scanner(System.in);

    public Scanin() {

    }

    public static String ScanString() {
        return scan.next();
    }

    static void ScanClose() {
        scan.close();
    }

    static int ScanInt() {
        return scan.nextInt();
    }

    public static String ScanLine() {
        return scan.nextLine();
    }
}

