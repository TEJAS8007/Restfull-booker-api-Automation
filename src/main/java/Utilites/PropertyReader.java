package Utilites;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {

	static Properties pro;
	static FileInputStream file;
	
	public static Properties init_prop() {
		
		try {
			
			file = new FileInputStream("src/main/resources/api-Config/api.properties");
			
			pro = new Properties();
			pro.load(file);
			
		} 
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return pro;
	}
}
