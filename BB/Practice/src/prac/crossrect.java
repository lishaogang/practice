package prac;

import java.util.Scanner;

public class crossrect {

	/**
	 * @param args
	 */
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		double x1,y1,x2,y2,x3,y3,x4,y4;
		x1 = sc.nextDouble();
		y1 = sc.nextDouble();
		x2 = sc.nextDouble();
		y2 = sc.nextDouble();
		x3 = sc.nextDouble();
		y3 = sc.nextDouble();
		x4 = sc.nextDouble();
		y4 = sc.nextDouble();
		double maxx1 = Math.max(x1, x2);double minx1= Math.min(x1, x2);
		double maxy1 = Math.max(y1, y2);double miny1 = Math.min(y1, y2);
		double maxx2 = Math.max(x3, x4);double minx2= Math.min(x3, x4);
		double maxy2 = Math.max(y3, y4);double miny2 = Math.min(y3, y4);
		if(maxx1 <= minx2 || maxx1 <= minx2 || maxy1 <= miny2 || maxy2 <= miny1){
			System.out.println(String.format("%.2f", 0.0f));
		}
		else{
			double minx = Math.max(minx1, minx2);double miny = Math.max(miny1, miny2);
			double maxx = Math.min(maxx1, maxx2);double maxy = Math.min(maxy1, maxy2);
			System.out.println(String.format("%.2f", Math.abs(maxx-minx)*Math.abs(maxy-miny)));
		}
 
	}

}
