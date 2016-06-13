package bin;

import javax.swing.ImageIcon;

public class Coffee
{
	int								x				= 0,
	                  y = 0;
	static ImageIcon	coffee	= new ImageIcon();
	boolean						eaten		= false,
	                  deployed = false;

	Coffee()
	{
		coffee = new ImageIcon(PrisonBreak.class.getResource("/img/coffee.png"));
	}

	public static ImageIcon getImage()
	{
		return coffee;
	}

	public boolean isDeployed()
	{
		return deployed;
	}

	public int getX()
	{
		return x;
	}

	public int getY()
	{
		return y;
	}

	public int getHeight()
	{
		return y;
	}

	public void move(int dist)
	{
		x -= dist;
	}

	public void deploy()
	{
		eaten = false;
		deployed = true;
		x = (int) (Math.random() * 100) + 1000;
		if ((int) (Math.random() * 6) >= 4)
			y = 250;
		else
			y = 380;
	}

	public void eaten()
	{
		eaten = true;
		deployed = false;
	}

	public boolean getEaten()
	{
		return eaten;
	}
}
