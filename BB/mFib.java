public class mFib {
	public static int Fn(int n){
		int a = 1, b = 1;
		int c = 0;
		for(int i = 2; i < n; i++){
			c = (a + b) % 10007;
			a = b;
			b = c;
		}
		return c;
	}
	public static void main(String[] args) {
		System.out.print(Fn(1000000));
	}
}