package com.troglodyte.engine;

import java.awt.Font;
import java.awt.image.DataBufferInt;
import java.util.Random;

// $Renderer
// game rendered type shit
public class Renderer {
	
	// some vars bbygurl
	private int[] p;	// pixels
	private int pW, pH;	// pixel width, height
	
	private Random random = new Random();	
	private WriteToLog wl;
	
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
	
	public void setPixel(int x, int y, int value) 
	{
		if ((x<0 || x>=pW || y<0 || y>=pH) || value == 0xFFFF00FF) 
			return;
		
		p[x + y * pW] = value;
		
	}
	
	public void drawText(String text, int offX, int offY, int color)
	{
		text = text.toUpperCase();
		int offset = 0;
		
		for (int i = 0; i < text.length(); i++)
		{
			int unicode = text.codePointAt(i);
			
//			for (int y = 0; y < )
//			{
//				
//			}
			
		}
		
	}
	
	
	
	// it uh, clears the screen *thumbs up*
	public void clear() 
	{
		
		// draw gay ass background type shit
		for(int i = 0; i < p.length; i++) 
		{
			p[i] = 50;
		}
		
		
		
	}
	
	
}
