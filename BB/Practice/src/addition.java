
public class addition {
	public static int [] solution(int [] a,int [] b){
		if(a.length > 100 || b.length > 100)
			return null;
		int [] r = null;
		r = a;
		a = a.length > b.length ? a : b;
		b = r.length > b.length ? b : r;
		int carry  = 0;
		r = new int[101];
		int t = 0;
		int i = 0;
		for(i = 0; i < b.length; i++){
			t = (a[i] + b[i]);
			r[i] = (t+carry) % 10;
			carry = (t+carry) / 10;
		}
		
		while(i < a.length){
			t = (a[i]+carry);
			r[i++] = t % 10;
			carry = t /10;
		}
		while(carry != 0){
			r[i++] = carry % 10;
			carry = carry /10;
		}
		return r;
	}
	public static void init(int [] s){
		for(int i = 0; i < s.length; i++)
			s[i] = i % 10;
	}
	public static void main(String [] args){
		int [] a = new int[100];
		int [] b = new int[100];
		init(a);init(b);
		print(a);
		int [] r = solution(a,b);
		print(r);
	}
	public static void print(int [] s){
		int index = s.length-1;
		for(; index >= 0; index--)
			if(s[index]!=0)
				break;
		for(; index >= 0; index--)
			System.out.print(s[index]);
		System.out.println();
	}
}
