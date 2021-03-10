package com.troglodyte.engine;

import java.io.File;

public class CreateFile {
	
	WriteToLog eh;
	
	public CreateFile(String path, String filename) {
		
		try 
		{
			
			File file = new File(path+filename);
			file.createNewFile();
			
		} catch (Exception e) {
			e.printStackTrace();
			eh = new WriteToLog(String.valueOf(e), 1);
		}
		
	}
	
	
	
}
