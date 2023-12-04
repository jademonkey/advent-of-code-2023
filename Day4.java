import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Day4
 */
public class Day4 {
    static final String EXAMPLE_INPUT_1 = "Day4Example";

    static final String ACTUAL_INPUT_1 = "Day4Actual";

    public static void main(String[] args) throws IOException {
    	ArrayList<Cards> cards = new ArrayList<Cards>();
    	int p1a = 0;
    	int p2a = 0;
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
                int n = 1;
                String splitted[] = line.split(" ");
                // find ID
                for(;n < splitted.length; n++) {
                	if(splitted[n].length() > 0) {
                		break;
                	}
                }
               // for(int i =0; i < splitted.length; i ++)
              //  System.out.println(i + "="+splitted[i]);
                int ID = 0;
                ArrayList<Integer> winningNums = new ArrayList<Integer>();
                ArrayList<Integer> cardNums = new ArrayList<Integer>();
                // skip index 0 
                ID = Integer.parseInt(splitted[n].replaceAll(":", ""));
                // ID = index 1 (minus :)
                boolean outWin = false;
                for(int i=(n+1); i < splitted.length; i ++) {
                	String thisS = splitted[i];
                	if(thisS.equals("|")) {
                		outWin = true;
                		continue;
                	}
                	if(thisS.equals("")) {
                		continue;
                	}
                	int num = Integer.parseInt(splitted[i]);
                	if(outWin) {
                		cardNums.add(num);
                	} else {
                		winningNums.add(num);
                	}
                }
                // numbers up to |
                // more numbers
                
                // Done sort for fun then create
                Collections.sort(cardNums);
                Collections.sort(winningNums);
                Cards thisCard = new Cards(ID, winningNums, cardNums);
                cards.add(thisCard);
            }
            
            // calculate points
            for(Cards c : cards) {
            	p1a += c.calculatePoints();
            }
            System.out.println("Part 1=" + p1a);
            
            // Part 2
            for(int i = 0; i < cards.size(); i++) {
            	if(cards.get(i).getWinningCards() > 0)
            		p2a += processCard(cards, i+1);
            	else 
            	 p2a++;
            }
            System.out.println("Part 2=" + p2a);
            
        } finally {
            br.close();
        }

    }

    public static int processCard(ArrayList<Cards> fullList, int thisID) {
    	int cards = 1;
    	Cards thisCard = fullList.get(thisID-1);
    	int winnNum = thisCard.getWinningCards();
    	for(int i = 0; i < winnNum; i++) {
    		cards += processCard(fullList, thisID + 1 + i);
    	}
    	
    	return cards;
    }
    
    public static class Cards {
    	private int ID = 0;
    	private ArrayList<Integer> winningNumbers = new ArrayList<Integer>();
    	private ArrayList<Integer> cardNumbers = new ArrayList<Integer>();
    	private int points =0;
    	private int winningCards = 0;
    	
    	public Cards(int ID, ArrayList<Integer> winNum, ArrayList<Integer> thisNum){
    		this.ID =ID;
    		this.winningNumbers = winNum;
    		this.cardNumbers = thisNum;
    	}

		public ArrayList<Integer> getWinningNumbers() {
			return winningNumbers;
		}

		public void setWinningNumbers(ArrayList<Integer> winningNumbers) {
			this.winningNumbers = winningNumbers;
		}

		public ArrayList<Integer> getCardNumbers() {
			return cardNumbers;
		}

		public void setCardNumbers(ArrayList<Integer> cardNumbers) {
			this.cardNumbers = cardNumbers;
		}

		public int getPoints() {
			return points;
		}

		public int calculatePoints() {
			points = 0;
			winningCards = 0;
			for(int w : winningNumbers) {
				for(int n: cardNumbers) {
					if(w == n) {
						winningCards = getWinningCards() + 1;
					//	System.out.println("Matched " + w + " & " + n + " in card " + ID);
						if(points ==  0) {
							points = 1;
						} else {
							points *= 2;
						}
						break;
					}
					if (n > w) { // gone past where it could have been
						break;
					}
				}
			}
			return points;
		}

		public int getID() {
			return ID;
		}

		public int getWinningCards() {
			return winningCards;
		}    	
    }
}
