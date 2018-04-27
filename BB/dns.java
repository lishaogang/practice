import java.lang.Math;
public class dns {
	static int N = 8;
	static int board[] = new int[N];
	private static boolean tryPut(int k){
		for (int i = 0; i < k; i++) {
			if(Math.abs(board[i] - board[k]) == Math.abs(i - k) || board[k] == board[i])
				return false;
		}
		return true;
	}

	public static int track(int n){
		if(n == N){
			return 1;
		}

		int status = 0;
		for (int i = 0; i < N; i++) {
			board[n] = i;
			if(tryPut(n)){
				status += track(n+1);
			}
		}
		return status;
	}
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		
		//int answer = NQueen(b,Queen.WHITE,N);
		int answer = track(0);

		long end = System.currentTimeMillis();
		System.out.printf("time:%dms\n",(end - start));
	}

}