import java.util.Scanner;
public class series {
	public static void solution(){
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		if(n <= 0)
			return;
		int [] nums = new int[n];
		for (int i = 0; i < n; i++) {
			nums[i] = in.nextInt();
		}

		int max = nums[0], min = nums[0], sum = 0;
		for (int i = 0; i < n; i++) {
			sum += nums[i];
			max = max > nums[i] ? max : nums[i];
			min = min < nums[i] ? min : nums[i];
		}

		System.out.println(max);
		System.out.println(min);
		System.out.println(sum);

	}
	public static void main(String[] args) {
		solution();
	}
}