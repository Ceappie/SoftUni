import java.util.Scanner;


public class P6_FormattingNumbers {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		int a = 254;
		double b = 3.5d;
		double c = 1.5d;
		String bin = Integer.toBinaryString(a);
		
		System.out.printf("%-10X|%010d|%10.2f|%-10.3f|", a, Integer.parseInt(bin), b, c);
	}

}
