// package
package com.troglodyte.engine;

import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


// $GameContainer
// $GC
// GameContainer class : 
public class GameContainer implements Runnable {

	// creats thread object
	Thread thread;
	
	// window obj type shit
	private Window window;
	
	// renderer obj type shit
	private Renderer renderer;
	
	// reads config
	private ReadConfig readconfig;
	
	// creates WriteToLog object 
	private WriteToLog wl;
	
	private Input input;
	
	// ok
	private AbstractGame game;
	
	// sets game vars
	private boolean running = false;		// makes sure we're not running the game yet
	private double TPS = 1.0/60.0;	// ticks per second -> how often the game updates per second : update cap
	private int MAXFPS = 60;
	private String version = "0.0.2";
	private int fps;
	
	// window vars
	private int width = 640, height = 360;
	private float scale = 1F;
	private int fullscreen = 0;
	private String windowTitle = "Troglodyte v" + version;
	
	
	// log file setup
	CreateFile cf;
	final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy_HH-mm-ss");  
	final LocalDateTime now = LocalDateTime.now();  
	final String dt = dtf.format(now);
	
	
	// ok
	public GameContainer(AbstractGame game) 
	{
		this.game = game;
	}
	
	// $Start
	public void start() 
	{	
		// clear latest.log
		try {
			FileWriter fw = new FileWriter(".\\logs\\latest.log");
			fw.write("");
			fw.close();   
		} catch (IOException e) { e.printStackTrace(); }
		
		
		// lame ass shit
		readconfig = new ReadConfig(this);
		
		// real shit
		window = new Window(this);		// create window for THIS game
		renderer = new Renderer(this);	// create renderer for THIS game
		
		input = new Input(this);
		
		thread = new Thread(this);	// yk
		thread.run();				// calls:  public void run() 
		
		// log (wood?) -> first argument=msg - second argument=close or not -> 0|1 (if we close it, it will be saved in a crashreport)
		wl = new WriteToLog("Ran startup", 0);
		
	}	
	
	@SuppressWarnings("deprecation")
	public void stop()
	{
		try { copyLog(); } catch (Exception e) { e.printStackTrace(); }
		
		this.thread.stop();
		this.dispose();
	}
	

	private void copyLog() throws Exception
	{
		// copy latest.log file to other thingy		
		File latest_log = new File(".\\logs\\latest.log");
		File to_log = new File(".\\logs\\", "log__" + dt + "__.log");

		Files.copy(latest_log.toPath(), to_log.toPath());
	}
	
	// runs game
	public void run() 
	{
		// sets up vars for frame time etc.
		boolean render = false;		// we set rendering to false because we dont want to start rendering the game YET.
		double first = 0;
		double last = (System.nanoTime() / (1E+9) );
		double timePassed = 0;
		double unprocessedTime = 0;	// makes sure that if we skip frames or game freezes, that we will still update the game.
		
		double frameTime = 0;		// the time it took to render/execute the frame
		int frames = 0;				// total frames passed
		int FPS = 0;				// frames per second
		
		
		// lets game run
		running  = true;
		while (running) 
		{
			render = false;
						
			// setup for FPS counter & game clock -> TPS handler
			first = (System.nanoTime() / (1E+9) );	// gets current time
			timePassed = first - last;				// how long it has been since the last time we ran:  last = first;
			last = first;							// sets the last time to the first time
			
			unprocessedTime += timePassed;
			frameTime += timePassed;
			
			
			// handles missed updates
			while (unprocessedTime >= TPS) 
			{
				unprocessedTime -= TPS;
				render = true;
				
				
				game.update(this, (float) TPS);
				
				// ok
				if (input.isKey(KeyEvent.VK_B))
				{
					System.out.println("your mom is pressed lulz");
				}
				
				input.update();
				
				// fps counter
				if (frameTime >= 1.0) 
				{
					frameTime = 0;
					FPS = frames;
					frames = 0;
					
					System.out.println("FPS: " +  FPS);
				}
				
			}
			
			// $Render
			// if we are rendering the game we do:
			if (render) 
			{
				// lets game render
				renderer.clear();
				
				//
				game.render(this, renderer);
				
				// update window
				window.update();
				
				// frames int go brr
				frames++;
				
			} else {
				try 
				{
					Thread.sleep(1);	// frees up the CPU -> releases some processor usage
					
				} catch (InterruptedException e) { e.printStackTrace(); running = false; }
				
			} // else
				
			
		} // while running
		
		dispose();
		
	}
	
	private void dispose() 
	{
		renderer.clear();
		window.close();
		
		System.out.println("Closing game...");
		wl = new WriteToLog("Closed game", 0);
		
		System.exit(0);
	}

	
	// !--------------------------------------------------------------------!
	
	
	// getters and setters  ->  makes sure other classes can access these container variables

	public int getWidth() {
		return width;
	}


	public void setWidth(int width) {
		this.width = width;
	}


	public int getHeight() {
		return height;
	}


	public void setHeight(int height) {
		this.height = height;
	}


	public float getScale() {
		return scale;
	}


	public void setScale(float scale) {
		this.scale = scale;
	}


	public String getWindowTitle() {
		return windowTitle;
	}


	public void setWindowTitle(String windowTitle) {
		this.windowTitle = windowTitle;
	}


	public Window getWindow() {
		return window;
	}


	public double getTPS() {
		return TPS;
	}


	public void setTPS(double tPS) {
		TPS = tPS;
	}


	public int getFullscreen() {
		return fullscreen;
	}


	public void setFullscreen(int fullscreen) {
		this.fullscreen = fullscreen;
	}


	public int getMAXFPS() {
		return MAXFPS;
	}


	public void setMAXFPS(int mAXFPS) {
		MAXFPS = mAXFPS;
	}

	public String getDt() {
		return dtf.format(now);
	}

	public int getFps() {
		return fps;
	}

	public String getVersion() {
		return version;
	}

	public Input getInput() {
		return input;
	}
	
	
}
