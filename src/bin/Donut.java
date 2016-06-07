package bin;

import javax.swing.ImageIcon;

public class Donut {
 private int invSpeed = 500, accel = 0;
 
 static ImageIcon[] donut = new ImageIcon[4];
 Donut(){
  for(int k = 1; k<5; k++){
   donut[k-1] = new ImageIcon(PrisonBreak.class.getResource("/img/donut" + k + ".png"));
  }
 }
 
 public static ImageIcon getDonut(int k){
  return donut[k];
 }
 
}
