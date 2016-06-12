package bin;

import javax.swing.ImageIcon;

public class Garbage
{
	static int				size	= 0;
	int								x			= 0,
	                  y 		= 0;
	ImageIcon	garb	= null;
	boolean						hit		= false;

	Garbage(int i)
	{
		garb = new ImageIcon(PrisonBreak.class.getResource("/img/garbage" + i + ".png"));
		size = i;
		x = (int) (Math.random() * 1000) + 800 * (i + 1);
		y = 360;
	}

	public ImageIcon getImage()
	{
		return garb;
	}

	public int getSize()
	{
		return size;
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

	public void reGen(int i)
	{
		hit = false;
		size = i;
		garb = new ImageIcon(PrisonBreak.class.getResource("/img/garbage" + i + ".png"));
		x = (int) (Math.random() * 500) + 900;
		y = 360;
	}

	public void hit()
	{
		hit = true;
	}

	public boolean getHit()
	{
		return hit;
	}
}
