import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

public class Day8 {
	static final String EXAMPLE_INPUT_1 = "Day8Example";

	static final String ACTUAL_INPUT_1 = "Day8Actual";

	public static void main(String[] args) throws FileNotFoundException {
		String[] dirs;
		HashMap<String, LRMap> maps = new HashMap<String, LRMap>();
		ArrayList<String> startPoints = new ArrayList<String>();
		/* Read each line */
		BufferedReader br = new BufferedReader(new FileReader(ACTUAL_INPUT_1));
		try {
			String line = br.readLine(); // directions
			dirs = line.split("");
			br.readLine(); // skip new line
			
			for(int i = 0; i < dirs.length; i++) {
				System.out.print(i + "="+dirs[i] + " ");
			}
			System.out.println();

			// parse the map
			while (true) {
				line = br.readLine();
				if (line == null) {
					/* EOF */
					break;
				}
				
				String area = line.substring(0, 3);
				String left = line.substring(7, 10);
				String right = line.substring(12, 15);
				
			//	System.out.println(String.format("area(%s) left(%s) right(%s)", area, left, right));
				
				maps.put(area, new LRMap(left, right));
				if(area.charAt(2) == 'A') {
					startPoints.add(area);
				}
			}
			System.out.println();
			
			// now route for part 1
			String currentArea = "AAA";
			LRMap routes = maps.get(currentArea);
	//		System.out.print("AAA");
			int steps = 0;
			int dirP = 0;
			while(!currentArea.equals("ZZZ")) {
				String go = dirs[dirP];
				dirP++;
				if(dirP >= dirs.length) {
					dirP = 0;
				}
				if(go.equals("R")) {
					currentArea = routes.right;
				} else {
					currentArea = routes.left;
				}
				steps++;
				routes = maps.get(currentArea);
		//		System.out.print(" -"+go+"> " + currentArea);
			}
			//System.out.println();
			System.out.println("P1a steps = " + steps);
			

			String startPointsAr[] = startPoints.toArray(new String[0]);
			// Part 2
			steps = 0;
			dirP = 0;
			ArrayList<Long> StepsPerRoute = new ArrayList<Long>();
			while(true) {
				// check if done
				boolean atEnd=true;
				for(String s: startPointsAr) {
					if(!s.equals("DONE")) {
						atEnd = false;
						break;
					}
				}
				if(atEnd) {
					break;
				}
				
				// push everything along
				String go = dirs[dirP];
				dirP++;
				if(dirP >= dirs.length) {
					dirP = 0;
				}
				steps++;
				for(int i =0; i < startPointsAr.length; i++) {
					if(startPointsAr[i].equals("DONE")) {
						continue;
					}
					String newP;
					if(go.equals("R")) {
						newP = maps.get(startPointsAr[i]).right;
					} else {
						newP = maps.get(startPointsAr[i]).left;
					}
					startPointsAr[i] = newP;
					if(newP.charAt(2) == 'Z') {
						startPointsAr[i] = "DONE";
						StepsPerRoute.add((long) steps);
					}
				}
			}
			
			long p2a = lcm(StepsPerRoute.toArray(new Long[0]));
			
			System.out.println("P2a steps = " + p2a);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static long gcd(long a, long b)
	{
	    while (b > 0)
	    {
	        long temp = b;
	        b = a % b; // % is remainder
	        a = temp;
	    }
	    return a;
	}
	
	private static long lcm(long a, long b)
	{
	    return a * (b / gcd(a, b));
	}

	private static long lcm(Long[] input)
	{
	    long result = input[0];
	    for(int i = 1; i < input.length; i++) result = lcm(result, input[i]);
	    return result;
	}
	
	public static class LRMap {
		public String left;
		public String right;
		
		public LRMap(String left, String right) {
			this.left = left;
			this.right = right;
		}
		
	}
}
