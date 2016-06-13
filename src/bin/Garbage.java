package bin;

import javax.swing.ImageIcon;

public class Garbage
{
	// Which type and difficulty the garbage cans are at
	static int	size	= 0,
	            difi 	= 0;
	
	// The X and Y values of the garbage cans
	int					x			= 0,
	            y 		= 0;
	
	// The image of garbage
	ImageIcon		garb	= null;
	
	// Whether or not the garbage can has been hit
	boolean			hit		= false;

	/**
	 * The constructor used to prepare the image and position
	 * 
	 * @param i - The type of garbage can to be used
	 */
	Garbage(int i)
	{
		// Sets the image
		garb = new ImageIcon(PrisonBreak.class.getResource("/img/garbage" + i + ".png"));
		
		// Sets the size
		size = i;
		
		// Sets the location of the garbage can
		x = (int) (Math.random() * 1000) + 800 * (i + 1);
		y = 360;
	}// End Constructor
	
	// Returns the Image
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
		garb = new ImageIcon(PrisonBreak.class.getResource("/img/knocked.png"));
		hit = true;
	}

	public boolean getHit()
	{
		return hit;
	}
}
