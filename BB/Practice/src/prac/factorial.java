package prac;

import java.math.BigInteger;
import java.util.Scanner;

public class factorial {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		sc.close();
		BigInteger bn = BigInteger.valueOf(1);
		System.out.println(bn);
		for(int i = 2; i <= n; i++){
			bn = bn.multiply(BigInteger.valueOf(i));
		}
		System.out.println(bn);
	}

}
