import java.util.Scanner;


public class findTheFour {
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		sc.close();
		int limit = (int) (Math.sqrt(n));
		
		for(int a = 0; a <= limit; a++)
			for(int b = a; b <= limit;b++)
				for(int c = b; c <= limit; c++)
					for(int d = c; d <= limit; d++){
						if(a*a+b*b+c*c+d*d == n){
							System.out.println(a+" "+b+" "+c+" "+d);
							return;
						}
							
					}
	}
}
