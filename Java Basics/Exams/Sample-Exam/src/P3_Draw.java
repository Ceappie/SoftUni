import java.util.Scanner;
import java.lang.Object;


public class P3_Draw {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int height = input.nextInt();
		int width = height;
		
		int dots = 0;
		for (int i = 0; i < height; i++) {
			String innerRow = "";
			for (int j = 0; j < dots; j++) {
				innerRow += ".";
			}
			for (int j = 0; j < width - dots * 2; j++) {
				innerRow += "*";
			}
			for (int j = 0; j < dots; j++) {
				innerRow += ".";
			}
			if (i < height / 2) {
				dots++;
			}
			else {
				dots--;
			}
			System.out.println(innerRow);
		}
	}

}
