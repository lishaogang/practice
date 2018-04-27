public class pascalTri {
	public static void solution(int n){
		int nums[] = new int[n];
		int pre = 1;
		for (int line = 0; line < n; line++) {
			//every line has 'line' number(s)
			for (int i = 0; i <= line; i++) {
				int cur = nums[i];
				nums[i] = cur + pre;
				pre = cur;
				System.out.print(nums[i] + " ");
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		//solution(4);
	}
}