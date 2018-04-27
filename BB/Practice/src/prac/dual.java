package prac;

import java.util.Scanner;

public class dual {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int m, n;
		m = sc.nextInt();
		n = sc.nextInt();
		int[][] nums = new int[m][n];
		for (int i = 0; i < m; i++)
			for (int j = 0; j < n; j++) {
				nums[i][j] = sc.nextInt();
			}
		sc.close();
		for (int i = 0; i < m; i++)
			for (int j = 0; j < n; j++) {
				System.out.print(nums[i][j]+" ");
			}
		System.out.println("");
		for (int i = 0; i < Math.min(m, n) / 2 + 1; i++) {
			// down
			for (int j = i; j < n - i; j++) {
				System.out.print(nums[j][i] + " ");
			}
			// right
			for (int j = i+1; j < m - i; j++) {
				System.out.print(nums[n-i-1][j] + " ");
			}
			// up
			for (int j = n - i - 2; j > i-1; j--) {
				System.out.print(nums[j][m - i - 1] + " ");
			}
			// left
			for (int j = m - i - 2; j > i; j--) {
				System.out.print(nums[i][j]+" ");
			}
		}
	}
}
