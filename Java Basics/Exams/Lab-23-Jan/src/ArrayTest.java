import java.util.Scanner;

public class ArrayTest {
	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner scn = new Scanner(System.in);

		int sizeOfArray = scn.nextInt();
		long[] array = new long[sizeOfArray];

		for (int i = 0; i < array.length; i++) {
			array[i] = scn.nextLong();
		}

		String command = scn.next();
		// Changed Over to Stop
		while (!command.equals("stop")) {
			String line = scn.nextLine().trim();
			int[] params = new int[2];
			//subtract
			if (command.equals("add") || command.equals("subtract")
					|| command.equals("multiply")) {
				String[] stringParams = line.split(" ");
				params[0] = Integer.parseInt(stringParams[0]);
				params[1] = Integer.parseInt(stringParams[1]);

				performAction(array, command, params);
			} else { //Else
				performAction(array, command, params);
			}

			printArray(array);
			System.out.print('\n');

			command = scn.next();
		}
	}

	static void performAction(long[] arr, String action, int[] params) {
		//
		int pos = params[0] - 1;
		int value = params[1];

		switch (action) {
		case "multiply":
			arr[pos] *= value;
			break;
		case "add":
			arr[pos] += value;
			break;
		case "subtract":
			arr[pos] -= value;
			break;
		case "lshift":
			arrayShiftLeft(arr);
			break;
		case "rshift":
			arrayShiftRight(arr);
			break;
		}
	}

	private static void arrayShiftRight(long[] array) { //arrayShiftRight
		long temp = array[array.length - 1]; //
		for (int i = array.length - 1; i >= 1; i--) {
			array[i] = array[i - 1];
		}
		array[0] = temp;
	}

	private static void arrayShiftLeft(long[] array) {
		long temp = array[0]; //
		for (int i = 0; i < array.length - 1; i++) {
			array[i] = array[i + 1];
		}
		array[array.length - 1] = temp; //
	}

	private static void printArray(long[] array) {
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i] + " ");
		}
	}
}