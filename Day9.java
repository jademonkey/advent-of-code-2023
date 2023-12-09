import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Day1
 */
public class Day9 {
    static final String EXAMPLE_INPUT_1 = "E:/git/src/jademonkey/advent-of-code-2023/ExampleInputs/Day9";

    static final String ACTUAL_INPUT_1 = "E:/git/src/jademonkey/advent-of-code-2023/Inputs/Day9";

    public static void main(String[] args) throws IOException {
        ArrayList<Lines> lines = new ArrayList<Lines>();
        /* Read each line */
        BufferedReader br = new BufferedReader(
                new FileReader(ACTUAL_INPUT_1));
        try {
            while (true) {
                String line = br.readLine();
                if (line == null) {
                    /* EOF */
                    break;
                }
                String splitted[] = line.split(" ");
                long vals[] = new long[splitted.length];
                for (int i = 0; i < vals.length; i++) {
                    vals[i] = Integer.parseInt(splitted[i]);
                }
                lines.add(new Lines(vals));
            }

            long p1a = 0;
            for (Lines l : lines) {
                long nextval = l.calculateNext();
                // l.printme();
                System.out.println("Next val: " + nextval);
                p1a += nextval;
            }
            System.out.println("Part 1 = " + p1a);

            long p2a = 0;
            for (Lines l : lines) {
                long nextval = l.calculatePrev();
                l.printme();
                System.out.println("Prev val: " + nextval);
                p2a += nextval;
            }
            System.out.println("Part 2 = " + p2a);

        } finally {
            br.close();
        }
    }

    public static class Lines {
        long baseValues[];
        long next = 0;
        long prev = 0;
        ArrayList<long[]> differences = new ArrayList<long[]>();

        public Lines(long base[]) {
            baseValues = base;
            calculateDiffs();
            // printme();
        }

        public void calculateDiffs() {
            long nextDiff[] = new long[baseValues.length - 1];
            for (int i = 0; i < nextDiff.length; i++) {
                nextDiff[i] = baseValues[i + 1] - baseValues[i];
            }
            differences.add(nextDiff);
            boolean continueY = false;
            for (long c : nextDiff) {
                if (c != 0) {
                    continueY = true;
                    break;
                }
            }
            if (!continueY)
                return;

            int curD = 0;
            while (true) {
                long prevDiff[] = differences.get(curD);
                nextDiff = new long[prevDiff.length - 1];
                for (int i = 0; i < nextDiff.length; i++) {
                    nextDiff[i] = prevDiff[i + 1] - prevDiff[i];
                }
                continueY = false;
                for (long c : nextDiff) {
                    if (c != 0) {
                        continueY = true;
                        break;
                    }
                }
                if (!continueY)
                    break;

                differences.add(nextDiff);
                curD++;
            }
        }

        public long calculateNext() {
            long add = 0;
            for (int i = differences.size() - 1; i >= 0; i--) {
                long diff[] = differences.get(i);
                add += diff[diff.length - 1];
            }

            next = baseValues[baseValues.length - 1] + add;
            return next;
        }

        public long calculatePrev() {
            long min = 0;
            for (int i = differences.size() - 1; i >= 0; i--) {
                long diff[] = differences.get(i);
                min = diff[0] - min;
            }

            prev = baseValues[0] - min;
            return prev;
        }

        public void printme() {
            System.out.print("base: ");
            for (long i : baseValues) {
                System.out.print(i + " ");
            }
            System.out.println();
            for (int i = 0; i < differences.size(); i++) {
                System.out.print(i + ": ");
                for (long d : differences.get(i)) {
                    System.out.print(d + " ");

                }
                System.out.println();
            }

        }
    }
}
