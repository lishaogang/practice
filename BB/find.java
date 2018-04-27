import java.util.Scanner;
public class find {
	public static void solution(){
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int [] nums = new int[n];
		for (int i = 0; i < n; i++) {
			nums[i] = in.nextInt();
		}
		int a = in.nextInt();
		int index = 0;
		for (index = 0; index < n; index++) {
			if(nums[index] != a)
				continue;
			else
				break;
		}
		System.out.println(index < n ? index+1 : -1);
	}
	public static void main(String[] args) {
		solution();
	}
}