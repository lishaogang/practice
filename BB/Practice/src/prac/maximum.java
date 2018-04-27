package prac;

import java.util.Scanner;

public class maximum {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int [] a = new int[n];
		int mark = 0;
		String r = "";
		for(int i = 0; i < n; i++){
			a[i] = sc.nextInt();
		}
		for(int i = 0; i < n;i++){
			if(a[i] > a[mark]){
				mark = i;
			}
		}
		int m = a[mark];
		System.out.print(m+" "+mark);
		for(int i = mark+1; i < n;i++){
			if(a[i] == m)
				System.out.print(" "+m);
		}
		
	}

}
