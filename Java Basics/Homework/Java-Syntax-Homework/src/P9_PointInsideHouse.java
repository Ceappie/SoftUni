import java.util.Scanner;

public class P9_PointInsideHouse {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		double x = input.nextDouble();
		double y = input.nextDouble();

		double aX = 12.5d, aY = 8.5d;
		double bX = 17.5, bY = 3.5d;
		double cX = 22.5, cY = 8.5d;
		
		boolean insideTriangle = false;
		if ((((bX - aX) * (y - aY) - (bY - aY) * (x - aX)) >= 0) &&
			(((cX - bX) * (y - bY) - (cY - bY) * (x - cX)) >= 0) &&
			(((cX - aX) * (y - aY) - (cY - aY) * (x - cX)) <= 0)) {
			insideTriangle = true;
		}
		boolean insideRectangles = false;
		if ((x >= 12.5d && x <= 17.5d && y >= 8.5d && y <= 13.5d) ||
			(x >= 20d && x <= 22.5d && y >= 8.5d && y <= 13.5d)) {
			insideRectangles = true;
		}
		if (insideRectangles || insideTriangle) {
			System.out.println("Inside");
		}
		else {
			System.out.println("Outside");
		}
	}

}
/*
 * Use the sign of the determinant of vectors (AB,AM), where M(X,Y) is the query
 * point:
 * 
 * position = sign( (Bx-Ax)*(Y-Ay) - (By-Ay)*(X-Ax) )
 * 
 * It is 0 on the line, and +1 on one side, -1 on the other side.
 */