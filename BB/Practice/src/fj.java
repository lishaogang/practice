import java.io.IOException;
import java.io.RandomAccessFile;


public class fj {
	public static String solutoin(int n){
		if(n == 1)
			return "A";
		String F = solutoin(n-1);
		char mid = (char) ('A'+n-1);
		return F+mid+F;
	}
	public static void save(String filename,String content){
		try {
			RandomAccessFile file = new RandomAccessFile(filename,"rw");
			long fileLength = file.length();
			file.seek(fileLength);
			file.writeBytes(content);
			file.writeChars("\n-------------------\n");
			file.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(solutoin(4));
		//System.out.print(solutoin(24));
		//save("./fj.txt",solutoin(24));
	}

}
