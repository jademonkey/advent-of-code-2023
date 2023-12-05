import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Day1
 */
public class Day5 {
    static final String EXAMPLE_INPUT = "E:/git/src/jademonkey/advent-of-code-2023/ExampleInputs/Day5";

    static final String ACTUAL_INPUT = "E:/git/src/jademonkey/advent-of-code-2023/Inputs/Day5";

    public static void main(String[] args) throws IOException {
        ArrayList<Long> seedsList = new ArrayList<Long>();
        ArrayList<Range> seedToSoilList = new ArrayList<Range>();
        ArrayList<Range> soilToFert = new ArrayList<Range>();
        ArrayList<Range> fertToWater = new ArrayList<Range>();
        ArrayList<Range> WaterToLight = new ArrayList<Range>();
        ArrayList<Range> LightToTemp = new ArrayList<Range>();
        ArrayList<Range> TempToHumi = new ArrayList<Range>();
        ArrayList<Range> HumiToLoca = new ArrayList<Range>();

        /* Dest sour len */

        /* Read each line */
        BufferedReader br = new BufferedReader(
                new FileReader(ACTUAL_INPUT));
        try {
            String line = br.readLine();
            // seeds
            String seedsS[] = line.split(" ");
            for (int i = 1; i < seedsS.length; i++) {
                seedsList.add(Long.parseLong(seedsS[i]));
            }

            br.readLine(); // new line
            br.readLine(); // seed to soil tag
            seedToSoilList = getRanges(br);
            br.readLine(); // soil to fert tag
            soilToFert = getRanges(br);
            br.readLine(); // fert to water
            fertToWater = getRanges(br);
            br.readLine(); // water to light
            WaterToLight = getRanges(br);
            br.readLine(); // light to Temp
            LightToTemp = getRanges(br);
            br.readLine(); // Temp to humi
            TempToHumi = getRanges(br);
            br.readLine(); // hum to loc
            HumiToLoca = getRanges(br);

            // System.out.print("Seeds=");
            // for (int i = 0; i < seedsList.size(); i++) {
            // System.out.print(seedsList.get(i) + ", ");
            // }
            // System.out.println();
            // System.out.println("SoilToSeedRanges:");
            // for (Range r : seedToSoilList) {
            // System.out.println(r.toString());
            // }
            // System.out.println("humidity to Loca:");
            // for (Range r : HumiToLoca) {
            // System.out.println(r.toString());
            // }

            /* solve part 1 */
            long p1a = Long.MAX_VALUE;
            for (Long seed : seedsList) {
                long soil = findDest(seed, seedToSoilList);
                long fert = findDest(soil, soilToFert);
                long water = findDest(fert, fertToWater);
                long light = findDest(water, WaterToLight);
                long temp = findDest(light, LightToTemp);
                long humi = findDest(temp, TempToHumi);
                long location = findDest(humi, HumiToLoca);
                if (location < p1a) {
                    p1a = location;
                }
                // System.out.println(String.format("Seed(%d) soil(%d) fert(%d) water(%d)
                // light(%d) temp(%d) humi(%d) Location(%d)", seed,soil, fert, water, light,
                // temp,humi, location));
            }
            System.out.println("Part 1 solution = " + p1a);

            /* Solve part 2 */
            long p2a = Long.MAX_VALUE;
            for (int i = 0; i < seedsList.size(); i++) {
                long start = seedsList.get(i);
                i++;
                long end = seedsList.get(i) + start;
                for (long l = start; l < end; l++) {
                    long soil = findDest(l, seedToSoilList);
                    long fert = findDest(soil, soilToFert);
                    long water = findDest(fert, fertToWater);
                    long light = findDest(water, WaterToLight);
                    long temp = findDest(light, LightToTemp);
                    long humi = findDest(temp, TempToHumi);
                    long location = findDest(humi, HumiToLoca);
                    if (location < p2a) {
                        p2a = location;
                    }
                }
            }
            System.out.println("Part 2 solution = " + p2a);

        } finally {
            br.close();
        }
    }

    public static ArrayList<Range> getRanges(BufferedReader br) throws IOException {
        ArrayList<Range> returnl = new ArrayList<Range>();
        while (true) {
            String line = br.readLine();
            if (line == null || line.length() == 0) {
                break; // new line
            }
            String rSplit[] = line.split(" ");
            long dest = Long.parseLong(rSplit[0]);
            long sour = Long.parseLong(rSplit[1]);
            long len = Long.parseLong(rSplit[2]);
            Range r = new Range(dest, sour, len);
            returnl.add(r);
        }
        return returnl;
    }

    public static long findDest(long input, ArrayList<Range> rangeToSearch) {
        long output = input;
        for (Range r : rangeToSearch) {
            output = r.returnDest(output);
            if (output != input) {
                break;
            }
        }
        return output;
    }

    static class Range {
        long dest;
        long sour;
        long len;

        public Range(long dest, long sour, long len) {
            this.dest = dest;
            this.sour = sour;
            this.len = len;
        }

        public long returnDest(long input) {
            if (input < sour || input > (sour + len)) {
                return input;
            }
            return (input - sour) + dest;
        }

        public String toString() {
            return String.format("dest(%d),source(%d),len(%d)", dest, sour, len);
        }
    }
}
