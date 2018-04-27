
public class nowtime {
	private static String[] map = null;
	private static void init(){
		map = new String[61];
		map[0]="zero";
		map[1]="one";
		map[2]="two";
		map[3]="three";
		map[4]="four";
		map[5]="five";
		map[6]="six";
		map[7]="seven";
		map[8]="eight";
		map[9]="nine";
		map[10]="ten";
		map[11]="eleven";
		map[12]="twelve";
		map[13]="thirteen";
		map[14]="fourteen";
		map[15]="fifteen";
		map[16]="sixteen";
		map[17]="seventeen";
		map[18]="eighteen";
		map[19]="nineteen";
		map[20]="twenty";
		map[30]="thirty";
		map[40]="forty";
		map[50]="fifty";
		map[60]="o'clock";
		for(int i = 21; i < 60; i++)
			map[i] =i % 10 != 0 ?  map[i/10]+" "+map[i%10] : map[i];
	}
	private static void tellHour(int h){
		if(h < 0 || h > 12)
			return;
		System.out.print(map[h]+" ");
	}
	private static void tellMinute(int m){
		if(m < 0 || m > 59)
			return;
		m = m > 0 ? m : 60;
		System.out.print(map[m]);
	}
	public static void tellTime(int h, int m){
		tellHour(h);
		tellMinute(m);
		
	}
	/**
	 * @param args
	 */
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		init();
		for(int h = 0; h < 13; h++)
			for(int m = 0; m < 60; m++){
				tellTime(h,m);
				System.out.println();
			}
	}

}
