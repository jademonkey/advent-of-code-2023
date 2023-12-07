import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * Day1
 */
public class Day7 {
    static final String EXAMPLE_INPUT_1 = "E:/git/src/jademonkey/advent-of-code-2023/ExampleInputs/Day7";

    static final String ACTUAL_INPUT_1 = "E:/git/src/jademonkey/advent-of-code-2023/Inputs/Day7";

    // 32T3K 1
    // KK677 2
    // T55J5 4
    // QQQJA 4
    // KTJJT 4

    public static void main(String[] args) throws Exception {
        ArrayList<Hand> hands = new ArrayList<Hand>();
        ArrayList<Hand> handsJokersLive = new ArrayList<Hand>();
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
                String[] splitted = line.split(" ");
                int bid = Integer.parseInt(splitted[1]);
                hands.add(new Hand(splitted[0], bid, false));
                handsJokersLive.add(new Hand(splitted[0], bid, true));
            }

            // order them
            Collections.sort(hands);
            Collections.reverse(hands);

            int totalWInnings = 0;
            for (int i = 0; i < hands.size(); i++) {
                totalWInnings += ((i + 1) * hands.get(i).bid);
            }
            System.out.println("Part 1 solution = " + totalWInnings);

            // sort by potential
            Collections.sort(handsJokersLive);
            Collections.reverse(handsJokersLive);

