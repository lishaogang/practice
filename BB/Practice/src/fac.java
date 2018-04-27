
public class fac {
	static int[] product = new int[10000]; //���
	static int count = 1;   //�������
	
	public static int[] solution(int n){
		if(n <= 0)
			return null;
		//��ʼ��
		product[0]=1;
		count = 1; //�����ڼ���֮ǰ��ֵΪһ
		int [] r = null;
		//��ʼ���㣬����������1000������int�� ����ѭ���е�i
		for(int i = 2; i <= n; i++){
			//step(int [] product,  int n)��һ�����������飬Java�е��ú��������������Ͳ��������ں����иı�Ԫ��ֵ������ִ���귵��֮�󣬱���������鱻�ı�
			step(product, i);
		}
		//������
		r = product;
		//����
		product = new int[10000];

		return r;
		
	}
	//����һ��С��n��һ������product���
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
	
	//��λ
	private static void carry(int[] product, int carry_bit){
		while(carry_bit > 0){
			product[count++] = carry_bit %10;
			carry_bit = carry_bit / 10;
		}
	}
	
	public static void main(String [] args){
		long start = 0, end = 0;
		int [] answer = null;
		//���Լ��� 1000!
		start = System.currentTimeMillis();
		answer = solution(1000);
		end = System.currentTimeMillis();
		
		System.out.printf("1000��: ");
		printIntArr(answer);
		System.out.printf("time of calculating 1000�� : %dms\n", (end - start));
		//���Լ���35��
		answer = solution(35);
		System.out.printf("35��: ");
		printIntArr(answer);
		
	}
	
	//��� int[]
	public static void printIntArr(int [] a){
		for(int i = count-1; i > -1 ; i--){
			System.out.print(a[i]);
		}
		System.out.println();
	}
}
