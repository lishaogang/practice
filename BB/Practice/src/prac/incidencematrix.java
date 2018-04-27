package prac;

import java.util.Scanner;

public class incidencematrix {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt(), m = sc.nextInt();
		int[][] mat = new int[n][m];
		for (int i = 0; i < m; i++) {
			mat[sc.nextInt()-1][i] = 1;
			mat[sc.nextInt()-1][i] = -1;
		}
		for(int i =0; i < n;i++){
			for(int j = 0; j < m;j++){
				System.out.print(String.format("%4d", mat[i][j]));
			}
			System.out.println();
		}
	}

}
