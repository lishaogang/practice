package prac;

import java.util.Scanner;

public class matrimul {

	/**
	 * @param args
	 */
	static int N = 0;
	static int M = 0;

	public static long[][] step(long[][] mat1, long[][] mat2) {
		long[][] r = new long[N][N];
		for (int i = 0; i < N; i++)
			for (int k = 0; k < N; k++)
				for (int j = 0; j < N; j++) {
					r[k][j] += mat2[i][j] * mat1[k][i];
				}
		return r;
	}

	public static void show(long[][] mat) {
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat[i].length; j++) {
				System.out.print(mat[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		long[][] mat = new long[N][N];
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				mat[i][j] = (j + 1)%10;
		long[][] r = new long[N][N];
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				r[i][j] = i == j ? 1 : 0;

		for (int i = 0; i < M; i++) {
			r = step(r, mat);
		}

		show(r);
		show(mat);

	}

}
