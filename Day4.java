import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Day4
 */
public class Day4 {
    static final String EXAMPLE_INPUT_1 = "E:/git/src/jademonkey/advent-of-code-2023/ExampleInputs/Day4";

    static final String ACTUAL_INPUT_1 = "E:/git/src/jademonkey/advent-of-code-2023/Inputs/Day4";

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
                /*  TODO */
                
            }
        } finally {
            br.close();
        }
    }
}
