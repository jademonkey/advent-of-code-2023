import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Day1
 */
public class Day1 {
    static final String EXAMPLE_INPUT_1 = "E:/git/src/jademonkey/advent-of-code-2023/ExampleInputs/Day1";

    static final String ACTUAL_INPUT_1 = "E:/git/src/jademonkey/advent-of-code-2023/Inputs/Day1";

    public static void main(String[] args) throws IOException {
        ArrayList<String> numbers = new ArrayList<String>();
        /* Read each line */
        BufferedReader br = new BufferedReader(
                new FileReader(ACTUAL_INPUT_1));
        try {
            while (true) {
                String num = "";
                String line = br.readLine();
                if (line == null) {
                    /* EOF */
                    break;
                }
                /* find first */
                for (int i = 0; i < line.length(); i++) {
                    char c = line.charAt(i);
                    if (Character.isDigit(c)) {
                        num += c;
                        break;
                    }
                    int pos = checkForSubStringDigit(line.substring(i));
                    if (pos != -1) {
                        num += pos;
                        break;
                    }
                }
                /* find last */
                for (int i = line.length() - 1; i >= 0; i--) {
                    char c = line.charAt(i);
                    if (Character.isDigit(c)) {
                        num += c;
                        break;
                    }
                    int pos = checkForSubStringDigit(line.substring(i));
                    if (pos != -1) {
                        num += pos;
                        break;
                    }
                }

                System.out.println(num);
                numbers.add(num);
            }
        } finally {
            br.close();
        }

        /** Now work out total */
        int total = 0;
        for (String s : numbers) {
            total += Integer.parseInt(s);
        }

        System.out.println("Total = " + total);
    }

    /**
     * one
     * two
     * six
     * 
     * four
     * five
     * nine
     * 
     * three
     * seven
     * eight
     * 
     */

    public static int checkForSubStringDigit(String s) {
        int stlen = s.length();
        if (stlen >= 5) { /* Can check 3, 7, 8 */
            if ("three".equalsIgnoreCase(s.substring(0, 5))) {
                return 3;
            }
            if ("seven".equalsIgnoreCase(s.substring(0, 5))) {
                return 7;
            }
            if ("eight".equalsIgnoreCase(s.substring(0, 5))) {
                return 8;
            }
        }
        if (stlen >= 4) { /* Can check 4, 5, 9 */
            if ("four".equalsIgnoreCase(s.substring(0, 4))) {
                return 4;
            }
            if ("five".equalsIgnoreCase(s.substring(0, 4))) {
                return 5;
            }
            if ("nine".equalsIgnoreCase(s.substring(0, 4))) {
                return 9;
            }
        }
        if (stlen >= 3) { /* Can check 1, 2, 6 */
            if ("one".equalsIgnoreCase(s.substring(0, 3))) {
                return 1;
            }
            if ("two".equalsIgnoreCase(s.substring(0, 3))) {
                return 2;
            }
            if ("six".equalsIgnoreCase(s.substring(0, 3))) {
                return 6;
            }
        }
        /* no match! */

        return -1;
    }
}
