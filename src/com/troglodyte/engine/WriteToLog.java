// package
package com.troglodyte.engine;

// libs type shit
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;
import java.io.FileWriter;


// handles errors and stores them in the correct files
public class WriteToLog {
	
	GameContainer gc = new GameContainer();
	CreateFile cf;
	
	public WriteToLog(String msg, int close_game) {
		
		// close_game = 0 -> Doesnt close game	| writes to log
		// close_game = 0 -> Closes game		| writes to crash-report
		
		try {
			
			// setup type shit
			String filename;
			String path;
			String FinalMSG;
			
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy_HH-mm-ss");  
			LocalDateTime now = LocalDateTime.now();  
			String dt = dtf.format(now);  
			
			
			// out
			FinalMSG = "~" + dt + " [ (!) $Engine ] : " + msg + ";";
			
			if (close_game == 0) // -> we store msg in log
			{
				path = ".\\logs\\log__" + gc.dt + "__.log";
				
				FileWriter fw = new FileWriter(path, true);
				fw.write(FinalMSG+"\n");
				fw.close();
				
			}
			
			else if (close_game == 1)  // -> we store error in crash report and at the end of the log
			{
				
				filename = "crash-report__" + dt + "__.log";
				path = ".\\crash-reports\\";
				
				try 
				{
					// create crash report file
					cf = new CreateFile(path, filename);
					
					// write error to log
					FileWriter fw = new FileWriter(path + filename, true);
					fw.write(FinalMSG+"\n");
					fw.close();
					
					// close game
					gc = new GameContainer();
					gc.stop();
					
					System.exit(0);
					
				} 
				catch (Exception ioex) {
					ioex.printStackTrace();
					System.exit(1);
				}
				
				
			}
			
			System.out.println(FinalMSG);
			
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		
	}

}
