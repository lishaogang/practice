import java.math.BigInteger;


public class bigAddition {

	public static String solution(int a, int b){
		BigInteger big_a = BigInteger.valueOf(a);
		BigInteger big_b = BigInteger.valueOf(b);
		return big_a.add(big_b)+"";

	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(solution(3,5));
	}

}
