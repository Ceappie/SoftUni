
public class P5_CardsPDF {

	public static void main(String[] args) {
		
		char[] sets = {'\u2660', '\u2665', '\u2666', '\u2663'};
		
		for (int suits = 0; suits < 4; suits++) {
			for (int cards = 1; cards <= 13; cards++) {
				String card = "";
				switch (cards) {
				case 1:
					card = "A" + sets[suits];
					break;
				case 11:
					card = "J" + sets[suits];
					break;
				case 12:
					card = "Q" + sets[suits];
					break;
				case 13:
					card = "K" + sets[suits];
					break;
				default:
					card = cards + "" + sets[suits];
					break;
				}
				System.out.print(card + " ");
			}
			System.out.println();
		}

	}

}
