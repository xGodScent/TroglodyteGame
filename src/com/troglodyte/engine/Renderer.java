package com.troglodyte.engine;

import java.awt.image.DataBufferInt;

// $Renderer
// game rendered type shit
public class Renderer {
	
	// some vars bbygurl
	private int[] p;	// pixels
	private int pW, pH;	// pixel width, height
	
	WriteToLog wl;
	
	// ok
	public Renderer(GameContainer gc) 
	{
		wl = new WriteToLog("Started renderer", 0);
		
		// get this bish
		pW = gc.getWidth();
		pH = gc.getHeight();
		
		// gives p array access to pixel data from image in window class:
		p = ( (DataBufferInt) gc.getWindow().getImage().getRaster().getDataBuffer() ).getData();
		
	}
	
	
	// it uh, clears the screen *thumbs up*
	public void clear() 
	{
		for(int i = 0; i < p.length; i++) {
//			p[i] = 0xFF000000;
			p[i] += i;
		}
		
	}
	
	
}
