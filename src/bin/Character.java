package bin;

import javax.swing.ImageIcon;

public class Character {
	private int spd = 200;
	String mode = "run";
	ImageIcon[] imgs = new ImageIcon[6];
	ImageIcon[] jumps = new ImageIcon[2];
	ImageIcon[] rolls = new ImageIcon[6];
	
	
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
	
	public void setSpd(int set){
		spd = set;
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
