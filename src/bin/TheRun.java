package bin;

import javax.swing.*;
import java.awt.event.*;
import java.awt.BorderLayout;
import java.awt.Graphics;

public class TheRun extends JPanel implements ActionListener
{
 
 JFrame frame = new JFrame();
 ImageIcon prison, player;
 int pX = 100, pY = 100;
  int i = 0, d = 500, x = 700;
    JLabel holder = new JLabel();
    JLabel background = new JLabel();
    Timer clock = new Timer(40, this);
    //int []bg = new int [10];
    ImageIcon test = new ImageIcon(PrisonBreak.class.getResource("/img/UrbanBuilding" + ((int)(Math.random()*2)+1) + ".png"));
    
 public TheRun() {
  frame.setResizable(false);
  frame.setSize(900,500);
  DrawPanel panel = new DrawPanel();
  frame.getContentPane().add(panel, BorderLayout.CENTER);
  frame.setVisible(true);
  
  panel.add(prison, pX, pY);
 }
    
    
    public void Window()
    {
        //add(holder);
        clock.start();
        frame.getContentPane().add(this);
        frame.setVisible(true);
        //background = new JLabel();
        //add(background);       
    }
    
        public void paintComponent(Graphics g)
        {
          x--;
          super.paintComponent(g);
          test.paintIcon(this, g, x, 100);
          test.paintIcon(this,g,x+600,100);
          repaint();
        }
        
    @Override
    public void actionPerformed(ActionEvent e)
    {
        clock.setDelay(d);
        if(d>=42)
         d-=(d/10);
        prison = Character.getPrison(i);
         d-=(d/10);
        player = Character.getPlayer(i);
        this.remove(holder);
        holder = new JLabel(prison);
        add(holder);
        i++;
        if (i == 6 )
        {
            i = 0;
        }
        revalidate();
    }
    
    class DrawPanel extends JPanel{

  public void add(ImageIcon img, int x, int y) {
      
   
  }
     
    }
}