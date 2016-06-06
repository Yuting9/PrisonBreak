package bin;

import javax.swing.*;
import java.awt.event.*;
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
import java.awt.BorderLayout;
=======
>>>>>>> parent of 2b83bec... Minor Edits to Formatting
=======
>>>>>>> parent of 2b83bec... Minor Edits to Formatting

public class TheRun extends JPanel implements ActionListener
{
	JFrame f = new JFrame();
	ImageIcon anima;
	int i = 0, d = 500;
<<<<<<< HEAD
<<<<<<< HEAD
	int pX = 100, pY = 100;
=======
import java.awt.Graphics;

public class TheRun extends JPanel implements ActionListener
{
 JFrame f = new JFrame();
 ImageIcon anima;
 int i = 0, d = 500, x = 300;
=======
>>>>>>> parent of 2b83bec... Minor Edits to Formatting
=======
>>>>>>> parent of 2b83bec... Minor Edits to Formatting
    JLabel holder = new JLabel();
    JLabel background = new JLabel();
    Timer clock = new Timer(40, this);
<<<<<<< HEAD
<<<<<<< HEAD
	public TheRun() {
		frame.setResizable(false);
		frame.setSize(600,450);
		DrawPanel panel = new DrawPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		frame.setVisible(true);
		
		panel.add(prison, pX, pY);
	}
    ImageIcon test = new ImageIcon(PrisonBreak.class.getResource("/img/UrbanBuilding" + ((int)(Math.random()*2)+1) + ".png"));
=======
    
    public void Window()
    {
    	f.setResizable(false);
        add(holder);
        clock.start();
        f.setSize(600,450);
        f.getContentPane().add(this);
        f.setVisible(true);
    }
>>>>>>> parent of 2b83bec... Minor Edits to Formatting
    
    
    public void Window()
    {
     f.setResizable(false);
=======
    
    public void Window()
    {
    	f.setResizable(false);
>>>>>>> parent of 2b83bec... Minor Edits to Formatting
        add(holder);
        clock.start();
        f.setSize(600,450);
        f.getContentPane().add(this);
        f.setVisible(true);
<<<<<<< HEAD
       
        background = new JLabel(test);
        add(background);       
=======
>>>>>>> parent of 2b83bec... Minor Edits to Formatting
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
<<<<<<< HEAD
<<<<<<< HEAD
        prison = Character.getPrison(i);
         d-=(d/10);
=======
>>>>>>> parent of 2b83bec... Minor Edits to Formatting
=======
>>>>>>> parent of 2b83bec... Minor Edits to Formatting
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