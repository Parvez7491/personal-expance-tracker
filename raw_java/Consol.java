package raw_java;

import java.util.*;

public class Consol {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.print("Input text: ");
        String raw = sc.nextLine();

        String[] fetch = devideData(raw);

        for (String s : fetch) {
            System.out.println(s);
        }
    }

    private static String[] devideData(String raw) {
        String[] result = new String[4];
        for (int i = 0; i < 4; i++) {
            result[i] = "";
        }
        int len = raw.length();
        boolean in_quote = false;

        for (int i = 0, j = 0; i < len; i++) {
            if (raw.charAt(i) == '\"') {
                in_quote = in_quote ? false : true;
                continue;
            }
            if (raw.charAt(i) == ',' && !in_quote) {
                j++;
                continue;
            }

            result[j] += raw.charAt(i);

        }

        return result;

    }
}