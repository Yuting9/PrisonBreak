package bin;

import javax.swing.ImageIcon;

public class Donut {
  
  static ImageIcon[] donut = new ImageIcon[4];
  Donut(){
    for(int d = 0; d < 4; d++)
    {
      donut[d] = new ImageIcon(PrisonBreak.class.getResource("/img/donut" + d + ".png"));
    }
  }
  
  public static ImageIcon getImage(int d)
  {
    return donut[d];
  }
  
}
