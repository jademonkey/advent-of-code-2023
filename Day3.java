import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Day3
 */
public class Day3 {
    static final String EXAMPLE_INPUT_1 = "E:/git/src/jademonkey/advent-of-code-2023/ExampleInputs/Day3";

    static final String ACTUAL_INPUT_1 = "E:/git/src/jademonkey/advent-of-code-2023/Inputs/Day3";

    public static void main(String[] args) throws IOException {
        /* Read each line */

        String input = ACTUAL_INPUT_1;
        int cols = findArrayLineSize(input);
        int rows = findArrayTotLinesSize(input);

        char charar[][] = new char[rows][cols];

        BufferedReader br = new BufferedReader(
                new FileReader(input));
        try {

            // Parse
            int curRow = 0;
            while (true) {
                String line = br.readLine();
                if (line == null) {
                    /* EOF */
                    break;
                }
                for (int i = 0; i < cols; i++) {
                    charar[curRow][i] = line.charAt(i);
                }
                curRow++;
            }

            printcharar(charar);

            // p1 answer
            ArrayList<Integer> p1a = findPart1Solution(charar);
            int p1atotal = 0;
            for (int i : p1a) {
                p1atotal += i;
            }
            System.out.println("Part 1=" + p1atotal);

            // p2 answer
            ArrayList<Integer> p2a = findPart2Solution(charar);
            int p2atotal = 0;
            for (int i : p2a) {
                p2atotal += i;
            }
            System.out.println("Part 2=" + p2atotal);


        } finally {
            br.close();
        }
    }

    public static int findArrayLineSize(String input) throws IOException {

        BufferedReader findArSize = new BufferedReader(
                new FileReader(input));
        try {

            String line = findArSize.readLine();
            if (line == null)
                return 0;
            else
                return line.length();
        } finally {
            findArSize.close();
        }
    }

    public static int findArrayTotLinesSize(String input) throws IOException {

        BufferedReader findArSize = new BufferedReader(
                new FileReader(input));
        int lines = 0;
        try {
            while (true) {
                String line = findArSize.readLine();
                if (line == null)
                    break;
                lines++;
            }
        } finally {
            findArSize.close();
        }
        return lines;
    }

    public static void printcharar(char[][] out) {
        int rowSize = out.length;
        int colsize = out[0].length;

        for (int c = 0; c < colsize; c++) {
            for (int r = 0; r < rowSize; r++) {
                System.out.print(out[c][r]);
            }
            System.out.println();
        }
    }

    public static ArrayList<Integer> findPart1Solution(char[][] in) {
        ArrayList<Integer> answer = new ArrayList<>();

        int rowSize = in.length;
        int colsize = in[0].length;

        for (int c = 0; c < colsize; c++) {
            for (int r = 0; r < rowSize; r++) {
                boolean foundStraightUp = false;

                if (isSymbol(in[c][r])) {
                    // Get number left
                    if (r > 0 && Character.isDigit(in[c][r - 1])) {
                        answer.add(getNum(in[c], r - 1));
                    }
                    // get number right
                    if (r < rowSize - 1 && Character.isDigit(in[c][r + 1])) {
                        answer.add(getNum(in[c], r + 1));
                    }

                    // Get number up
                    if (c > 0 && Character.isDigit(in[c - 1][r])) {
                        foundStraightUp = true;
                        answer.add(getNum(in[c - 1], r));
                    }
                    if (!foundStraightUp) {
                        // get numbed up left
                        if (c > 0 && r > 0 && Character.isDigit(in[c - 1][r - 1])) {
                            answer.add(getNum(in[c - 1], r - 1));
                        }
                        // get numbed up right
                        if (c > 0 && r < rowSize - 1 && Character.isDigit(in[c - 1][r + 1])) {
                            answer.add(getNum(in[c - 1], r + 1));
                        }
                    }

                    foundStraightUp = false;

                    // get number down
                    if (c < colsize - 1 && Character.isDigit(in[c + 1][r])) {
                        foundStraightUp = true;
                        answer.add(getNum(in[c + 1], r));
                    }

                    if (!foundStraightUp) {
                        // get numbed down left
                        if (c < colsize - 1 && r > 0 && Character.isDigit(in[c + 1][r - 1])) {
                            answer.add(getNum(in[c + 1], r - 1));
                        }
                        // get numbed down right
                        if (c < colsize - 1 && r < rowSize - 1 && Character.isDigit(in[c + 1][r + 1])) {
                            answer.add(getNum(in[c + 1], r + 1));
                        }
                    }

                }
            }
        }

        return answer;
    }

