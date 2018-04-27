// To improve data flow of this procedure: Enumerate three-digit numbers ---> filter these numbers
public class cubical {
	public static void solution(){
		int z = 0;
		for (int a = 1; a < 10; a++) {
			int cube_a = a*a*a;
			int hun = a*100;
			for (int b = 0; b < 10; b++) {
				int cube_b = b*b*b;
				int dec = b*10;
				for (int c = 0; c < 10; c++) {
					z = cube_a + cube_b + c*c*c;
					if(z == hun + dec + c)
						System.out.println(z);
				}
			}
		}
	}

	public static void main(String[] args) {
		solution();
	}
}