package bin;

import javax.swing.*;
import java.awt.event.*;
import java.awt.BorderLayout;

public class TheRun extends JPanel implements ActionListener
{
	
	JFrame frame = new JFrame();
	ImageIcon prison;
	int i = 0, d = 500;
	int pX = 100, pY = 100;
    JLabel holder = new JLabel();
    Timer clock = new Timer(40, this);
	public TheRun() {
		frame.setResizable(false);
		frame.setSize(600,450);
		DrawPanel panel = new DrawPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		frame.setVisible(true);
		
		panel.add(prison, pX, pY);
	}
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        
        clock.setDelay(d);
        if(d>=42)
        	d-=(d/10);
        prison = Character.getPrison(i);
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