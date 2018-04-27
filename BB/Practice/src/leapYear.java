import java.util.Scanner;
public class leapYear {
	public static void solution(){
		Scanner in = new Scanner(System.in);
		int y = in.nextInt();
		if(!(y % 4 == 0 && y % 100 != 0) && !(y % 400 == 0)){
			System.out.println("no");
		}
		System.out.println("yes");
	}
	public static void main(String[] args) {
		solution();
	}
}