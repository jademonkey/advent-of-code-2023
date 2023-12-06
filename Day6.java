import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Day1
 */
public class Day6 {
    static final String EXAMPLE_INPUT_1 = "E:/git/src/jademonkey/advent-of-code-2023/ExampleInputs/Day6";

    static final String ACTUAL_INPUT_1 = "E:/git/src/jademonkey/advent-of-code-2023/Inputs/Day6";

    public static void main(String[] args) throws IOException {
        ArrayList<Long> times = new ArrayList<>();
        ArrayList<Long> dist = new ArrayList<>();
        /* Read each line */
        BufferedReader br = new BufferedReader(
                new FileReader(ACTUAL_INPUT_1));
        try {
            boolean readTimes = true;
            while (true) {
                String line = br.readLine();
                if (line == null) {
                    /* EOF */
                    break;
                }
                /** TODO */
                String[] numbers = line.substring(line.indexOf(':') + 1).trim().split(" ");
                for (String s : numbers) {
                    if (s == null || s.equals(" ") || s.equals("")) {

                    } else {
                        if (readTimes) {
                            times.add(Long.parseLong(s));
                        } else {

                            dist.add(Long.parseLong(s));
                        }
                    }
                }
                readTimes = false;
            }
            // ok
            long p1a = 1;
            String bigTimeS = "";
            String bigDistS = "";
            for(int i = 0; i < times.size(); i++) {
                long time = times.get(i);
                long dis = dist.get(i);
                long min = calculateMlongime(time, dis);
                long max = calculateMaxTime(time, dis);
                long product = (max - min) +1;
                System.out.println(String.format("time(%d)dist(%d)min(%d)max(%d)mor(%d)", time, dis, min, max, product));
                p1a *= product;
                // for part 2
                bigTimeS += time;
                bigDistS += dis;
            }
            System.out.println("Part 1 solution = " + p1a);
            
            long bigTime = Long.parseLong(bigTimeS);
            long bigDist = Long.parseLong(bigDistS);

            long bigmin = calculateMlongime(bigTime, bigDist);
            long bigMax = calculateMaxTime(bigTime, bigDist);
            long bigProd = (bigMax - bigmin) +1;
            System.out.println(String.format("time(%d)dist(%d)min(%d)max(%d)mor(%d)", bigTime, bigDist, bigmin, bigMax, bigProd));
            System.out.println("Part 2 solution = " + bigProd);

        } finally {
            br.close();
        }
    }

    public static long calculateMlongime(long maxTime, long distance) {
        for (long i = 1; i < maxTime; i++) {
            if (i * (maxTime - i) > distance) {
                return i;
            }
        }
        return 0;
    }

    public static long calculateMaxTime(long maxTime, long distance) {
        for (long i = maxTime-1; i >= 0; i--) {
            if (i * (maxTime - i) > distance) {
                return i;
            }
        }
        return 0;
    }
}
