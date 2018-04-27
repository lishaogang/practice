import java.util.Scanner;
import java.lang.Math;
public class scale {
	public static void main(String[] args) {
		// String [] hexs = input(1);
		// String [] octs = hex2oct(hexs);
		// for(String oct:octs){
		// 	System.out.println(oct);
		// }
		String hex = dec2hex(170);
		System.out.println(hex);
	}

	public static String dec2hex(double dec){
		char [] BYTE2HEX = new char[16];
		char [] hexs = {'1','2','3','4','5','6','7','8','9','A','B','C','D','E','F','G'};
		for(int index = 0; index < 16; index++){
			BYTE2HEX[index]  = hexs[index];
		}

		StringBuffer hex = new StringBuffer();
		double r = 0;

		while(dec > 1){
			int re = (int)(dec % 16.0) - 1;

			hex.append(BYTE2HEX[re]);
			dec = dec / 16;
		}
		return hex.reverse().toString();
	}
	public static double hex2dec(String hex){
		byte [] HEX2BYTE = new byte[256];
		char [] hexs = {'1','2','3','4','5','6','7','8','9','A','B','C','D','E','F','G'};
		byte acc = (byte)0x01;
		for(char index:hexs){
			HEX2BYTE[index] = acc++;
		}

		double dec = 0.0;
		int length = hex.length();
		for(int i = 0; i < length; i++){
			dec += HEX2BYTE[hex.charAt(i)] * Math.pow(16,length - i - 1);
		}
		return dec;
	}
	public static String [] hex2oct(String [] hexs){
		String [] bins = hex2bin(hexs);
		String [] octs = bin2oct(bins);
		return octs;
	}

	private static String [] hex2bin(String [] hexs){
		String bins[] = new String[hexs.length];
		for(int i = 0; i < bins.length; i++)
			bins[i] = "";
		for(int j = 0; j < hexs.length; j++){
			int length = hexs[j].length();
			for(int i = 0; i < length; i++){
				switch(hexs[j].charAt(i)){
					case '0':bins[j] += "0000";break;
					case '1':bins[j] += "0001";break;
					case '2':bins[j] += "0010";break;
					case '3':bins[j] += "0011";break;
					case '4':bins[j] += "0100";break;
					case '5':bins[j] += "0101";break;
					case '6':bins[j] += "0110";break;
					case '7':bins[j] += "0111";break;
					case '8':bins[j] += "1000";break;
					case '9':bins[j] += "1001";break;
					case 'A':bins[j] += "1010";break;
					case 'B':bins[j] += "1011";break;
					case 'C':bins[j] += "1100";break;
					case 'D':bins[j] += "1101";break;
					case 'E':bins[j] += "1110";break;
					case 'F':bins[j] += "1111";break;
				}
			}
		}
		return bins;
	}
	private static String [] bin2oct(String [] bins){
		String [] octs = new String[bins.length];
		for(int i = 0; i < octs.length; i++)
			octs[i] = "";
		for (int i = 0; i < bins.length; i++) {
			int length = bins[i].length();
			

			if(length % 3 == 1)	bins[i] = "00" + bins[i];
			else if(length % 3 == 2) bins[i] = "0" + bins[i];
			length = bins[i].length();
			for (int j = 0; j <= length-3; j+=3) {
				int a = (bins[i].charAt(j) - '0')*4 + (bins[i].charAt(j+1) - '0')*2 + (bins[i].charAt(j+2)-'0');
				
				octs[i] += Integer.toString(a);
			}
		}
		return octs;
	}
	
	public static String [] input(int n){
		String [] s = new String[n];
		Scanner in = new Scanner(System.in);
		for(int i = 0; i < n; i++){
			s[i] = in.nextLine();
		}
		in.close();
		return s;
	}
}