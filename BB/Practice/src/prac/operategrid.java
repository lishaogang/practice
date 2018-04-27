package prac;

import java.util.Scanner;

public class operategrid {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt(), m = sc.nextInt();
		int[] g = new int[n];
		int p, x, y;
		for (int i = 0; i < n; i++)
			g[i] = sc.nextInt();
		int r = 0;
		while (m-- != 0) {
			p = sc.nextInt();
			x = sc.nextInt();
			y = sc.nextInt();
			switch (p) {
			case 1:
				g[x-1] = y;
				break;
			case 2:
				for (; x <= y; x++)
					r += g[x-1];
				System.out.println(r);
				break;
			case 3:
				r = g[x-1];
				for (; x <= y; x++) {
					r = r < g[x-1] ? g[x-1] : r;
				}
				System.out.println(r);
			default:
				break;
			}
		}
	}

}
