import java.util.HashSet;
import java.util.Set;
public class demo{
	public static void fun(int arg[]){
		if(arg != null)
			arg[0] = 99;
	}
	public static void gnu(int i){
		i = 99;
	}

	public static void main(String args[]){
		Set s = new HashSet();
		s.add(1);
		System.out.println(s.size());
	}
}