import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class P4_CouplesFrequency {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		String data = input.nextLine();
		//String data = "3 4 2 3 4 2 1 12 2 3 4";
		String[] nums = data.split(" ");
		HashMap<String, Integer> couples = new LinkedHashMap<String, Integer>();
		int totalCouples = 0;
		for (int i = 0; i < nums.length - 1; i++) {
			String couple = nums[i] + " " + nums[i + 1];
			//System.out.println(couple);
			if (couples.containsKey(couple)) {
				couples.put(couple, couples.get(couple) + 1);
			} else {
				couples.put(couple, 1);
			}
			totalCouples++;
		}
		for ( Map.Entry<String, Integer> entry : couples.entrySet() ) {
		    String key = entry.getKey();
		    int value = entry.getValue();
		    double frequency = value / (double) totalCouples * 100;
		    System.out.printf("%s -> %.2f%%\n", key, frequency);
		}
		
	}
}
