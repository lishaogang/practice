package prac;

import java.sql.Array;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class reversedorder {

	/**
	 * @param args
	 */
	static int N = 0;
	static int[] a;
	static int index = 0;
	static tree T;

	static class tree {
		tree r, l;
		int self;

		public tree() {
			r = l = null;
			self = 0;
		}
	}

	private static void tree2ints(tree t) {
		int r = 0;
		if (t.self != 0) {
			a[index++] = t.self;
			return;
		}
		tree2ints(t.l);
		tree2ints(t.r);
	}

	private static int count() {
		int r = 0;
		tree2ints(T);
		index = 0;
		
		for (int i = 0; i < a.length; i++) {
			for (int j = i; j < a.length; j++) {
				r += a[i] > a[j] ? 1 : 0;
			}
		}
		return r;
	}

	private static tree init(Scanner sc) {
		tree r = new tree();

		int leave = sc.nextInt();
		if (leave != 0) {
			r.self = leave;
			return r;
		}
		r.l = init(sc);
		r.r = init(sc);
		
		return r;
	}

	private static void swap(tree t) {
		tree m = t.l;
		t.l = t.r;
		t.r = m;
	}

	private static int solution(tree t) {
		if(t.self != 0){
			return 0;
		}
		int r = 0;
		int o = 0;
		solution(t.l);
		solution(t.r);
		o = count();
		swap(t);
		r = count();
		if(o < r){
			r = 0;
			swap(t);
		}
		return r;
	}

	private static void test() {
		
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		a = new int[N];
		T = init(sc);
		sc.close();
		System.out.println();
		System.out.println("initial " + count());
		for(int i = 0; i < N; i++)
			System.out.print(a[i]+" ");
		System.out.println();
		System.out.println("switch " + solution(T));
		for(int i =0; i < N;i++)
			System.out.print(a[i]+" ");
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test();
	}

}
