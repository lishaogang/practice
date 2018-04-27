package prac;

import java.io.PrintWriter;
import java.util.Scanner;

public class FJsequence {

	/**
	 * @param args
	 */
	private static String  solution(int n){
		if(n==0)
			return "A";
		String s = solution(n-1);
		
		return s+(char)(n+65)+s;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		long start = System.currentTimeMillis();
		PrintWriter out = new PrintWriter(System.out);
		out.println(solution(n));
		long end = System.currentTimeMillis();
		System.out.println("\n"+(end - start)+"ms");
	}

}
