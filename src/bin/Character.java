package bin;

import javax.swing.ImageIcon;

public class Character {
 private int invSpeed = 500, accel = 0;
 boolean isPlayer = true;
 
 static ImageIcon[] prison = new ImageIcon[6];
 static ImageIcon[] player = new ImageIcon[6];
 Character(){
  for(int i = 1; i<7; i++){
   prison[i-1] = new ImageIcon(PrisonBreak.class.getResource("/img/prisoner" + i + ".png"));
   player[i-1] = new ImageIcon(PrisonBreak.class.getResource("/img/cop" + i + ".png"));
  }
 }
 
 public static ImageIcon getPrison(int i){
  return prison[i];
 }
 public static ImageIcon getPlayer(int i){
  return player[i];
 }
 
}
