package prac;

import java.util.Arrays;
import java.util.Scanner;

public class minmul {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		int sum = 0;
		while (T-- != 0) {
			int m = sc.nextInt();
			int a[] = new int[m];
			int b[] = new int[m];
			for (int i = 0; i < m; i++) {
				a[i] = sc.nextInt();
			}
			for (int i = 0; i < m; i++) {
				b[i] = sc.nextInt();
			}
			Arrays.sort(a);
			Arrays.sort(b);
			for (int i = 0, j = b.length - 1; i < a.length && j >= 0; i++, j--) {
				sum += a[i] * b[j];
			}
			System.out.println(sum);
			sum = 0;
		}
		sc.close();
	}

}
