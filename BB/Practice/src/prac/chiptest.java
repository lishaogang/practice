package prac;

import java.awt.List;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class chiptest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int test[][] = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++)
				test[i][j] = sc.nextInt();
		}
		sc.close();
		ArrayList<Integer> r = new ArrayList<Integer>();

		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				if ((test[i][j] & test[j][i]) == 1) {
					r.add(i+1);
					r.add(j+1);
				}
			}
		}
		r.add(0);
		Object[] re = r.toArray();
		Arrays.sort(re);
		for(Object x:re){
			System.out.print(x+" ");
		}
	}
}
