enum Color{
	NULL, WHITE
}
public class temp {
	public static void change(int arr[]){
		int a[];

		arr[0] = 99;
	}
	public static void main(String[] args) {
		Color colors[][] = new Color[5][5];
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				colors[i][j] = Color.WHITE;					
			}				
		}
		System.out.println(colors[0][0]);
		Color copy[][] = new Color[5][5];
		System.out.println(copy[0][0]);
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				copy[i][j] = colors[i][j];
			}
		}
		colors[0][0] = Color.NULL;

		System.out.println(copy[0][0]);
		
	}
}