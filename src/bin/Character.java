package bin;

import javax.swing.ImageIcon;

public class Character {
	private int invSpeed = 500, accel = 0;
	
	ImageIcon[] prison = new ImageIcon[6];
	ImageIcon[] player = new ImageIcon[6];
	Character(){
		for(int i = 0; i<6; i++){
			prison[i] = new ImageIcon(PrisonBreak.class.getResource("/img/prisoner" + i + ".png"));
			player[i] = new ImageIcon(PrisonBreak.class.getResource("/img/cop" + i + ".png"));
		}
	}
}
