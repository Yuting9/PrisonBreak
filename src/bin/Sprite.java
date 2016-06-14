/*********************************************************************************
 * Assignment: Final Summative
 * Description: The class containing all the needed values for buildings
 *
 * Main Author: Yuting L.
 * Date: 19/05/16 through 13/06/16
 * Course: ICS4U1
 *********************************************************************************/
package bin;

import javax.swing.ImageIcon;

public class Sprite
{
	/********************
	 * Instance Variables
	 ********************/
	
	ImageIcon image = null;
	int x = 700, y = 0, i = 0;

	
	/********************
	 * Constructors
	 ********************/
	
	Sprite(int i)
	{
		x = i;
	}	
	
	/********************
	 * Get Methods
	 ********************/
	
	public ImageIcon getImage()
	{
		return image;
	}

	public int getX()
	{
		return x;
	}

	public int getY()
	{
		return y;
	}

	/*******************
	 * Set Methods
	 ******************/
	
	public void setX(int set)
	{
		x = set;
	}

	public void setY(int set)
	{
		y = set;
	}


	/*********************
	 * Instance Methods
	 *********************/
	
	
	public void move(int dist)
	{
		x -= dist;
	}

	public void reGen(boolean urb)
	{
		if (urb)
		{
			image = new ImageIcon(PrisonBreak.class
			.getResource("/img/UrbanBuilding" + (int) (Math.random() * 3) + ".png"));
		}
		else
		{
			image = new ImageIcon(
			PrisonBreak.class.getResource("/img/Rural" + (int) (Math.random() * 5) + ".png"));
		}
		x = 1000;
		y = 100;
	}
}
