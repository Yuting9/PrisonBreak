/*********************************************************************************
 * Assignment: Final Summative
 * Description: A sidescroller where you play as a police officer chasing down
 * 				a runaway prisoner. You win if you catch the prisoner, and you
 * 				lose if the prisoner gets too far away. Donuts give you points
 * 				and coffee speeds you up. You also get a "Skill" bonus to your
 * 				points if you catch the prisoner quickly or if you avoid a lot
 * 				of obstacles.
 *
 * Author: Yuting L.
 * Date: 19/05/16 through 13/06/16
 * Course: ICS4U1
 *********************************************************************************/
package bin;

import javax.swing.ImageIcon;

public class Character
{
	private int					spd				= 200,
	                    vert 			= 0, 
	                    x 				= 100, 
	                    y 				= 338;
	private static int	floor			= 338,
	                    spdDiff 	= 0;
	String							mode			= "run";
	ImageIcon[]					imgs			= new ImageIcon[6],
											jumps			= new ImageIcon[2],
											rolls			= new ImageIcon[6];
	boolean							released	= false;

	Character(boolean bool)
	{
		for (int i = 0; i < 6; i++)
		{
			if (bool)
			{
				imgs[i] = new ImageIcon(PrisonBreak.class.getResource("/img/prisoner" + i + ".png"));
				if (i < 2)
					jumps[i] = new ImageIcon(PrisonBreak.class.getResource("/img/prisjump" + i + ".png"));
				if (i < 4)
					rolls[i] = new ImageIcon(PrisonBreak.class.getResource("/img/prisroll" + i + ".png"));
			} else
			{
				System.out.println(i);
				imgs[i] = new ImageIcon(PrisonBreak.class.getResource("/img/cop" + i + ".png"));
				if (i < 2)
					jumps[i] = new ImageIcon(PrisonBreak.class.getResource("/img/copjump" + i + ".png"));
				if (i < 4)
					rolls[i] = new ImageIcon(PrisonBreak.class.getResource("/img/coproll" + i + ".png"));
			}
		}
	}

	public int getSpd()
	{
		return spd;
	}

	public int getVert()
	{
		return vert;
	}

	public int getY()
	{
		return y;
	}

	public static int getDiff()
	{
		return spdDiff;
	}

	public ImageIcon getImage(int i)
	{
		if (mode.equals("jump"))
			return jumps[i];
		else if (mode.equals("roll"))
			return rolls[i];
		else
			return imgs[i];
	}

	public String getMode()
	{
		return mode;
	}

	public int getX()
	{
		return x;
	}

	public void isReleased()
	{
		released = true;
	}

	public void isNotReleased()
	{
		released = false;
	}

	public void setSpd(int set)
	{
		spd = set;
	}

	public void setX(int set)
	{
		x = set;
	}

	public void setVert(int set)
	{
		vert = set;
	}

	public static void addDiff(int set)
	{
		spdDiff += set;
	}

	public static void setDiff(int set)
	{
		spdDiff = set;
	}
	
	public void jump()
	{
		y -= vert;
		if (!released && mode.equals("jump"))
		{
			vert -= 3;
			if (vert <= 3)
			{
				released = true;
			}
		} else
		{
			vert -= 5;
		}
		if (y > floor)
		{
			System.out.println("This is Ture");
			y = 338;
			vert = 0;
			mode = "roll";
			released = false;
		}
	}

	public void doRun()
	{
		mode = "run";
		released = false;
	}

	public void doJump()
	{
		mode = "jump";
	}

	public void doRoll()
	{
		mode = "roll";
		released = false;
	}
}
