package prac;

import java.util.Scanner;

public class clock {

	/**
	 * @param args
	 */
	static String [] hour = new String[]{"","zero","one", "two","three","four","five","six","seven","eight",
		"nine","ten","eleven","twelve"};
	static String [] minute = new String[] {"ten","twenty","thirty","fourty","fifty"};
	static String [] numbers = new String[] {"zero","one", "two","three","four","five","six","seven","eight",
			"nine","ten","eleven","twelve","thirteen","fourteen","fifteen","sixteen","seventeen",
			"eighteen","nineteen"};
	private static void readH(int h){
		System.out.print(hour[h+1]);
	}
	private static void readM(int m){
		switch(m){
		case 0:
			System.out.print(" o'clock");
			break;
		default:
			  if(m > 20)
				  System.out.print(" "+minute[m/10-1]+((m%10)!=0?(" "+hour[m%10+1]):"")); 
			  else
				  System.out.print(" "+numbers[m]);
			break;
			
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int h = sc.nextInt();
		int m = sc.nextInt();
		readH(h);
		readM(m);
		
	}

}
