import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;


public class P10_ExtractAllUniqueWords {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		String[] data = input.nextLine().toLowerCase().split("\\W");
		HashSet<String> words = new HashSet<>();
		for (String word : data) {
			words.add(word);
		}
		//Sort;
		ArrayList<String> list = new ArrayList<String>(words);
		  java.util.Collections.sort(list);
		//Print;
		for (String word : list) {
			System.out.print(word + " ");
		}
	}

}
