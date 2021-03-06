import java.util.Scanner;


public class P4_SortArray {

	public static void main(String[] args) {
		
		//Get names;
		Scanner input = new Scanner(System.in);
		int len = input.nextInt();
		String[] names = new String[len];
		for (int i = 0; i < len; i++) {
			names[i] = input.next();
		}
		
		//Bubble sort;
		boolean sort = true;
		while (sort) {
			sort = false;
			for (int i = 0; i < len -1; i++) {
				if (names[i].compareToIgnoreCase(names[i + 1]) > 0) {
					//Second name is alphabetically before first name
					String swapper = names [i];
					names[i] = names[i + 1];
					names[i + 1] = swapper;
					sort = true;
				}
			}
		}
		
		//Print result;
		for (int i = 0; i < len; i++) {
			System.out.println(names[i]);
		}
	}

}