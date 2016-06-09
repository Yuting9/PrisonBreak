package bin;

import javax.swing.ImageIcon;

public class Character {
	private int spd = 200, vert = 0, x = 0, y = 338;
	private static int floor = 338;
	String mode = "run";
	ImageIcon[] imgs = new ImageIcon[6];
	ImageIcon[] jumps = new ImageIcon[2];
	ImageIcon[] rolls = new ImageIcon[6];
	boolean released = false;
	
	
	Character(boolean bool){
		for(int i = 0; i<6; i++){
			if(bool){
				imgs[i] = new ImageIcon(PrisonBreak.class.getResource("/img/prisoner" + i + ".png"));
				if(i<2)
					jumps[i] = new ImageIcon(PrisonBreak.class.getResource("/img/prisjump" + i + ".png"));
				if(i<4)
					rolls[i] = new ImageIcon(PrisonBreak.class.getResource("/img/prisroll" + i + ".png"));
			}
			else{
				System.out.println(i);
				imgs[i] = new ImageIcon(PrisonBreak.class.getResource("/img/cop" + i + ".png"));
				if(i<2)
					jumps[i] = new ImageIcon(PrisonBreak.class.getResource("/img/copjump" + i + ".png"));
				if(i<4)
					rolls[i] = new ImageIcon(PrisonBreak.class.getResource("/img/coproll" + i + ".png"));
			}
		}
	}
	
	public int getSpd(){
		return spd;
	}
	
	public int getVert(){
		return vert;
	}
	
	public int getHeight(){
		return y;
	}
	
	public ImageIcon getImage(int i){
		if(mode.equals("jump"))
			return jumps[i];
		else if(mode.equals("roll"))
			return rolls[i];
		else
			return imgs[i];
	}
	
	public String getMode(){
		return mode;
	}
	
	public int getX(){
		return x;
	}
	
	public void isReleased(){
		released = true;
	}
	
	public void setSpd(int set){
		spd = set;
	}
	
	public void setX(int set){
		x = set;
	}
	
	public void setVert(int set){
		vert = set;
	}

	public void jump(){
		if(!released){
			y-=vert;
			vert--;
			if(vert <= 3){
				released = true;
			}
		}
		else{
			System.out.println("falling");
			y-=vert;
			vert-=5;
		}
		if(y > floor){
			System.out.println("This is Ture");
			y = 338;
			vert = 0;
			mode = "roll";
			released = false;
		}
	}
	
	public void doRun(){
		mode = "run";
	}
	
	public void doJump(){
		mode = "jump";
	}
	
	public void doRoll(){
		mode = "roll";
	}
}
