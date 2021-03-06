import java.util.ArrayList;
import java.util.Scanner;


public class P3_Largest3Rectangles {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		//String data = " [3 x 3] [3 x 2] [4 x 3] [1 x 4] [5 x 3] [3 x 1]";
		String data = input.nextLine();

		String[] sides = data.split("[\\D\\s]+");
		ArrayList<Integer> nums = new ArrayList<Integer>();
		for (String side : sides) {
			if (side.compareTo("") != 0) {
				nums.add(Integer.parseInt(side));
				//System.out.println(side);
			}

		}
		int maxArea = 0;
		for (int i = 0; i < nums.size() - 5; i+=2) {
			int currentArea = 
					nums.get(i) * nums.get(i + 1) +
					nums.get(i + 2) * nums.get(i + 3) +
					nums.get(i + 4) * nums.get(i + 5);
			if (currentArea > maxArea) {
				maxArea = currentArea;
			}
		}
		System.out.println(maxArea);
	}
}
