/*********************************************************************************
 * Assignment: Final Summative
 * Description: Character class contains all the variables to control and
 * 							manage the Player and Prisoner
 *
 * Main Author: Yuting L.
 * Date: 19/05/16 through 13/06/16
 * Course: ICS4U1
 *********************************************************************************/
package bin;

import javax.swing.ImageIcon;

public class Character // #define
{
	private int spd = 200, vert = 0, x = 100, y = 338;
	private static int floor = 338, spdDiff = 0;
	String mode = "run";
	ImageIcon[] imgs = new ImageIcon[6], jumps = new ImageIcon[2], rolls = new ImageIcon[6];
	boolean released = false;

	/**
	 * Default Character Constructor
	 */
	Character(boolean bool)
	{
		for (int i = 0; i < 6; i++)
		{
			if (bool)
			{
				imgs[i] = new ImageIcon(PrisonBreak.class.getResource("/img/prisoner" + i + ".png"));
				if (i < 2)
					jumps[i] = new ImageIcon(
					PrisonBreak.class.getResource("/img/prisjump" + i + ".png"));
				if (i < 4)
					rolls[i] = new ImageIcon(
					PrisonBreak.class.getResource("/img/prisroll" + i + ".png"));
			}
			else
			{
				imgs[i] = new ImageIcon(PrisonBreak.class.getResource("/img/cop" + i + ".png"));
				if (i < 2)
					jumps[i] = new ImageIcon(PrisonBreak.class.getResource("/img/copjump" + i + ".png"));
				if (i < 4)
					rolls[i] = new ImageIcon(PrisonBreak.class.getResource("/img/coproll" + i + ".png"));
			}// End if
		}// End loop
	}// End default constructor

	// Returns the speed
	public int getSpd()
	{
		return spd;
	}// End getSpd

	// Returns
	public int getVert()
	{
		return vert;
	}// End getVert

	// Returns y
	public int getY()
	{
		return y;
	}// End getY

	// Returns the speed difference
	public static int getDiff()// #static
	{
		return spdDiff;
	}// End getDiff

	// Returns the image
	public ImageIcon getImage(int i)// #instance
	{
		if (mode.equals("jump"))
			return jumps[i];
		else if (mode.equals("roll"))
			return rolls[i];
		else
			return imgs[i];
	}// End getImage

	public String getMode()
	{
		return mode;
	}// End getMode

	// Returns x
	public int getX()
	{
		return x;
	}// End getX

	public void isReleased()
	{
		released = true;
	}// End isReleased

	public void isNotReleased()
	{
		released = false;
	}// End isNotReleased

	// Sets the speed
	public void setSpd(int set)
	{
		spd = set;
	}// End setSpd

	// Sets x
	public void setX(int set)
	{
		x = set;
	}// End setX

	public void setVert(int set)
	{
		vert = set;
	}// End setVert

	public static void addDiff(int set)
	{
		spdDiff += set;
	}// End addDiff

	public static void setDiff(int set)
	{
		spdDiff = set;
	}// End setDiff

	public void jump()
	{
		y -= vert;
		if (!released && mode.equals("jump"))
		{
			vert -= 3;
			if (vert <= 3)
			{
				released = true;
			}// End if
		}
		else
		{
			vert -= 5;
		}// End if
		if (y > floor)
		{
			y = 338;
			vert = 0;
			mode = "roll";
			released = false;
		}// End if
	}// End jump

	public void doRun()
	{
		mode = "run";
		released = false;
	}// End doRun

	public void doJump()
	{
		mode = "jump";
	}// End doJump

	public void doRoll()
	{
		mode = "roll";
		released = false;
	}// End doRoll
}// End Character
