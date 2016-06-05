package bin;

import javax.swing.*;
import java.awt.event.*;

public class TheRun extends JPanel implements ActionListener
{
	JFrame f = new JFrame();
	ImageIcon anima;
	int i = 0, d = 500;
    JLabel holder = new JLabel();
    Timer clock = new Timer(40, this);
    
    public void Window()
    {
    	f.setResizable(false);
        add(holder);
        clock.start();
        f.setSize(600,450);
        f.getContentPane().add(this);
        f.setVisible(true);
    }
    
    public void setPlayer(){
    	
    }
    
    public void setChase(){
    	
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