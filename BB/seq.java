public class seq {
	public static double sumSeq(int n){
		double t = n;
		return 0.5 * t * (1 + t);
	}
	public static void main(String[] args) {
		System.out.print(String.format("%.0f",sumSeq(10)));
	}
}