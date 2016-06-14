/*********************************************************************************
 * Assignment: Final Summative
 * Description: The Garbage Class contains all the needed values to 
 * 							paint and modify garbage can images.
 *
 * Main Author: Yuting L.
 * Date: 19/05/16 through 13/06/16
 * Course: ICS4U1
 *********************************************************************************/
package bin;

import javax.swing.ImageIcon;

public class Garbage
{
	// Which type and difficulty the garbage cans are at
	static int size = 0, difi = 0;

	// The X and Y values of the garbage cans
	int x = 0, y = 0;

	// The image of garbage
	ImageIcon garb = null;

	// Whether or not the garbage can has been hit
	boolean hit = false;

	/**
	 * The constructor used to prepare the image and position
	 * 
	 * @param i
	 *          - The type of garbage can to be used
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
	}// End getImage

	// Returns size
	public int getSize()
	{
		return size;
	}// End getSize

	// Returns x value
	public int getX()
	{
		return x;
	}// End getX

	// Returns y value
	public int getY()
	{
		return y;
	}// End getY

	// Returns height
	public int getHeight()
	{
		return y;
	}// End getHeight

	// Moves the x value
	public void move(int dist)
	{
		x -= dist;
	}// End move

	// Regenerates the garbage can
	public void reGen(int i)
	{
		hit = false;
		size = i;
		garb = new ImageIcon(PrisonBreak.class.getResource("/img/garbage" + i + ".png"));
		x = (int) (Math.random() * 500) + 900;
		y = 360;
	}// End reGen

	// The garbage can is knocked over
	public void hit()
	{
		garb = new ImageIcon(PrisonBreak.class.getResource("/img/knocked.png"));
		hit = true;
	}// End hit

	// Returns is hit
	public boolean getHit()
	{
		return hit;
	}// End getHit
}// End Garbage
