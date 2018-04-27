package prac;

import java.util.Scanner;

public class timeconversion {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		sc.close();
		int h,m,s;
		s = t % 60;
		t = t / 60;
		m = t % 60;
		t = t /60;
		h = t;
		System.out.println(String.format("%d:%d:%d", h,m,s));
	}

}
