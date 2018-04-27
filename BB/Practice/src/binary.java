public class binary {
	public static void solution(){
		//final byte [] map = {0,1};
		for (byte line = 0; line < 32; line++) {
			byte mask = (byte)0x10;
			int flag = 0;
			for (byte i = 0; i < 5; i++) {
				flag = (line & mask) > 0 ? 1 : 0;
				System.out.print(flag);
				mask = (byte)(mask >>> 1);
			}
			System.out.println();
		}
	}
	public static void main(String[] args) {
		solution();
	}
}
	
