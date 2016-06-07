package bin;

import javax.swing.*;
import java.awt.event.*;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Color;

public class TheRun extends JPanel implements ActionListener
{
 
 JFrame frame = new JFrame();
 ImageIcon prison, player, donut;
 int pX = 100, pY = 100;
  int i = 0, k = 0, d = 500, x = 700, t;
    JLabel holder = new JLabel();
    JLabel background = new JLabel();
    Timer clock = new Timer(40, this);
    ImageIcon []bg = new ImageIcon [50];
    ImageIcon test = new ImageIcon(PrisonBreak.class.getResource("/img/UrbanBuilding" + ((int)(Math.random()*2)+1) + ".png"));
    
 public TheRun() {
   player = Character.getPlayer(i);
   prison = Character.getPrison(i);
   donut = Donut.getDonut(k);
   
   for (int t = 0; t < 50; t++)
          {
            bg[t] = new ImageIcon(PrisonBreak.class.getResource("/img/UrbanBuilding" + ((int)(Math.random()*2)+1) + ".png"));
          }
   
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
          for (int p = 0; p < 50; p++)
          {
            bg[p].paintIcon(this, g, x + (p * 200), 100);
          }
          g.setColor(Color.darkGray);
          g.fillRect(0,450,900,30);
          donut.paintIcon(this, g, 100, 350);
          player.paintIcon(this, g, 50, 338);
          prison.paintIcon(this, g, 300, 338);
            repaint();
        }
        
    @Override
    public void actionPerformed(ActionEvent e)
    {
        clock.setDelay(d);
        if(d>=142)
         d-=(d/10);
        prison = Character.getPrison(i);
         //d-=(d/10);
        player = Character.getPlayer(i);
        donut = Donut.getDonut(k);
        this.remove(holder);
        
        k++;
        if (k == 4)
        {
            k = 0;
        }
        
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