package com.troglodyte.engine;

import java.io.BufferedReader;
import java.io.FileReader;

public class ReadConfig {
	
	WriteToLog wl;
	
	// this will read the config files and apply them to the game
	public ReadConfig(GameContainer gc)
	{
		
		BufferedReader br;
		try 
		{
			// open and read file, change settings using gamecontainer setters
			br = new BufferedReader(new FileReader(".\\config\\player\\config.cfg"));
			
			String line = br.readLine();
			while (line != null)
			{
				line = br.readLine();
				
				// game settings
				if (line.startsWith("scale=")) 
				{
					gc.setScale( (float) GetArg(line) );
				}
				
				else if (line.startsWith("fps_max=")) 
				{
					gc.setMAXFPS( (int) (GetArg(line)) );
				}
				
				else if (line.startsWith("tps=")) 
				{
					gc.setTPS(  (1/(GetArg(line)))  );
				}
				
				else if (line.startsWith("fullscreen=")) 
				{
					gc.setFullscreen( (int) (GetArg(line)));
				}
				
				
				// read keybinds from config file and set keybinds
				
				
				
				
							
			}
			
			// close buffered reader and write status to log
			br.close();
			wl = new WriteToLog("Successfully imported player configuration", 0);
			
		}
		catch (Exception e) 
		{
			wl = new WriteToLog("[!] Warning: Config file may not have been read correctly", 0);
		}
		
		
	}
	
	// setter -> removes anything but the argument value (int) at the end
	public double GetArg(String convar) 
	{
		double temp = Double.valueOf(  convar.substring( convar.indexOf("=")+1, convar.length() )  );
		return temp;
	}
	
	
}
