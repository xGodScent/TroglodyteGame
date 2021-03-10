package com.troglodyte.engine;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadConfig {
	
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
				
				
				// set keybinds
				
				
			}
			
			br.close();
			
		}
		catch (Exception e) 
		{
			System.out.println("\n// [!] Warning: Config file may not have been read correctly.\n\n" + LinesGoBrr(50) + "\n");
		}
		
		
	}
	
	// setter -> removes anything but the argument value (int) at the end
	public double GetArg(String convar) 
	{
		double temp = Double.valueOf(  convar.substring( convar.indexOf("=")+1, convar.length() )  );
		return temp;
	}
	
	
	// string go brr
	public String LinesGoBrr(int times)
	{
		
		String temp = "";
		
		for (int i = 0; i < times; i++) 
		{
			temp += "-";
		}
			
		return temp;
	}
	
	
}
