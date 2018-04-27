import java.lang.Math;
public class area {
	public static double getArea(int r){
		return Math.PI * r * r;
	}
	public static void main(String[] args) {
		System.out.print(String.format("%.7f",getArea(4)));
	}
}