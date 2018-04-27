import java.util.HashSet;
import java.util.Set;

public class MS {
	static int arr[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
	static int count = 0;

	public static int check(int[] a) {
		int[] c = new int[8];
		c[0] = a[0] + a[1] + a[2];
		c[1] = a[3] + a[4] + a[5];
		c[2] = a[6] + a[7] + a[8];
		c[3] = a[0] + a[3] + a[6];
		c[4] = a[1] + a[4] + a[7];
		c[5] = a[2] + a[5] + a[8];
		c[6] = a[0] + a[4] + a[8];
		c[7] = a[2] + a[4] + a[6];
		Set<Integer> s = new HashSet();
		for (int i = 0; i < 8; i++) {
			s.add(c[i]);
		}
		return s.size() == 8 ? 1 : 0;
	}

	public static int solution(int[] a, int n) {
		if (n == 9)
			return check(a);
		int r = 0;
		for (int i = n; i < 9; i++) {
			int t = a[i];
			a[i] = a[n];
			a[n] = t;
			r += solution(a, n + 1);
			t = a[i];
			a[i] = a[n];
			a[n] = t;
		}
		return r;
	}

	public static void main(String[] args) {
		System.out.println(solution(arr, 0));

	}
}