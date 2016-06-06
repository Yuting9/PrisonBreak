package bin;

import javax.swing.*;
import java.awt.event.*;
import java.awt.Graphics;

public class TheRun extends JPanel implements ActionListener
{
 JFrame f = new JFrame();
 ImageIcon anima;
 int i = 0, d = 500, x = 300;
    JLabel holder = new JLabel();
    JLabel background = new JLabel();
    Timer clock = new Timer(40, this);
    ImageIcon test = new ImageIcon(PrisonBreak.class.getResource("/img/UrbanBuilding" + ((int)(Math.random()*2)+1) + ".png"));
    
    
    public void Window()
    {
     f.setResizable(false);
        add(holder);
        clock.start();
        f.setSize(600,450);
        f.getContentPane().add(this);
        f.setVisible(true);
       
        background = new JLabel(test);
        add(background);       
    }
    
        public void paintComponent(Graphics g)
        {
          x--;
          super.paintComponent(g);
          test.paintIcon(this,g,x,0);
          repaint();
        }
        
    @Override
    public void actionPerformed(ActionEvent e)
    {
        clock.setDelay(d);
        if(d>=42)
         d-=(d/10);
        anima = Character.getPrison(i);
        this.remove(holder);
        holder = new JLabel(anima);
        add(holder);
        i++;
        if (i == 6 )
        {
            i = 0;
        }
        revalidate();
    }
}