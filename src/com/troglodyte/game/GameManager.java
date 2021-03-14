package com.troglodyte.game;

import com.troglodyte.engine.AbstractGame;
import com.troglodyte.engine.GameContainer;
import com.troglodyte.engine.Renderer;
import com.troglodyte.engine.gfx.LoadImage;

public class GameManager extends AbstractGame
{

	private LoadImage image;
	
	// ok
	public GameManager() 
	{
		image = new LoadImage(".\\resources\\scene\\test\\image.png");
	}
	
	public void render(GameContainer gc, Renderer r)
	{
//		r.drawImage(image, , offY);
		
		
	}
	
	public static void main(String[] args) 
	{
		GameContainer gc = new GameContainer(new GameManager());
		gc.start();
	}

	@Override
	public void update(GameContainer gc, float dt) 
	{

		
	}
	
	
}
