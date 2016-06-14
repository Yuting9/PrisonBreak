/*********************************************************************************
 * Assignment: Final Summative 
 * Description: The class containing information
 * 							used to draw the Donuts.
 *
 * MainAuthor: Yuting L. 
 * Date: 19/05/16 through 13/06/16 
 * Course: ICS4U1
 *********************************************************************************/

package bin;

// Import ImageIcon
import javax.swing.ImageIcon;

// Donut Class
public class Donut
{
	// The X and Y values for the donut
	int x = 0, y = 0;

	// The image for the donut
	static ImageIcon donut = null;

	// Whether or not the donut has been eaten
	boolean eaten = false;

	// Default Constructor
	Donut(int i)
	{
		// Sets the imageIcon
		donut = new ImageIcon(PrisonBreak.class.getResource("/img/donut0.png"));

		// Sets the X value to a random value
		x = (int) (Math.random() * 1000) + 800 * (i + 1);

		// Sets the Y value to either "jump height" and "walk height" randomly
		if ((int) (Math.random() * 6) >= 4)
			y = 250;
		else
			y = 380;

	}// End Default Constructor

	// Gets the ImageIcon
	public static ImageIcon getImage()
	{
		return donut;
	}// End getImage

	// Gets the X value
	public int getX()
	{
		return x;
	}// End getX

	// Gets the Y value
	public int getY()
	{
		return y;
	}// End getY

	// Moves the donut's X value
	public void move(int dist)
	{
		x -= dist;
	}// End move

	// Regenerates the donut
	public void reGen()
	{
		eaten = false;
		x = (int) (Math.random() * 100) + 1000;
		if ((int) (Math.random() * 6) >= 4)
			y = 250;
		else
			y = 380;
	}// End reGen

	// Sets the donut to eaten
	public void eaten()
	{
		eaten = true;
	}// End eaten

	// Returns eaten
	public boolean getEaten()
	{
		return eaten;
	}// End getEaten

}// End Donut
