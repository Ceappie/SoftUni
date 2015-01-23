import java.util.Scanner;

public class BitRotationFinished {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		byte number = Byte.parseByte(input.nextLine());
		byte rotations = Byte.parseByte(input.nextLine());

		for (int i = 0; i < rotations; i++) {
			String direction = input.nextLine();

			if (direction.compareTo("right") == 0) {
				int rightMostBit = number & 1;
				number >>= 1;
				number |= rightMostBit << 5;
			} else if (direction.compareTo("left") == 0) {
				int leftMostBit = (number >> 5) & 1;
				number <<= 1;
				number |= leftMostBit;
				number &= ~(1 << 6);
			}
		}

		System.out.println(number);
	}
}