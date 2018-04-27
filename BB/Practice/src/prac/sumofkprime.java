package prac;

import java.util.Scanner;

public class sumofkprime {

	/**
	 * @param args
	 */
	private static boolean isprime(int p){
		float n = (float) p;
		for(int i = 2; i <= Math.sqrt(p);i++){
			if(n/i-p/i == 0)
				return false;
		}
		return p > 1;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		sc.close();
		int p = 1;
		int product = 1;
		while (n-- != 0) {
			while(!isprime(p++));
			product = ((p-1)*product)%50000;
		}
		System.out.println(product);
		
	}

}
