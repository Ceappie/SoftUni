import java.util.Scanner;


public class P2_Durts {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		String data = input.nextLine();
		String[] coordinates = data.split(" ");
		String radiusData = input.nextLine();
		int radius = Integer.parseInt(radiusData);
		String numPoints = input.nextLine();
		data = input.nextLine();
		String[] pointsCoordinates = data.split("[\\s]+");
		int centerX = Integer.parseInt(coordinates[0]);
		int centerY = Integer.parseInt(coordinates[1]);
		//System.out.println(pointsCoordinates.length);
		for (int i = 0; i < Integer.parseInt(numPoints) * 2; i+=2) {
			int pointX = Integer.parseInt(pointsCoordinates[i]);
			int pointY = Integer.parseInt(pointsCoordinates[i + 1]);
			if (isInside(pointX, pointY, centerX, centerY, radius)) {
				System.out.println("yes");
			}
			else {
				System.out.println("no");
			}
		}

	}
	
	private static boolean isInside(int x, int y, int centerX, int centerY, int radius) {
		boolean insideFirst = false;
		if ((x >= centerX - radius) && (x <= centerX + radius) &&
			(y >= centerY - radius / 2) && (y <= centerY + radius / 2)) {
			insideFirst = true;
		}
		boolean insideSecond = false;
		if ((x >= centerX - radius / 2) && (x <= centerX + radius / 2) &&
				(y >= centerY - radius) && (y <= centerY + radius )) {
			insideSecond = true;
			}
		boolean inside = false;
		if (insideFirst || insideSecond) {
			inside = true;
		}
		return inside;
	}

}
