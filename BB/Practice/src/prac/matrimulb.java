package prac;

import java.math.BigInteger;
import java.util.Scanner;

public class matrimulb {

	/**
	 * @param args
	 */
	static int N = 0;
	static int M = 0;

	public static BigInteger[][] step(BigInteger[][] mat1, BigInteger[][] mat2) {
		BigInteger[][] r = new BigInteger[N][N];
		for(int i = 0; i < N;i++)
			for(int j = 0; j < N;j++){
				r[i][j] = BigInteger.valueOf(0);
			}
		for (int i = 0; i < N; i++)
			for (int k = 0; k < N; k++)
				for (int j = 0; j < N; j++) {
					r[k][j] = r[k][j].add(mat2[i][j].multiply(mat1[k][i]));
				}
		return r;
	}

	public static void show(BigInteger[][] mat) {
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
				mat[i][j] = (j + 1) % 10;
		long[][] r = new long[N][N];
		BigInteger[][] br = new BigInteger[N][N];
		BigInteger[][] bmat = new BigInteger[N][N];
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++) {
				br[i][j] = BigInteger.valueOf(i == j ? 1 : 0);
				bmat[i][j] = BigInteger.valueOf(mat[i][j]);
			}
		
		for (int i = 0; i < M; i++) {
			br = step(br, bmat);
		}

		show(br);
		show(bmat);

	}

}
