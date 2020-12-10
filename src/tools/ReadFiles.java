package tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ReadFiles {
	
	private static BufferedReader br;

	public static ArrayList<String> readFromTextFile(String pathname) throws IOException{
	    ArrayList<String> strArray = new ArrayList<String>();
	    File filename = new File(pathname);
	    InputStreamReader reader = new InputStreamReader(new FileInputStream(filename));
	    br = new BufferedReader(reader);
	    String line = "";
	    line = br.readLine();
	    while(line != null) {
	        strArray.add(line);
	        line = br.readLine();
	    }
	    return strArray;
	}
}
