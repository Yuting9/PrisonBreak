/*********************************************************************************
 * Assignment: Final Summative 
 * Description: The class containing information
 *        used to draw the Donuts.
 *
 * Main Author: Yuting L. 
 * Date: 19/05/16 through 13/06/16 
 * Course: ICS4U1
 *********************************************************************************/
package bin;

// Import ImageIcon
import javax.swing.ImageIcon;

// Coffee Class
public class Coffee
{
	// The X and Y values of Coffee
	int x = 0, y = 0;

	// The image of the coffee
	static ImageIcon coffee = null;

	// Whether or not the coffee has been deployed to the screen and/or consumed
	boolean eaten = false, deployed = false;

	// Default Constructor
	Coffee()
	{
		// Initializes the ImageIcon
		coffee = new ImageIcon(PrisonBreak.class.getResource("/img/coffee.png"));
	}// End Default Constructor

	// Returns the ImageIcon
	public static ImageIcon getImage()
	{
		return coffee;
	}// End getImage

	// Returns the deployment status
	public boolean isDeployed()
	{
		return deployed;
	}// End isDeployed

	// Returns the X value
	public int getX()
	{
		return x;
	}// End getX

	// Returns the Y value
	public int getY()
	{
		return y;
	}// End getY

	// Moves the X value
	public void move(int dist)
	{
		x -= dist;
	}// End move

	// Deploys the coffee
	public void deploy()
	{
		eaten = false;
		deployed = true;
		x = (int) (Math.random() * 100) + 1000;
		if ((int) (Math.random() * 6) >= 4)
			y = 250;
		else
			y = 380;
	}// End deploy

	// Eats the coffee
	public void eaten()
	{
		eaten = true;
		deployed = false;
	}// End eaten

	// Returns the eaten status of the coffee
	public boolean getEaten()
	{
		return eaten;
	}// End getEaten
}// End Coffee
