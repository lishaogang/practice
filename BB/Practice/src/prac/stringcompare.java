package prac;

import java.util.Scanner;

public class stringcompare {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		String s1 = sc.nextLine();
		String s2 = sc.nextLine();
		sc.close();
		char [] a = s1.toCharArray();
		char [] b = s2.toCharArray();
		if(s1.length() != s2.length()){
			System.out.println(1);
			return;
		}
		if(s1.equals(s2)){
			System.out.println(2);
			return ;
		}
		if(s1.toLowerCase().equals(s2.toLowerCase())){
			System.out.println(3);
			return;
		}
		System.out.println(4);
		
	}

}
