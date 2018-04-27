import java.util.Arrays;
import java.util.Scanner;


public class GameFailed {

	/**
	 * @param args
	 */
	static int acc = 0;
	public static char solution(int first, int second,int ns[], int init){
		if(init - first - second - ns[0] < 0 ){
			if(first % 2 == 1 && second % 2 == 0) return '+';
			if(first % 2 == 0 && second % 2 == 1) return '-';
			return '0';
		}
		String r = null;
		int f = first, s = second;
		for(int i = 0; i < ns.length; i++){
			first += init - f - s - ns[i] >= 0 ? ns[i] : 0;
			if(first == f)
				break;
			for(int j = 0; j < ns.length; j++){
				second += init - first - s - ns[j] >= 0 ? ns[j] : 0;
				r += solution(first, second, ns, init);
				second = s;
			}
			first = f;
		}
		if (r.contains("-"))
			return '-';
		if (r.contains("0"))
			return '0';
		
		return '+';
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int [] ns = new int[3];
		int [] init = new int[5];
		for(int i = 0; i < 3;i++){
			ns[i] = sc.nextInt();
		}
		for(int i = 0; i < 5; i++){
			init[i] = sc.nextInt();
		}
		sc.close();
		Arrays.sort(ns,0,ns.length);
		for(int i = 0; i < init.length;i++)
			System.out.println(solution(0,0,ns, init[i]));
		
	}

}
