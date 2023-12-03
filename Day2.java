import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Day2
 */
public class Day2 {
    static final String EXAMPLE_INPUT_1 = "E:/git/src/jademonkey/advent-of-code-2023/ExampleInputs/Day2";

    static final String ACTUAL_INPUT_1 = "E:/git/src/jademonkey/advent-of-code-2023/Inputs/Day2";

    public static void main(String[] args) throws IOException {
        /* Read each line */
        ArrayList<ArrayList<Game>> games = new ArrayList<ArrayList<Game>>();
        BufferedReader br = new BufferedReader(
                new FileReader(EXAMPLE_INPUT_1));
        try {
            while (true) {
                String line = br.readLine();
                if (line == null) {
                    /* EOF */
                    break;
                }

                ArrayList<Game> thisGames = new ArrayList<Game>();
                String gamesLine = line.substring(line.indexOf(':') + 1).trim();
                // System.out.println(gamesLine);

                // split
                String[] gamesI = gamesLine.split(";");
                for (String t : gamesI) {
                    Game thisGame = new Game();
                    String[] nums = t.trim().split(" ");
                    System.out.println(t);
                    for (int x = 0; x < nums.length; x++) {
                        // System.out.println("["+x+"]="+nums[x]);
                        int thisNum = Integer.parseInt(nums[x]);
                        x++;
                        String val = nums[x].replaceAll(",", "").trim();
                        if (val.equalsIgnoreCase("red")) {
                            thisGame.setRed(thisNum);
                        } else if (val.equalsIgnoreCase("blue")) {
                            thisGame.setBlue(thisNum);
                        } else {
                            thisGame.setGreen(thisNum);
                        }
                    }
                    thisGames.add(thisGame);
                }
                games.add(thisGames);
            }

            int impossibelGametots = 0;
            int possibelGametots = 0;
            /** now find the part 1 solution... */
            for (int i = 0; i < games.size(); i++) {
                if (testPossible(games.get(i))) {
                    // System.out.println("Game " + (i+1) + " possible.");
                    possibelGametots += (i + 1);
                } else {
                    // System.out.println("Game " + (i+1) + " not possible.");
                    impossibelGametots += (i + 1);
                }
            }

            System.out.println("total for part 1 = " + possibelGametots);

            int powers = 0;
            for (int i = 0; i < games.size(); i++) {
                int thisPower = findMinCubes(games.get(i));
                System.out.println("Game " + (i + 1) + " = " + thisPower);
                powers += thisPower;
            }

            System.out.println("Total for part 2 = " + powers);

        } finally {
            br.close();
        }
    }

    /** find possibilities for part 1 */
    public static boolean testPossible(ArrayList<Game> g) {
        for (Game gam : g) {
            if (gam.getBlue() > 14) {
                return false;
            } else if (gam.getGreen() > 13) {
                return false;
            } else if (gam.getRed() > 12) {
                return false;
            }
        }
        return true;
    }

    /** find min cube numbers for part 2 */
    public static int findMinCubes(ArrayList<Game> g) {
        int minRed = 0;
        int minBlue = 0;
        int minGreen = 0;
        for (Game gam : g) {
            int b = gam.getBlue();
            int gr = gam.getGreen();
            int r = gam.getRed();
            if (r > minRed) {
                minRed = r;
            }
            if (b > minBlue) {
                minBlue = b;
            }
            if (gr > minGreen) {
                minGreen = gr;
            }

        }
        return (minRed * minBlue * minGreen);
    }

    /**
     * InnerDay2
     */
    public static class Game {
        private int redRevealed = 0;
        private int blueRevealed = 0;
        private int greenrevealed = 0;

        public Game() {
        }

        public int getRed() {
            return redRevealed;
        }

        public int getBlue() {
            return blueRevealed;
        }

        public int getGreen() {
            return greenrevealed;
        }

        public void setRed(int n) {
            redRevealed = n;
        }

        public void setBlue(int n) {
            blueRevealed = n;
        }

        public void setGreen(int n) {
            greenrevealed = n;
        }
    }
}
