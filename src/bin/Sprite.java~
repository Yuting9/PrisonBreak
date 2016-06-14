package bin;

import javax.swing.ImageIcon;

public class Sprite
{
	ImageIcon image = null;
	int x = 700, y = 0, i = 0;
	
	Sprite(int i){
		x = i;
	}
	
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
	
	public void setX(int set)
	{
		x = set;
	}
	
	public void setY(int set)
	{
		y = set;
	}
	
	public void move(int dist){
		x-=dist;
	}
	
	public void reGen(boolean urb)
	{
		if(urb)
		{
			image = new ImageIcon(PrisonBreak.class.getResource(
				"/img/UrbanBuilding" + (int) (Math.random() * 3) + ".png")
			);
		}
		else
		{
			image = new ImageIcon(PrisonBreak.class.getResource(
				"/img/Rural" + (int) (Math.random() * 5) + ".png")
			);
		}
		x = 1000;
		y = 100;
	}
}
