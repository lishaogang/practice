import java.util.Scanner;


public class getRect {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int m = 0 , n = 0;
		
		Scanner sc = new Scanner(System.in);
		m = sc.nextInt();
		n = sc.nextInt();
		int [][] arr = new int[m][n];
		for(int i = 0; i < m; i++)
			for(int j = 0; j < n; j++)
				arr[i][j] = sc.nextInt();
		for(int i = 0; i <= m/2 && i <= n/2; i++){
			//down print the (i) column 
			for(int row = i;row < m-i; row++)
				System.out.print(arr[row][i]+" ");
			//right print the (m-i-1) row
			for(int col = i+1;col < n-i; col++)
				System.out.print(arr[m-i-1][col]+" ");
			//up print the (n-i-1) column
			for(int row = m-i-2;row >= i; row--)
				System.out.print(arr[row][n-i-1]+" ");
			//left print the (i) row
			for(int col = n-i-2; col>i; col--)
				System.out.print(arr[i][col]+" ");
			}
		
	}

}
