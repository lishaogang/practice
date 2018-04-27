import java.util.Scanner;

//This process will be wrong after 35!

public class factorial {
	public static int[] solution(int n){
		if(n <= 0)
			return null;
		
		int [] product = {1};
		for(int i = 2; i <= n; i++){
			product = step(product, i);
		}
		
		return product;
		
	}
	
	private static int[] step(int [] product,  int n){
		int carry = 0;
		int [] r = null;
		r = new int[(n*product[product.length-1]+"").length()-1 + product.length];
		
		for(int j = 0; j < product.length; j++){
			
			r[j] = (product[j]*n + carry)%10;
			
			carry = (product[j]*n + carry)/10;
			if(n >= 34){
				
				printIntArr(r);
				System.out.print("|carry: "+carry+",n: "+n+"| ");
			}
			
		}
		
		product = r;
		carry = carry(product,carry,n);

		if(n >= 34){
			System.out.print("carried: "+carry+"| ");
			printIntArr(product);
		}
		return product;
		
	}
	
	
	private static int carry(int[] product, int carry_bit,int n){
		if(n == 35)
			printIntArr(product);
		for(int j = (carry_bit+"").length(); j > 0  ;j--){
			product[product.length-j] = product[product.length-j] > 0 ? product[product.length-j] : carry_bit%10;
			carry_bit = carry_bit / 10;
		}
		return carry_bit;
	}
	
	public static void main(String [] args){
		int [] answer = null;
		
		
		answer = solution(34);

		printIntArr(answer);

		answer = step(answer,35);
		printIntArr(answer);
		
	}
	public static void printIntArr(int [] a){
		for(int i = a.length-1; i > -1 ; i--){
			System.out.print(a[i]+",");
		}
		System.out.println();
	}
}
