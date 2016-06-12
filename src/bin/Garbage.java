package bin;

import javax.swing.ImageIcon;

public class Garbage
{
	static int					doTime	= 0;
	int									x = 0, 
											y = 0;
	static ImageIcon[]	donut	= new ImageIcon[] {null,null,null,null};
	boolean hit = false;

	Garbage(int i)
	{
		for (int doTime = 0; doTime < 4; doTime++)
		{
			donut[doTime] = new ImageIcon(PrisonBreak.class.getResource("/img/donut" + doTime + ".png"));
		}
		x = (int) (Math.random() * 1000) + 800*(i+1);
		if((int)(Math.random()*6) >= 4)
			y = 250;
		else
			y = 380;
	}

	public static ImageIcon getImage(int d)
	{
		return donut[d];
	}

	public static void advance()
	{
		doTime++;
		if (doTime >= 4)
		{
			doTime = 0;
		}
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
	
	public void reGen(){
		hit = false;
		x = (int) (Math.random() * 100) + 1000;
		y = (int) (Math.random() * 10) + 300;
	}
	
	public void hit(){
		hit = true;
	}
	
	public boolean gethit(){
		return hit;
	}
}
