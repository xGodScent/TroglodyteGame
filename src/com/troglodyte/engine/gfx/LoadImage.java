package com.troglodyte.engine.gfx;


import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class LoadImage 
{
	
	private int w, h;
	private int[] p;

	public LoadImage(String path) 
	{
		BufferedImage image = null;
		try 
		{
			image = ImageIO.read(LoadImage.class.getResourceAsStream(path));
			
			w = image.getWidth();
			h = image.getHeight();
			p = image.getRGB(0, 0, w, h, null, 0, w);
			
			image.flush();
			
		} catch (IOException e) { e.printStackTrace(); }
		
		
	}

	public int getW() {
		return w;
	}

	public void setW(int w) {
		this.w = w;
	}

	public int getH() {
		return h;
	}

	public void setH(int h) {
		this.h = h;
	}

	public int[] getP() {
		return p;
	}

	public void setP(int[] p) {
		this.p = p;
	}
	
}
