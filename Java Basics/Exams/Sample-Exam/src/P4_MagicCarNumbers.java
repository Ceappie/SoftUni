import java.util.Scanner;

public class P4_MagicCarNumbers {

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);
		int desiredWeight = input.nextInt();
		int[] digitsWeight = { 10, 20, 30, 50, 80, 110, 130, 160, 200, 240 };
		int count = 0;

		for (int a = 0; a < 10; a++) {
			for (int b = 0; b < 10; b++) {
				for (int x = 0; x < digitsWeight.length; x++) {
					for (int y = 0; y < digitsWeight.length; y++) {
						if (a != b) {
							if (getWeight(a, b, b, b, digitsWeight[x],
									digitsWeight[y]) == desiredWeight) {
								count++;
							}
							if (getWeight(a, a, a, b, digitsWeight[x],
									digitsWeight[y]) == desiredWeight) {
								count++;
							}
							if (getWeight(a, a, b, b, digitsWeight[x],
									digitsWeight[y]) == desiredWeight) {
								count++;
							}
							if (getWeight(a, b, a, b, digitsWeight[x],
									digitsWeight[y]) == desiredWeight) {
								count++;
							}
							if (getWeight(a, b, b, a, digitsWeight[x],
									digitsWeight[y]) == desiredWeight) {
								count++;
							}
						} else if (getWeight(a, a, a, a, digitsWeight[x],
								digitsWeight[y]) == desiredWeight) {
							count++;
						}
					}
				}
			}
		}
		System.out.println(count);
	}

	private static int getWeight(int d1, int d2, int d3, int d4, int d5, int d6) {
		// CA is 40;
		int weight = 40 + d1 + d2 + d3 + d4 + d5 + d6;
		return weight;
	}

}
