import java.util.Scanner;


public class P03_LargestSequence {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		String[] data = input.nextLine().split("\\s+");
		int maxCount = 1;
		String savedWord = data[0];
		for (int i = 0; i < data.length; i++) {
			int currentCount = 1;
			for (int j = i; j < data.length - 1; j++) {
				if (data[j].equals(data[j + 1])) {
					currentCount++;
					if (maxCount < currentCount ) {
						maxCount = currentCount;
						savedWord = data[j];
					}
				}
				else {
					currentCount = 1;
				}
			}
		}
		for (int i = 0; i < maxCount; i++) {
			System.out.printf(savedWord + " ");
		}

	}

}
