package resources;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Helper {

    public static String readLine() {
        String ergebnis = null;
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            ergebnis = br.readLine();
        } catch (IOException e) {
            // Dieser Fall sollte eigentlich nie eintreten.
            e.printStackTrace();
            System.exit(0);
        }
        return ergebnis;
    }

    public static int readInt() {
        boolean zahlGefunden = false;
        int ergebnis = 0;
        while (!zahlGefunden) {
            String s = Helper.readLine();
            try {
                ergebnis = Integer.parseInt(s);
                zahlGefunden = true;
            } catch (Exception e) {
                System.out.print("Das war keine ganze Zahl. Bitte versuchen Sie es erneut: ");
            }
        }
        return ergebnis;
    }

    public static double readDouble() {
        boolean zahlGefunden = false;
        double ergebnis = 0;
        while (!zahlGefunden) {
            String s = Helper.readLine();
            try {
                ergebnis = Double.parseDouble(s);
                zahlGefunden = true;
            } catch (Exception e) {
                System.out.print("Das war keine Zahl. Bitte versuchen Sie es erneut: ");
            }
        }
        return ergebnis;
    }

    public static void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }
}
