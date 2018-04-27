package prac;

import java.util.Scanner;

public class race {

	/**
	 * @param args
	 */
	static int v1, v2, t, s, l;
	static int acc = 0;
	private static int run(float s1, float s2) {
		
		float m = (t - s1 + s2) / (v1 - v2);
		//如果不需要休息
		if ((l - s1) / v1 <= m) {
			if ((l - s1) / v1 < (l - s2) / v2)
				return 1;
			else if ((l - s1) / v1 > (l - s2) / v2)
				return -1;
			return 0;
		}
		//如果需要休息
		acc += s;
		if ((l - s2) / v2 <= m + s)
			return -1;
		return run(s1 + v1 * m, s2 + v2 * (m + s));

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		v1 = sc.nextInt();
		v2 = sc.nextInt();
		t = sc.nextInt();
		s = sc.nextInt();
		l = sc.nextInt();
		String[] r = new String[] { "T", "D", "R" };
		int winner = run(0, 0);
		System.out.println(r[winner+1]);
		System.out.print(winner == 1 ? l/v1+acc : l/v2);
	}

}
