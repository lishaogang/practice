import java.util.Arrays;
import java.util.Scanner;

public class Game {

	/**
	 * @param args
	 */
	public static int solution(int f, int s, int ns[], int index, int init) {
		// 最后一步，此时判断自己能否赢
		if (init - ns[0] < 0) {
			if (f % 2 == 1 && s % 2 == 0)
				return 1;
			if (f % 2 == 1 && s % 2 == 1)
				return 0;
			if (f % 2 == 0 && s % 2 == 0)
				return 0;
			return -1;

		}
		// 不是最后一步，则判断对手是否能赢
		int r = -1;
		int t = 0;
		// init - ns[0] > 0
		while (init - ns[index] < 0)
			index--;
		for (int i = 0; i < ns.length; i++) {

			t = solution(s, f + ns[index], ns, i, init - ns[index]);
			r = t > r ? t : r;
		}

		// 若对手能赢，则自己会输
		return -1 * r;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int[] ns = new int[3];
		int[] init = new int[5];
		for (int i = 0; i < 3; i++) {
			ns[i] = sc.nextInt();
		}
		for (int i = 0; i < 5; i++) {
			init[i] = sc.nextInt();
		}
		sc.close();
		// 排序
		Arrays.sort(ns);

		char sign[] = new char[3];
		sign[0] = '-';
		sign[1] = '0';
		sign[2] = '+';
		for (int i = 0; i < init.length; i++) {
			// 若有求解方程F
			// 可取数目为init[n]，共M个球，则F(M) = max(F(M-init[0]),...,F(M-init[N-1])
			int r = -1;
			int t = 0;
			for (int j = 0; j < ns.length; j++) {

				t = solution(0, 0, ns, j, init[i]);
				r = t > r ? t : r;
			}
			System.out.print(sign[r + 1] + " ");
			r = -1;
		}
	}

}
