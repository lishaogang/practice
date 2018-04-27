package prac;

import java.util.Random;
import java.util.Scanner;

public class inversion {

	/**
	 * @param args
	 */
	private static void swap(int[] a, int i, int j) {
		int t = a[i];
		int c = a[i + 1];
		for (int k = i; k < j; k += 2) {
			a[i] = a[i + 2];
			a[i + 1] = a[i + 3] + 1;
		}
		a[j] = t;
		a[j + 1] = c + (j - i)/2;
	}
	private static void show(int [] a){
		for (int i = 0; i < a.length; i+=2) {
			System.out.print(String.format("%d ", a[i]));
		}
		System.out.println();
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Random random = new Random(10);
		for(int i = 0; i < 10;i++)
			System.out.print(Math.abs(random.nextInt())%1000000+" ");
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] a = new int[2 * n];
		long start = System.currentTimeMillis();
		for (int i = 0; i < 2 * n; i += 2) {
			a[i] = Math.abs(random.nextInt())%1000000; //sc.nextInt();
			a[i + 1] = 0;
		}
		for (int i = 0; i < 2 * n; i += 2) {
			int t = 0;
			for (int j = 0; j < 2 * n; j += 2) {
				if (a[t] > a[j]) {
					swap(a, t, j);
					t = j;
				
				}

			}
		}
		int s = 0;
		for (int i = 1; i < 2 * n; i += 2) {
			s += a[i]*(a[i]+1)/2;
		}
		long end = System.currentTimeMillis();
		System.out.println(end-start+"ms");
		System.out.println(s);
	}

}
