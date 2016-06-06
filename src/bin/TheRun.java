package bin;

import javax.swing.*;
import java.awt.event.*;
<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> parent of 74d9a7f... Revert "Minor Edits to Formatting"
import java.awt.BorderLayout;

public class TheRun extends JPanel implements ActionListener
{
	
	JFrame frame = new JFrame();
	ImageIcon prison;
	int i = 0, d = 500;
	int pX = 100, pY = 100;
import java.awt.Graphics;

public class TheRun extends JPanel implements ActionListener
{
 JFrame f = new JFrame();
 ImageIcon anima;
 int i = 0, d = 500, x = 300;
    JLabel holder = new JLabel();
    JLabel background = new JLabel();
    Timer clock = new Timer(40, this);
	public TheRun() {
		frame.setResizable(false);
		frame.setSize(600,450);
		DrawPanel panel = new DrawPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		frame.setVisible(true);
		
		panel.add(prison, pX, pY);
	}
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
        prison = Character.getPrison(i);
         d-=(d/10);
        anima = Character.getPrison(i);
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
			// TODO Auto-generated method stub
			
		}
    	
    }
}