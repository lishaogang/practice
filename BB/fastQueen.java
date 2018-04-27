import java.lang.Math;
public class fastQueen {
	static int N = 8;
	static int wBoard[] = new int[N];
	static int bBoard[] = new int[N];
	static int count = 0;
	private static boolean tryPutWhite(int k){
		for (int i = 0; i < k; i++) {
			if(Math.abs(wBoard[i] - wBoard[k]) == Math.abs(i - k) || wBoard[k] == wBoard[i])
				return false;
		}
		return true;
	}

	private static boolean tryPutBlack(int k){
		//*
		if(bBoard[k] == wBoard[k]){
			count++;
			return false;
		}//*/

		for (int i = 0; i < k; i++) {
			/* DO NOT decide whether it is applicable to put the black queen 
			//in k row bBoard[k] column
			if(bBoard[k] == wBoard[k]){
				count++;
				return false;
			}//*/

			if(Math.abs(bBoard[i] - bBoard[k]) == Math.abs(i - k) || bBoard[k] == bBoard[i])
				return false;
		}
		return true;
	}

	public static int putBlack(int n){
		if(n == N){
			return 1;
		}

		int status = 0;
		for (int i = 0; i < N; i++) {
			bBoard[n] = i;
			if(tryPutBlack(n)){
				status += putBlack(n+1);
			}
		}
		return status;
	}
	public static int putWhite(int n){
		if (n == N) {
			//return 1;
			return putBlack(0);
		}
		int status = 0;
		for (int i = 0; i < N; i++) {
			wBoard[n] = i;
			if(tryPutWhite(n)){
				status += putWhite(n+1);
			}
		}
		return status;
	}
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		
		//int answer = NQueen(b,Queen.WHITE,N);
		int answer = putWhite(0);

		long end = System.currentTimeMillis();
		System.out.printf("count:%d answer:%d, time:%dms\n", count, answer, (end - start));
	}

}