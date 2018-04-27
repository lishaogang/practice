package prac;

import java.util.Random;
import java.util.Scanner;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

public class demo {

	/**
	 * @param args
	 */

	static int MAXN = 1000105;// 以H的范围确定

	static int bit[] = new int[MAXN];
	static int n;
	static int h[] = new int[MAXN];
	static int t[] = new int[MAXN];
	static int s[] = new int[MAXN];
	static long ans;

	static int lowbit(int i) {
		return i & (-i);
	}

	static void add(int i, int x) {
		while (i <= MAXN) {
			bit[i] += x;
			i += lowbit(i);
		}
	}

	private static int sum(int i) {
		int ans = 0;
		while (i > 0) {
			ans += bit[i];
			i -= lowbit(i);
		}
		return ans;
	}

	public static void main(String [] args)
	{
		Scanner sc = new Scanner(System.in);
		Random random = new Random(10);
		for(int i = 0; i < 10;i++)
			System.out.print(Math.abs(random.nextInt())%1000000+" ");
	    n = sc.nextInt();
	    long start = System.currentTimeMillis();
	    for(int i=0;i<n;i++)
	    {
	        h[i] = Math.abs(random.nextInt()) % 1000000;//sc.nextInt();
	        h[i]++;            //注意:测试数据存在身高为0的小朋友 
	        t[i]=i-sum(h[i]);
	        add(h[i],1);
	        s[i+1]=s[i]+(i+1);    
	    }
	    bit = new int[MAXN];
	    
	    for(int i=n-1;i>=0;i--)
	    {
	        t[i]+=sum(h[i]);
	        add(h[i]+1,1);    //注意加1 这样可去掉相同身高 
	        ans+=s[t[i]];
	    }
	    long end = System.currentTimeMillis();
	    System.out.println(end-start);
	    System.out.println(ans);
	}
}
