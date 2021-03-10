package com.troglodyte.engine;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class Input implements KeyListener, MouseListener, MouseMotionListener, MouseWheelListener 
{

	// setup vars type shit
	private GameContainer gc;
	
	// on press it'll make the value true
	private final int NUM_OF_KEYS = 256;		// 256 for standard keyboard (amount of keys)
	private boolean[] keys = new boolean[NUM_OF_KEYS];
	private boolean[] keysLast = new boolean[NUM_OF_KEYS];

	private final int NUM_OF_BUTTONS = 5;		// mouse keys
	private boolean[] buttons = new boolean[NUM_OF_BUTTONS];	
	private boolean[] buttonsLast = new boolean[NUM_OF_BUTTONS];	
	
	private int mouseX, mouseY;
	private int scroll;
	
	
	public Input(GameContainer gc) 
	{
		this.gc = gc;
		
		mouseX = 0;
		mouseY = 0;
		scroll = 0;
		
		// lets window listen for any input
		gc.getWindow().getCanvas().addKeyListener(this);
		gc.getWindow().getCanvas().addMouseListener(this);
		gc.getWindow().getCanvas().addMouseMotionListener(this);
		gc.getWindow().getCanvas().addMouseWheelListener(this);
		
		
	}
	
	
	// update loop for input
	public void update() 
	{
		for (int i = 0; i < NUM_OF_KEYS; i++) 
		{
			keysLast[i] = keys[i];
			
			
		}
		
		for (int i = 0; i < NUM_OF_BUTTONS; i++) 
		{
			buttonsLast[i] = buttons[i];
			
			
		}
		
			
	}
	

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
		
	}

	@Override
	public void mousePressed(MouseEvent e) 
	{
	
		buttons[e.getButton()] = true;
		
	}

	@Override
	public void mouseReleased(MouseEvent e) 
	{
	
		buttons[e.getButton()] = false;
		
	}

	@Override
	public void keyPressed(KeyEvent e) 
	{
		
		keys[e.getKeyCode()] = true;
		
	}

	@Override
	public void keyReleased(KeyEvent e) 
	{
		
		keys[e.getKeyCode()] = false;
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
		
	}
	
}
