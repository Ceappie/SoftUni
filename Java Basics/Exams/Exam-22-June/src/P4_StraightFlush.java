import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class P4_StraightFlush {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		String data = input.nextLine();
		String[] cards = data.split("[,\\s]");
		/*Arrays.sort(cards);
		int len = cards.length;
		ArrayList<String> spades = new ArrayList<String>();
		ArrayList<String> hearts = new ArrayList<String>();
		ArrayList<String> diamonds = new ArrayList<String>();
		ArrayList<String> clubs = new ArrayList<String>();
		for (int i = 0; i < len; i++) {
			System.out.println(cards[i]);
			switch (cards[i].charAt(1)) {
			case 'S':
				spades.add(cards[i]);
				break;
			case 'H':
				hearts.add(cards[i]);
				break;
			case 'D':
				diamonds.add(cards[i]);
				break;
			case 'C':
				clubs.add(cards[i]);
				break;
			}
		}
		for (int i = 0; i < spades.size() - 5; i++) {
			System.out.println(spades.get(i));
		}*/

	}
}
/*
 * Split to cards; sort cards in 4 lists; for-loop to find flushes
 */