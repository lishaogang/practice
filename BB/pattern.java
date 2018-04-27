import java.util.Scanner;
public class pattern {
	public static void solution(){
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int m = in.nextInt();
		char [] map = new char[26];
		for (int i = 0, ch = 'A'; i < 26; i++, ch++) {
			map[i] = (char)ch;

		}

		for (int line = 0; line < n; line++) {
				//inverted order
				for (int j = line % m; j > 0; j--) {
					System.out.print(map[j]);
				}
				//normal order
				for (int j = 0; j < m - (line % m); j++) {
					System.out.print(map[j]);
				}
				System.out.println();
		}
	}
	public static void main(String[] args) {
		solution();
	}
}