    public static boolean isSymbol(char c) {
        if (Character.isDigit(c)) {
            return false;
        }
        return c != '.';
    }

    public static int getNum(char[] in, int c) {
        int maxC = in.length;
        String s = "";
        // find the start
        while (true) {
            if (Character.isDigit(in[c])) {
                if (c == 0) {
                    // can go no further
                    break;
                } else {
                    c--;
                    continue;
                }
            } else {
                // found end!
                c++;
                break;
            }
        }
        // build the int string
        s += in[c];
        while (true) {
            c++;
            if (c < maxC) {
                if (Character.isDigit(in[c])) {
                    s += in[c];
                    continue;
                } else {
                    // end of numbers
                    break;
                }
            } else {
                // end of line
                break;
            }
        }

        return Integer.parseInt(s);
    }

    public static ArrayList<Integer> findPart2Solution(char[][] in) {
        ArrayList<Integer> answer = new ArrayList<>();

        int rowSize = in.length;
        int colsize = in[0].length;

        for (int c = 0; c < colsize; c++) {
            for (int r = 0; r < rowSize; r++) {
                ArrayList<Integer> numbers = new ArrayList<>();
                boolean foundStraightUp = false;

                if (isGear(in[c][r])) {
                    // Get number left
                    if (r > 0 && Character.isDigit(in[c][r - 1])) {
                        numbers.add(getNum(in[c], r - 1));
                    }
                    // get number right
                    if (r < rowSize - 1 && Character.isDigit(in[c][r + 1])) {
                        numbers.add(getNum(in[c], r + 1));
                    }

                    // Get number up
                    if (c > 0 && Character.isDigit(in[c - 1][r])) {
                        foundStraightUp = true;
                        numbers.add(getNum(in[c - 1], r));
                    }
                    if (!foundStraightUp) {
                        // get numbed up left
                        if (c > 0 && r > 0 && Character.isDigit(in[c - 1][r - 1])) {
                            numbers.add(getNum(in[c - 1], r - 1));
                        }
                        // get numbed up right
                        if (c > 0 && r < rowSize - 1 && Character.isDigit(in[c - 1][r + 1])) {
                            numbers.add(getNum(in[c - 1], r + 1));
                        }
                    }

                    foundStraightUp = false;

                    // get number down
                    if (c < colsize - 1 && Character.isDigit(in[c + 1][r])) {
                        foundStraightUp = true;
                        numbers.add(getNum(in[c + 1], r));
                    }

                    if (!foundStraightUp) {
                        // get numbed down left
                        if (c < colsize - 1 && r > 0 && Character.isDigit(in[c + 1][r - 1])) {
                            numbers.add(getNum(in[c + 1], r - 1));
                        }
                        // get numbed down right
                        if (c < colsize - 1 && r < rowSize - 1 && Character.isDigit(in[c + 1][r + 1])) {
                            numbers.add(getNum(in[c + 1], r + 1));
                        }
                    }

                }

                if(numbers.size()==2){
                    // we gold
                    int power = numbers.get(0) * numbers.get(1);
                    answer.add(power);
                }
            }
        }

        return answer;
    }

    public static boolean isGear(char c) {
        return c == '*';
    }
}
