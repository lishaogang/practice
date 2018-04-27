import java.util.Scanner;

public class Floyd {
    
    public void floyd(long[][] adjMatrix) {
        for(int k = 0;k < adjMatrix.length;k++) {
            for(int i = 0;i < adjMatrix.length;i++) {
                for(int j  = 0;j < adjMatrix.length;j++) {
                    if(adjMatrix[i][k] != Integer.MAX_VALUE && adjMatrix[k][j] != Integer.MAX_VALUE) {
                        if(adjMatrix[i][j] > adjMatrix[i][k] + adjMatrix[k][j])
                            adjMatrix[i][j] = adjMatrix[i][k] + adjMatrix[k][j];
                    }
                }
            }
         }
        for(int i = 1;i < adjMatrix.length;i++)
            System.out.println(adjMatrix[0][i]);
    }
    
    public static void main(String[] args) {
        Floyd test = new Floyd();
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        if(n > 20000 || n < 1 || m > 200000 || m < 1)
            return;
        long[][] adjMatrix = new long[n][n];
        for(int i = 0;i < n;i++) {
            for(int j = 0;j < n;j++)
                adjMatrix[i][j] = Integer.MAX_VALUE;
        }
        for(int i = 0;i < m;i++) {
            int a = in.nextInt();
            int b = in.nextInt();
            int value = in.nextInt();
            if(value > 10000 || value < -10000)
                return;
            adjMatrix[a - 1][b - 1] = value;
        }
        test.floyd(adjMatrix);
    }
    
}