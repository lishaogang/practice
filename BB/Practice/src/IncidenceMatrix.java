import java.util.Scanner;

public class IncidenceMatrix {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int m,n,in_degree,out_dgree;
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        int [][] matrix = new int[n][m];
        for(int i = 0 ; i < m; i++){
        	in_degree = sc.nextInt();
            out_dgree = sc.nextInt();
        	matrix[out_dgree-1][i] = 1;
        	matrix[in_degree-1][i] = -1;
        }
        for(int i = 0; i < n; i++){
        	for(int j = 0; j < m; j++){
        		System.out.print(matrix[i][j]+" ");
        	}
        	System.out.println();
        }
	}
}
