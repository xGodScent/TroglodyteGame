// package
package com.troglodyte.engine;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;


// $Window
// window class -> creates window for the game
public class Window {

	// window objects
	private JFrame frame;			// frame
	private BufferedImage image;	// pixel data for screen
	private BufferStrategy bstrat;	// memory organizing type shit (no, idk.)
	private Canvas canvas;			// engine will draw on this
	private Graphics g;				// graphics object
	
	
	// ok
	public Window(GameContainer gc) 
	{
		// create jframe window
		frame = new JFrame(gc.getWindowTitle());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		
		// create canvas
		image = new BufferedImage(gc.getWidth(), gc.getHeight(), BufferedImage.TYPE_INT_RGB);	// creates buffered image + sets type
		canvas = new Canvas();
		Dimension windowSize = new Dimension( (int) (gc.getWidth() * gc.getScale()), (int) (gc.getHeight() * gc.getScale()) );
		
		canvas.setMinimumSize(windowSize);
		canvas.setMaximumSize(windowSize);
		canvas.setPreferredSize(windowSize);
		
		
		// add canvas to jframe window
		frame.add(canvas, BorderLayout.CENTER);
		frame.pack();
		
		// window settings
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		
		canvas.createBufferStrategy(2);			// 2 buffers to render to
		bstrat = canvas.getBufferStrategy();	// bstrat type shit
		g = bstrat.getDrawGraphics();			// get graphics we need to draw
			
	}
	
	// updates window
	public void update() 
	{
		g.drawImage(image, 0, 0, canvas.getWidth(), canvas.getHeight(), null);	// draw that mf canvas through graphics obj
		bstrat.show();															// show that mf using the buffered strategy
		
		
		
	}
	
	
	// !--------------------------------------------------------------------!
	
	// getters

	public BufferedImage getImage() {
		return image;
	}

	public Canvas getCanvas() {
		return canvas;
	}
	
	
	
}
