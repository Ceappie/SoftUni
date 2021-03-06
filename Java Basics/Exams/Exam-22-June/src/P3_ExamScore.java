import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;


public class P3_ExamScore {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		String nonRelevent = new String();
		for (int i = 0; i < 3; i++) {
			nonRelevent = input.nextLine();
		}
		String data = input.nextLine();
		TreeMap<Integer,String> score = new TreeMap<Integer,String>();
		TreeMap<String, Double> names = new TreeMap<String, Double>();
		while (data.compareTo(nonRelevent) != 0) {
			String[] details = data.split("[\\s|]+");
			/*for (int i = 0; i < details.length; i++) {
				System.out.println("." + details[i] + ".");
			}*/
			int keyScore = Integer.parseInt(details[3]);
			if (score.containsKey(keyScore)) {
				double sum = names.get(score.get(keyScore));
				sum += Double.parseDouble(details[4]);
				String newNames = score.get(keyScore);
				newNames += "," + details[1] + " " + details[2];
				score.put(keyScore, newNames);
				names.put(newNames, sum);
			}
			else {
				names.put((details[1] + " " + details[2]), Double.parseDouble(details[4]));
				score.put(keyScore, (details[1] + " " + details[2]));
			}
			
			data = input.nextLine();
		}
		//Print;
		for ( Map.Entry<Integer, String> entry : score.entrySet() ) {
			System.out.printf("%d -> [",entry.getKey());
			String[] sortedNames = (entry.getValue()).split(",");
			Arrays.sort(sortedNames);
			for (int i = 0; i < sortedNames.length; i++) {
				String name = sortedNames[i];
				if (i != sortedNames.length - 1) {
					System.out.printf("%s, ", name);
				}
				else {
					System.out.printf("%s]; ", name);
				}
			}
			double averageScore = names.get(entry.getValue()) / sortedNames.length;
			BigDecimal avgScore = new BigDecimal(averageScore);
			avgScore = avgScore.setScale(2, BigDecimal.ROUND_HALF_EVEN);
			System.out.printf("avg=%.2s\n", avgScore);
		}
		//3.445
		//3.44
		
	}

}
