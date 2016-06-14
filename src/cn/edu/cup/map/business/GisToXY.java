package cn.edu.cup.map.business;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class GisToXY {
		public static void main(String[] args) {
			CoordinateConversion conversion  = new CoordinateConversion();
			
				System.out.println(conversion.latLon2MGRUTM(39.54,116.23));
				System.out.println(conversion.latLon2UTM(39.54,116.23));
				double a[]=conversion.utm2LatLon("50 s 642176.8753 3946598.227");
				System.out.println(a[0]);
				System.out.println(a[1]);
				//BufferedReader  bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("F:\\Location_Trace.txt")));
				//OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream("D:\\rs.txt"));
				//String data = null;
				//String [] strings = null;
				/*
				while ((data = bufferedReader.readLine())!=null) {						
						strings = data.split("\\t");
						outputStreamWriter.write(strings[0]+"\t"+conversion.latLon2UTM(Double.valueOf(strings[1])
								, Double.valueOf(strings[2]))+"\t"+strings[3]+"\t"+strings[4]+"\t"+strings[5]);
						outputStreamWriter.write("\n");
						outputStreamWriter.flush();
				}
				bufferedReader.close();
				outputStreamWriter.close();
				*/
				System.out.println("success");
			
		}
	
}
