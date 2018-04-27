// 5: mid+2*a+2*b=n
// 6: 2*mid+2*a+2*b=n
public class pal {
	public static void getFivePalOf(int n){
		int half = n/2 > 9 ? 9 : n/2;
		for (int mid = 0; mid < n; mid++) {
			for (int b = 0;  b <= half; b++) {
				for (int a = 1; a <= half; a++) {
					if(mid+2*a+2*b == n)
						System.out.println(a+b*10+mid*100+b*1000+a*10000);
				}
			}
		}
	}

	public static void getSixPalOf(int n){
		int half = n/2 > 9 ? 9 : n/2;
		for (int mid = 0; mid < half; mid++) {
			for (int b = 0; b <= half; b++) {
				for (int a = 1; a <= half; a++) {
					if(2*mid+2*a+2*b == n)
						System.out.println(a+b*10+mid*100+mid*1000+b*10000+a*100000);
				}
			}			
		}
	}
	//For 4 bits palindrome
	public static void palin(){
		for (int a = 1; a < 10; a++) {
			for (int b = 0; b < 10; b++) {
				System.out.println(a+b*10+b*100+a*1000);
			}
			
		}
	}
	public static void testSumPal(){
		for (int i = 8; i < 12; i++) {
			System.out.println("sum: "+i+"  5 bits");
			getFivePalOf(i);
			System.out.println("sum: "+i+"  6 bits");
			getSixPalOf(i);
			System.out.println("--------------------");
			
		}
	}
	public static void main(String[] args) {
		palin();

	}

}
