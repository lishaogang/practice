
public class fac {
	static int[] product = new int[10000]; //结果
	static int count = 1;   //结果长度
	
	public static int[] solution(int n){
		if(n <= 0)
			return null;
		//初始化
		product[0]=1;
		count = 1; //必须在计算之前赋值为一
		int [] r = null;
		//开始计算，乘数不大于1000，则用int， 即此循环中的i
		for(int i = 2; i <= n; i++){
			//step(int [] product,  int n)第一个参数是数组，Java中调用函数传入数组类型参数，若在函数中改变元素值，函数执行完返回之后，被传入的数组被改变
			step(product, i);
		}
		//保存结果
		r = product;
		//归零
		product = new int[10000];

		return r;
		
	}
	//计算一个小数n和一个大数product相乘
	private static void step(int [] product,  int n){
		int carry = 0;
		int t = 0;
		for(int j = 0; j < count; j++){
			
			t = product[j]*n;
			
			product[j] = (t + carry)%10;
			
			carry = (t + carry)/10;
		
		}
		
		carry(product,carry);
		
	}
	
	//进位
	private static void carry(int[] product, int carry_bit){
		while(carry_bit > 0){
			product[count++] = carry_bit %10;
			carry_bit = carry_bit / 10;
		}
	}
	
	public static void main(String [] args){
		long start = 0, end = 0;
		int [] answer = null;
		//测试计算 1000!
		start = System.currentTimeMillis();
		answer = solution(1000);
		end = System.currentTimeMillis();
		
		System.out.printf("1000！: ");
		printIntArr(answer);
		System.out.printf("time of calculating 1000！ : %dms\n", (end - start));
		//测试计算35！
		answer = solution(35);
		System.out.printf("35！: ");
		printIntArr(answer);
		
	}
	
	//输出 int[]
	public static void printIntArr(int [] a){
		for(int i = count-1; i > -1 ; i--){
			System.out.print(a[i]);
		}
		System.out.println();
	}
}
