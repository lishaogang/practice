import java.util.Scanner;


public class clock {

	/**
	 * @param args
	 */
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final String one_to_nineteen [] = new String[] {"zero","one",	"two","three","four","five",
			"six","seven","eight","nine","ten","eleven","twelve","thirteen",
			"fourteen","fifteen","sixteen",	"seventeen","eighteen","nineteen"};
		final String ten_fold[] = new String[] {"twenty","thirty","fourty","fifty"};
		Scanner sc = new Scanner(System.in);
		int h = sc.nextInt();
		int m = sc.nextInt();
		System.out.print(one_to_nineteen[h]+" ");
		if(m == 0)
			System.out.print("o'clock");
		else if(m < 20)
			System.out.println(one_to_nineteen[m]);
		else if(m%10 != 0)
			System.out.print(ten_fold[m/10-2] + " " + one_to_nineteen[m%10]);
		else
			System.out.print(ten_fold[m/10-2]);
		
	}
	

}
