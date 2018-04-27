package prac;

import java.sql.Array;
import java.util.Scanner;

public class palindrome {

	/**
	 * @param args
	 */
	static int acc = 0;
	static char oddchar = 0;

	private static boolean check(char[] s) {
		int[] map = new int[256];
		int odd = 0;
		for (int i = 0; i < s.length; i++) {
			map[s[i]]++;
		}
		for (int i = 0; i < s.length; i++) {
			if (map[s[i]] % 2 == 1) {
				odd++;
				oddchar = s[i];
			}
		}
		return odd > 1 ? false : true;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int N = Integer.parseInt(sc.nextLine());
		String s = sc.nextLine();
		char str[] = s.toCharArray();
		boolean flag = check(str); // 是否可以组成回文
		System.out.println(oddchar);
		if (!flag) {
			System.out.println("Impossible");
		} else {
			for (int i = 0; i < N / 2; i++) {
				int j = 0;
				for (j = N - 1 - i; j > i; j--) {
					if(str[i] == str[j])
						break;
				}
				acc += N-1-i-j;
				for(;j<N-1-i;j++)
					str[j]=str[j+1];
				str[N-1-i] = str[i];
			}
			for (int i = 0; i < str.length; i++)
				System.out.print(str[i]);
			System.out.println();
			System.out.println(acc);
		}
	}

}
