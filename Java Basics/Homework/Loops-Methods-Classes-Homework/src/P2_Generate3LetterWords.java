import java.util.Scanner;


public class P2_Generate3LetterWords {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		String letters = input.nextLine();
		for (int l1 = 0; l1 < letters.length(); l1++) {
			for (int l2 = 0; l2 < letters.length(); l2++) {
				for (int l3 = 0; l3 < letters.length(); l3++) {
					System.out.println("" + letters.charAt(l1) + letters.charAt(l2) + letters.charAt(l2));
				}
			}
		}

	}

}
