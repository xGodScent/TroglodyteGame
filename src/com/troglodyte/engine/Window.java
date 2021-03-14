// package
package com.troglodyte.engine;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;


// $Window
// window class -> creates window for the game
public class Window {

	// window objects
	private JFrame frame;			// frame
	private BufferedImage image;	// pixel data for screen
	private BufferStrategy bstrat;	// memory organizing type shit (no, idk.)
	private Canvas canvas;			// engine will draw on this
	private Graphics g;				// graphics object
	
	private WriteToLog wl;
	private JLabel fps_counter = new JLabel("FPS: ");
	
	private GameContainer gc;
	private Dimension windowSize;
	
	// TODO: add fps counter
	public Window(GameContainer gc) 
	{
		// create jframe window
		frame = new JFrame(gc.getWindowTitle());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.setUndecorated(true);
		frame.setAutoRequestFocus(true);
		
		// chnage window to full screen if read in config file
		if (gc.getFullscreen() == 1) {
	
			windowSize = Toolkit.getDefaultToolkit().getScreenSize();
			
			frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
			
			gc.setWidth((int) windowSize.getWidth());
			gc.setHeight((int) windowSize.getHeight());
			gc.setScale((float) 1.0F);
			
		} else { windowSize = new Dimension( (int) (gc.getWidth() * gc.getScale()), (int) (gc.getHeight() * gc.getScale()) ); }
		
		// create canvas
		image = new BufferedImage(gc.getWidth(), gc.getHeight(), BufferedImage.TYPE_INT_RGB);	// creates buffered image + sets type
		canvas = new Canvas();
		
		canvas.setMinimumSize(windowSize);
		canvas.setMaximumSize(windowSize);
		canvas.setPreferredSize(windowSize);
		
		
		// create exit button
		int btnW = (int) (40*gc.getScale());
		int btnH = (int) (20*gc.getScale());
		
		JButton exit_button = new JButton("X");
		exit_button.setBounds(
			((int) ((gc.getWidth()*gc.getScale())-btnW)), 
			0, 
			btnW,
			btnH
		);
		exit_button.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		
		exit_button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gc.stop();
			}});
		
		// create change window size button
		JButton cw_button = new JButton("□");
		cw_button.setBounds(
			((int) ((gc.getWidth()*gc.getScale())-(btnW*2))), 
			0, 
			btnW,
			btnH
		);
		cw_button.setFont(new Font("Tahoma", Font.PLAIN, 10));
		
		cw_button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if (gc.getFullscreen() == 1) {
					
					ReadConfig rc = new ReadConfig(gc);			
					gc.setWidth((int) (gc.getWidth()*gc.getScale()));
					gc.setHeight((int) (gc.getHeight()*gc.getScale()));
					
					frame.setSize(gc.getWidth(), gc.getHeight());
					frame.setLocationRelativeTo(null);
					
					// update buttons
					exit_button.setBounds(
						(int) (gc.getWidth()-btnW), 
						0, 
						btnW,
						btnH
					);
					
					cw_button.setBounds(
						(int) (gc.getWidth()-(btnW*2)), 
						0, 
						btnW,
						btnH
					);
					
					gc.setFullscreen(0);
					wl = new WriteToLog("Set screensize to windowed", 0);
					
				} else {
					
					frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
					
					gc.setWidth((int) windowSize.getWidth());
					gc.setHeight((int) windowSize.getHeight());
					gc.setScale((float) 1.0F);
					
					gc.setFullscreen(1);
					
					exit_button.setBounds(
						(int) (gc.getWidth()-btnW), 
						0, 
						btnW,
						btnH
					);
					
					cw_button.setBounds(
						(int) (gc.getWidth()-(btnW*2)), 
						0, 
						btnW,
						btnH
					);
					
					wl = new WriteToLog("Set screensize to fullscreen", 0);
					
				}
				
			}});
		
		
		// add buttons to frame
		frame.add(exit_button);
		frame.add(cw_button);
		
		// set game icon
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(".\\resources\\icons\\game_icon.png"));
		
		// add canvas to jframe window
		frame.add(canvas, BorderLayout.CENTER);
		frame.pack();
		
		// window settings
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		
		// final stuff
		canvas.createBufferStrategy(2);			// 2 buffers to render to
		bstrat = canvas.getBufferStrategy();	// bstrat type shit
		g = bstrat.getDrawGraphics();			// get graphics we need to draw
		
		wl = new WriteToLog("Created game window", 0);
	}
	
	// updates window
	public void update() 
	{
			
		// if we are not in the game, we pause -> maybe we should call the pause/menu ?
		while (!frame.isActive()) {
			try { Thread.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
		}
		
		g.drawImage(image, 0, 0, canvas.getWidth(), canvas.getHeight(), null);	// draw that mf canvas through graphics obj
		bstrat.show();															// show that mf using the buffered strategy
		
	}
	
	public void close() {
		frame.dispose();
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
