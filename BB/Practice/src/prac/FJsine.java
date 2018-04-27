package prac;

import java.util.Scanner;

public class FJsine {

	/**
	 * @param args
	 */
	private static String getA(int n) {
		int i = 0;
		String s="";
		for (i = 1; i < n; i++)
			s += "sin(" + i + "-";
		s += "sin(" + i;
		for (i = 0; i < n; i++)
			s += ")";
		return s;

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		sc.close();
		for (int i = 0; i < n - 1; i++)
			System.out.print('(');
		for (int i = 0; i < n; i++) {
			System.out.print(getA(i + 1) + "+" + (n - i) + ")");
		}
	}

}
