package bin;

import javax.swing.ImageIcon;

public class Donut {
  static int doTime = 0, x = 0, y = 0;
  static ImageIcon[] donut = new ImageIcon[4];
  Donut()
  {
    for(int i = 0; i < 4; i++)
    {
      donut[i] = new ImageIcon(PrisonBreak.class.getResource("/img/donut" + i + ".png"));
    }
  }
  
  public static ImageIcon getImage(int d)
  {
    return donut[d];
  }
  
  public static void advance()
  {
   doTime++;
   if(doTime >= 4)
   {
    doTime = 0;
   }
  }
  
  public int getX()
 {
  return x;
 }
  
  public int getHeight()
 {
  return y;
 }
}