            totalWInnings = 0;
            for (int i = 0; i < handsJokersLive.size(); i++) {
             //   if (handsJokersLive.get(i).ContainsJokers)
                    System.out.println(handsJokersLive.get(i).toString());
                totalWInnings += ((i + 1) * handsJokersLive.get(i).bid);
            }
            System.out.println("Part 2 solution = " + totalWInnings);

        } finally {
            br.close();
        }
    }

    /**
     * 
     */

    public static final int HIGHCARD = 1;
    public static final int ONEPAIR = 2;
    public static final int TWOPAIR = 3;
    public static final int THREEOFKIND = 4;
    public static final int FULLHOUSE = 5;
    public static final int FOUROFKIND = 6;
    public static final int FIVEOFKIND = 7;

    public static class Hand implements Comparable {
        public int hand[] = new int[5];
        public int handType;
        public int bid;
        public boolean jokerslive = false;
        public boolean ContainsJokers = false;

        public Hand(String handRepresentation, int bid, boolean jokerslive) throws Exception {
            this.jokerslive = jokerslive;
            for (int i = 0; i < 5; i++) {
                char c = handRepresentation.charAt(i);
                switch (c) {
                    case 'A':
                        hand[i] = 14;
                        break;
                    case 'K':
                        hand[i] = 13;
                        break;
                    case 'Q':
                        hand[i] = 12;
                        break;
                    case 'J':
                        ContainsJokers = true;
                        hand[i] = 11;
                        break;
                    case 'T':
                        hand[i] = 10;
                        break;
                    default:
                        hand[i] = Integer.parseInt("" + c);
                        break;
                }

                if (jokerslive) {
                    handType = findPotenType(hand);
                } else {
                    handType = findType(hand);
                }

                this.bid = bid;
            }
        }

        private int findType(int[] hand) {
            HashMap<Integer, Integer> cards = new HashMap<Integer, Integer>();
            for (Integer i : hand) {
                if (cards.containsKey(i)) {
                    int val = cards.get(i);
                    cards.put(i, val + 1);
                } else {
                    cards.put(i, 1);
                }
            }

            int size = cards.size();
            switch (size) {
                case 1:
                    // can only be 5 of a kind
                    return FIVEOFKIND;
                case 2:
                    // Could be 4 of kind of full house ..
                    for (int i : cards.values()) {
                        if (i == 4) {
                            return FOUROFKIND;
                        }
                    }
                    return FULLHOUSE;
                case 3:
                    // could be three of kind or two pair
                    for (int i : cards.values()) {
                        if (i == 3) {
                            return THREEOFKIND;
                        }
                    }
                    return TWOPAIR;
                case 4:
                    // can only be one pair
                    return ONEPAIR;
                case 5:
                    // can only be high card
                    return HIGHCARD;
            }
            // should never get here!
            return 0;
        }

        private int findPotenType(int[] hand) throws Exception {
            HashMap<Integer, Integer> cards = new HashMap<Integer, Integer>();
            HashMap<Integer, Integer> cardsNoJokers = new HashMap<Integer, Integer>();
            for (Integer i : hand) {
                if (cards.containsKey(i)) {
                    int val = cards.get(i);
                    cards.put(i, val + 1);
                    if (i != 11) {
                        cardsNoJokers.put(i, val + 1);
                    }
                } else {
                    cards.put(i, 1);
                    if (i != 11) {
                        cardsNoJokers.put(i, 1);
                    }
                }
            }

            int jokers = 0;
            if(cards.containsKey(11)) 
                jokers = cards.get(11);

            if(jokers == 0) {
                return findType(hand);
            }

            int size = cardsNoJokers.size();
            switch (size) {
                case 1:
                case 0:
                    // 1 JJJJ
                    // 11 JJJ
                    // 111 JJ
                    // 1111 J
                    //  JJJJJ

                    // already best or all jokers OR JOKERS CAN FILL IN TO MAKE ALL ONE
                    return FIVEOFKIND;
                case 5:
                    // 12345
                    // all different no jokers
                    return HIGHCARD;
                case 4:
                    // 1234 J
                    // Joker can only add 1 additional MAT
                    return ONEPAIR;
                case 3:
                    // XYZ JJ
                    // XYZZ J
                    // XYYZ J
                    // XXYZ J
                    return THREEOFKIND;
                // 2 below
            }

            int maxOtherC = 0;
            for (Integer i : cardsNoJokers.values()) {
                if (i > maxOtherC) {
                    maxOtherC = i;
                }
            }
            // 2 possibles
            // Jokers 3
            // XY JJJ
            // XYYY J
            // XXXY J
            // XXYY J

            // 

            if (jokers == 3) {
                return FOUROFKIND;
            }
            if (jokers == 1 && maxOtherC == 3) {
                return FOUROFKIND;
            }
            if (jokers == 1 && maxOtherC == 2) {
                return FULLHOUSE;
            }

            // jokers 2
            // XXYJJ
            // XYYJJ
            if (jokers == 2 && maxOtherC == 2)
                return FOUROFKIND;

            String back = "";
            for (int i = 0; i < 5; i++) {
                switch (hand[i]) {
                    case 14:
                        back += "A";
                        break;
                    case 13:
                        back += "K";
                        break;
                    case 12:
                        back += "Q";
                        break;
                    case 11:
                        back += "J";
                        break;
                    case 10:
                        back += "T";
                        break;
                    default:
                        back += hand[i];
                        break;
                }
            }
            throw new Exception(String.format("how? jokers(%d) maxother(%d) hand(%s)", jokers, maxOtherC, back));
        }

        public String toString() {
            String back = "jokerslive= " + jokerslive + " hand=";
            for (int i = 0; i < 5; i++) {
                switch (hand[i]) {
                    case 14:
                        back += "A";
                        break;
                    case 13:
                        back += "K";
                        break;
                    case 12:
                        back += "Q";
                        break;
                    case 11:
                        back += "J";
                        break;
                    case 10:
                        back += "T";
                        break;
                    default:
                        back += hand[i];
                        break;
                }
            }
            back += " big=" + bid + " type=";
            switch (handType) {
                case HIGHCARD:
                    back += "HIGHCARD";
                    break;
                case ONEPAIR:
                    back += "ONEPAIR";
                    break;
                case TWOPAIR:
                    back += "TWOPAIR";
                    break;
                case THREEOFKIND:
                    back += "THREEOFKIND";
                    break;
                case FULLHOUSE:
                    back += "FULLHOUSE";
                    break;
                case FOUROFKIND:
                    back += "FOUROFKIND";
                    break;
                case FIVEOFKIND:
                    back += "FIVEOFKIND";
                    break;
                default:
                    back += "YOUFUCKEDUPROB";
                    break;
            }
            return back;
        }

        public final int otherBigger = 1;
        public final int otherSmaller = -1;

        @Override
        public int compareTo(Object o) {
            Hand otherHand = (Hand) o;
            if (otherHand.handType > handType) {
                return otherBigger;
            } else if (otherHand.handType < handType) {
                return otherSmaller;
            }

            // same, sigh
            for (int i = 0; i < 5; i++) {
                if (otherHand.hand[i] > hand[i]) {
                    return otherBigger;
                } else if (otherHand.hand[i] < hand[i]) {
                    return otherSmaller;
                }
                // same so check next card
            }

            // they are exactly the same
            return 0;
        }
    }
}
