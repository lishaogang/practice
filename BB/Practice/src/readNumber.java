import java.util.ArrayList;
import java.util.Scanner;
public class readNumber {
	private static  String[] units = {"","shi","bai","qian","wan"};
	private static  String[] map = {"wan","Yi"};
	private static  String[] nums = {"ling","yi","er","san","si","wu","liu","qi","ba","jiu"};
	public static StringBuilder solution(int a){
		int r = 0;
		int i = 0, k = 0;
		int pre = 0;
		String read = "";
		for(;a != 0;k++){
			int t = a % 10000;
			a = a / 10000;
			i = 0;
			pre = 0;
			for(;t != 0;i++){
				r = t % 10;
				t = t / 10;
				if(r != 0){
					read = " " + nums[r] + " " + units[i] + read;
				}else if(pre != 0){
					read = " "+nums[r] + read;
					
				}
				pre = r;
				
			}
			if(a != 0 && i < 4 && k != 0)
				read = " ling " + read;
			if(a != 0 && a % 10000 != 0)
			read = " " + map[k] + " " + read;
		}
		
			
		System.out.println(read);
		return process(read);
	}
	private static  StringBuilder process(String read) {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		ArrayList<String> al = new ArrayList<String>();
		String [] words = read.split(" ");
		for(int i = 1; i < words.length; i++){
			al.add(words[i]);
		}
		if(al.get(0).equals("yi") && al.get(1).equals("shi")){
			al.remove(0);	
		}
		
		for(String e:al){
			sb.append(" "+e);
		}
		sb.replace(0, 1, "");
		return sb;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);  
        int s = input.nextInt();  
        input.close();  
		System.out.println(solution(s));
	
	}

}
