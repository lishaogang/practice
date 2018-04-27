package prac;

import java.util.ArrayList;
import java.util.Scanner;

public class readnumber {

	/**
	 * @param args
	 */
	 public static void main(String[] args) {  
	        Scanner input = new Scanner(System.in);  
	        String s = input.nextLine();  
	        input.close();  
	        int length = s.length();  
	        int l = (int) Math.ceil(length / 4.0);  
	        String[] arrS = new String[l];  
	        System.out.println(arrS.length);
	        
	        for (int i = 0; i < arrS.length; i++) {  
	            if (i == arrS.length - 1) {  
	                arrS[arrS.length - 1 - i] = s.substring(0, s.length() - 4 * i);  
	            } else {  
	                arrS[arrS.length - 1 - i] = s.substring(s.length() - 4 * i - 4,  
	                        s.length() - 4 * i);
	            }  
	        }
	        System.out.println(arrS.length);
	      
	        getSound(arrS);  
	    }  
	  
	    public static void getSound(String[] arrS) {  
	        String[] num = new String[] { "ling", "yi", "er", "san", "si", "wu",  
	                "liu", "qi", "ba", "jiu" };  
	        String[] maxWei = new String[] { "", "wan", "yi" };  
	        String[] minWei = new String[] { "", "shi", "bai", "qian" };  
	        String aim = "";  
	        for (int i = 0; i < arrS.length; i++) {  
	            String[] tS = arrS[i].split("");  
	            int t = -1;  
	            for (int j = 1; j < tS.length; j++) {  
	                if (t == 0) {  
	                    if (Integer.parseInt(tS[j]) == 0) {  
	                    } else if ("shi".equals(minWei[tS.length - j - 1])  
	                            && Integer.parseInt(tS[j]) == 1) {  
	                        aim += (minWei[tS.length - j - 1] + " ");  
	                    } else {  
	                        aim += (num[Integer.parseInt(tS[j])] + " "  
	                                + minWei[tS.length - j - 1] + " ");  
	                    }  
	                    t = Integer.parseInt(tS[j]);  
	                } else {  
	                    if (Integer.parseInt(tS[j]) == 0) {  
	                        aim += (num[Integer.parseInt(tS[j])] + " ");  
	                    } else if ("qian".equals(minWei[tS.length - j - 1])  
	                            && Integer.parseInt(tS[j]) == 2) {  
	                        aim += ("liang" + " " + minWei[tS.length - j - 1] + " ");  
	                    } else {  
	                        aim += (num[Integer.parseInt(tS[j])] + " "  
	                                + minWei[tS.length - j - 1] + " ");  
	                    }  
	  
	                    t = Integer.parseInt(tS[j]);  
	                }  
	  
	            }  
	            aim += (maxWei[arrS.length - i - 1] + " ");  
	        }  
	        System.out.println(aim);  
	    }  
}
