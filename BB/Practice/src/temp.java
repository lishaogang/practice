import java.math.BigInteger;  
import java.util.Scanner;  
  
class temp {  
    public static void main(String[] args) {  
            //Scanner in = new Scanner(System.in);  
            //int n = in.nextInt();  
            //����̨����������Ϊ1232���Ľ��
            int n = 1232;
            BigInteger ans = BigInteger.ONE;  
            long start, end;
            start = System.currentTimeMillis();
            for(int i = 1; i<=n; i++) {  
                 ans = ans.multiply(BigInteger.valueOf(i));  
            }
            end = System.currentTimeMillis();
            System.out.printf("time of calculating 1232�� : %dms\n", (end - start));
            System.out.println(ans);  
    }  
}  