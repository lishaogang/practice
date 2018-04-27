/*
 enum direction{
 
	UP,DOWN,LEFT,RIGHT
}//*/
public class dualRect {
	public static void solution(int [][] a){
		//bound[0] : y, bound[1] : x
		int [] bound = {a.length-1,a[0].length-1,0,0};
		//direction: 0-->move up, direction: 1--> move down, direction: 2--> move left, direction: 3-->move right
		int direction = 1;
		int count = a[0].length*a.length;
		//coor[0] : y, coor[1] : x;
		int [] coor = {0,0};

		while(count > 0){
			//int t = a[coor[0]][coor[1]];
			System.out.print(a[coor[0]][coor[1]]+" ");
			move(direction, coor);
			direction = changeDire(direction, coor, bound);
			count--;
		}
	}
	
	private static int changeDire(int direction, int [] coor, int [] bound){
		int dire = direction ;
		int []b = new int[bound.length];
		System.arraycopy(bound, 0, b, 0, bound.length);
		
		if(coor[0] == bound[0] && coor[1] == bound[2]){
			bound[2] += 1;
			dire =  3;
			
		}
		if(coor[0] == bound[0] && coor[1] == bound[1]){
			bound[0] -= 1;
			dire =  0;
			
		}
		if(coor[0] == bound[3] && coor[1] == bound[1]){
			bound[1] -= 1;
			dire =  2;
			
		}
		if(coor[0] == bound[3] && coor[1] == bound[2]){
			bound[3] += 1;
			dire =  1;
			
		}
		if(dire == direction)
			System.arraycopy(b, 0, bound, 0, bound.length);
		return dire;
	}
	
	
	private static void move(int dire, int [] coor){

		
		switch(dire){
			case 0:
				coor[0]--;
				break;
			case 1:
				coor[0]++;
				break;
			case 2:
				coor[1]--;
				break;
			case 3:
				coor[1]++;
				break;
			default:
				System.exit(-1);
				
		}
		
		//System.out.print("("+coor[0]+","+coor[1]+") ");
		
		
	}
	private static void init(int [][]a){
		for(int i = 0; i < a.length;i++){
			for(int j = 0; j < a[i].length; j++){
				a[i][j] = i*10+j;
				System.out.print(a[i][j]+"  ");
			}
			System.out.println();
		}
	}
	//数组arr[m][n]   最多有 (min(m,n)+1)/2圈
	public static void solution_1(int [][] arr){
		int i = 0,j = 0;
		int n = arr[0].length, m = arr.length;
		for (i = 0; i < (n + 1) / 2 && i < (m + 1) / 2; i++)  
        {  
            for (j = i; j < m - i; j++)  
                System.out.print(arr[j][i]+"↓，");  
            for (j = i + 1; j < n - i; j++)  
                System.out.print(arr[m - i - 1][j]+"→");  
            //if (n - i - 1 > i)
                for (j = m - i - 2; j >= i; j--)  
                    System.out.print( arr[j][n - i - 1]+"↑");  
            //if (m - i - 1 > i)  
                for (j = n - i - 2; j > i; j--)  
                    System.out.print( arr[i][j]+"←");  
        }
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int a[][] = new int[5][5];
		init(a);
		solution_1(a);
		
		
	}

}
