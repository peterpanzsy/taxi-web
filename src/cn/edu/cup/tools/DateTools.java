package cn.edu.cup.tools;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTools {
	public static String getStringFromDate(Date temp){
		
		 DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		  try {
			  String string2=dateFormat.format(temp);
			  System.out.println(string2);
			  return string2;
		} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
		
		  return "";
	}
}
