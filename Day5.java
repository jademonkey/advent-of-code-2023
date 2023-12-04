import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Day1
 */
public class Day1 {
    static final String EXAMPLE_INPUT = "E:/git/src/jademonkey/advent-of-code-2023/ExampleInputs/Day5";

    static final String ACTUAL_INPUT = "E:/git/src/jademonkey/advent-of-code-2023/Inputs/Day5";

    public static void main(String[] args) throws IOException {
        ArrayList<String> numbers = new ArrayList<String>();
        /* Read each line */
        BufferedReader br = new BufferedReader(
                new FileReader(ACTUAL_INPUT));
        try {
            while (true) {
                String line = br.readLine();
                if (line == null) {
                    /* EOF */
                    break;
                }
                /* TODO */

            }
        } finally {
            br.close();
        }
    }
}
