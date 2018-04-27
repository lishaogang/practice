import java.util.Scanner;  
  
  
public class Cow {  
  
    class edge {  
        public int a;  
        public int b;  
        public int value;  
          
        edge(int a, int b, int value) {  
            this.a = a;  
            this.b = b;  
            this.value = value;  
        }  
    }  
      
    public void edgeSort(edge[] A){  
        if(A.length > 1) {  
            edge[] leftA = getHalfEdge(A, 0);  
            edge[] rightA = getHalfEdge(A, 1);  
            edgeSort(leftA);  
            edgeSort(rightA);  
            mergeEdgeArray(A, leftA, rightA);  
        }  
    }  
      
    public edge[] getHalfEdge(edge[] A, int judge) {  
        edge[] half;  
        if(judge == 0) {  
            half = new edge[A.length / 2];  
            for(int i = 0;i < A.length / 2;i++)  
                half[i] = A[i];  
        } else {  
            half = new edge[A.length - A.length / 2];  
            for(int i = 0;i < A.length - A.length / 2;i++)  
                half[i] = A[A.length / 2 + i];  
        }  
        return half;  
    }  
      
    public void mergeEdgeArray(edge[] A, edge[] leftA, edge[] rightA) {  
        int i = 0;  
        int j = 0;  
        int len = 0;  
        while(i < leftA.length && j < rightA.length) {  
            if(leftA[i].value < rightA[j].value) {  
                A[len++] = leftA[i++];  
            } else {  
                A[len++] = rightA[j++];  
            }  
        }  
        while(i < leftA.length) A[len++] = leftA[i++];  
        while(j < rightA.length) A[len++] = rightA[j++];  
    }  
    //获取节点a的根节点  
    public int find(int[] id, int a) {  
        int x, r, k;  
        r = a;  
        while(id[r] >= 0) r = id[r];  
        k = a;  
        while(k != r) {  
            x = id[k];  
            id[k] = r;  
            k = x;  
        }  
        return r;  
    }  
    //合并a节点所在树和b节点所在树  
    public void union(int[] id, int a, int b) {  
        int ida = find(id, a);  
        int idb = find(id, b);  
        int num = id[ida] + id[idb];  
        if(id[ida] < id[idb]) {  
            id[idb] = ida;  
            id[ida] = num;  
        } else {  
            id[ida] = idb;  
            id[idb] = num;  
        }  
    }  
    //获取题意最终结果  
    public void getMinSpanTree(edge[] A, int[] valueN) {  
        int sum = 0;  
        int[] id = new int[valueN.length];  
          
        for(int i = 0;i < valueN.length;i++)   
            id[i] = -1;  
        edgeSort(A);  
        int count = 0;  
        for(int i = 0;i < A.length;i++) {  
            int a = A[i].a;  
            int b = A[i].b;  
            int ida = find(id, a - 1);  
            int idb = find(id, b - 1);  
            if(ida != idb) {  
                sum += A[i].value;  
                count++;  
                union(id, a - 1, b - 1);  
            }  
            if(count >= valueN.length - 1)  
                break;  
        }  
          
        int minValueN = valueN[0];  
        for(int i = 0;i < valueN.length;i++) {  
            if(minValueN > valueN[i]) {  
                minValueN = valueN[i];  
            }  
        }  
        sum += minValueN;  
        System.out.println(sum);  
    }  
      
    public static void main(String[] args){  
        Cow test = new Cow();  
        Scanner in = new Scanner(System.in);  
        int n = in.nextInt();  
        int p = in.nextInt();  
        if(n > 10000 || n < 5)  
            return;  
        if(p > 100000 || p < n - 1)  
            return;  
        int[] valueN = new int[n];  
        for(int i = 0;i < n;i++)  
            valueN[i] = in.nextInt();  
        edge[] A = new edge[p];  
        for(int i = 0;i < p;i++) {  
            int a = in.nextInt();  
            int b = in.nextInt();  
            int value = in.nextInt() * 2 + valueN[a - 1] + valueN[b - 1];  
            A[i] = test.new edge(a, b, value);  
        }  
        test.getMinSpanTree(A, valueN);  
    }  
} 