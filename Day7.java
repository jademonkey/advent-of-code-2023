import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Day1
 */
public class Day7 {
    static final String EXAMPLE_INPUT_1 = "E:/git/src/jademonkey/advent-of-code-2023/ExampleInputs/Day7";

    static final String ACTUAL_INPUT_1 = "E:/git/src/jademonkey/advent-of-code-2023/Inputs/Day7";

    public static void main(String[] args) throws IOException {
        /* Read each line */
        BufferedReader br = new BufferedReader(
                new FileReader(EXAMPLE_INPUT_1));
        try {
            while (true) {
               
                String line = br.readLine();
                if (line == null) {
                    /* EOF */
                    break;
                }
                /** TODO */
            }
        } finally {
            br.close();
        }
    }
}
