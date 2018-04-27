public class treearray {

	static int[] c;

	private static int lowbit1(int i) {
		return i & (-i);
	}

	private static void add(int p,int v){
		
		while(p < c.length){
			c[p] += v;
			p += lowbit1(p);
		}
	}

	private static int sum(int i){
		int r = 0;
		while(i > 0){
			r += c[i];
			i -= lowbit1(i);
		}
		return r;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		c = new int[9];
		for(int i = 0; i <= 8;i++){
			add(i+1,i%2);
		}
		System.out.println("added");
		for(int i = 1; i <= 8;i++){
			System.out.println(sum(i));
		}
		for(int i = 1; i <= 8;i++){
			System.out.println("-"+c[i]);
		}
	}

}
