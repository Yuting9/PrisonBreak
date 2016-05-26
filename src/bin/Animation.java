import javax.swing.*;
import java.awt.event.*;

public class Animation extends JPanel implements ActionListener
{
  
  JFrame f = new JFrame();
  ImageIcon anima;
  int i = 0;
  JLabel holder = new JLabel();
  Timer clock = new Timer(100, this);
  
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
    f.add(this);
    f.setVisible(true);
  }
  
  @Override
  public void actionPerformed(ActionEvent e)
  {
    i++;
    anima = new ImageIcon();
    this.remove(holder);
    holder = new JLabel(anima);
    add(holder);
    if (i == 6 /*number of images*/ )
    {
      i = 0;
    }
    revalidate();
  }
}