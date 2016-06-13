/*********************************************************************************
 * Assignment: Final Summative Description: The class containing information
 * used to draw the donuts
 *
 * Author: Yuting L. Date: 19/05/16 through 13/06/16 Course: ICS4U1
 *********************************************************************************/

package bin;

import javax.swing.ImageIcon;

public class Donut
{
	int								x			= 0,
	                  y = 0;
	static ImageIcon	donut	= null;
	boolean						eaten	= false;

	Donut(int i)
	{
		donut = new ImageIcon(PrisonBreak.class.getResource("/img/donut0.png"));
		x = (int) (Math.random() * 1000) + 800 * (i + 1);
		if ((int) (Math.random() * 6) >= 4)
			y = 250;
		else
			y = 380;
	}

	public static ImageIcon getImage()
	{
		return donut;
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

	public void reGen()
	{
		eaten = false;
		x = (int) (Math.random() * 100) + 1000;
		if ((int) (Math.random() * 6) >= 4)
			y = 250;
		else
			y = 380;
	}

	public void eaten()
	{
		eaten = true;
	}

	public boolean getEaten()
	{
		return eaten;
	}
}
