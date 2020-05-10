package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Reader {

	public static String getAttribute(String attribute){
		BufferedReader reader;
		
		try {
			reader = new BufferedReader(new FileReader("supersmash.properties"));
			String line = reader.readLine();
			while(line!=null){
				if(line.contains(attribute)) {
					String[] str =  line.split("=");
					reader.close();
					return str[1];
				}
				line=reader.readLine();
			}
			reader.close();

		} catch(IOException e){
			e.printStackTrace();
		}
		return "could not find attribute or file";
	} 
	
	
	
}
