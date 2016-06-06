package bin;

import javax.swing.*;
import java.awt.event.*;

public class Animation extends JPanel implements ActionListener
{
	public Animation() {
	}
  
  JFrame f = new JFrame();
  ImageIcon anima;
  int i = 0;
  JLabel holder = new JLabel();
  Timer clock = new Timer(40, this);
  
  public static void main (String[] args)
  {
    Animation test = new Animation();
    test.Window();
  }
  
  public void Window()
  {
    add(holder);
    clock.start();
    f.setSize(800, 800);
    f.getContentPane().add(this);
    f.setVisible(true);
  }
  
  @Override
  public void actionPerformed(ActionEvent e)
  {
    i++;
    anima = new ImageIcon(PrisonBreak.class.getResource("/img/prisoner" + i + ".png"));
    this.remove(holder);
    holder = new JLabel(anima);
    add(holder);
    if (i == 6 )
    {
      i = 0;
    }
    revalidate();
  }
}