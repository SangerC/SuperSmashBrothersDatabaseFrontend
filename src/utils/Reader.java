package utils;

import java.awt.Image;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.ImageIcon;

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
	
	public static Image resizedImage(String location, int width, int height) {
		ImageIcon imageOrig = new ImageIcon(location);
		Image image = imageOrig.getImage();
		Image imageResized = image.getScaledInstance(width, height,  java.awt.Image.SCALE_SMOOTH);
		return imageResized;
	}
	
	
}
