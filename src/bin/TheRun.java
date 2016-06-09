package bin;

import javax.swing.*;
import java.awt.event.*;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;

public class TheRun extends JPanel implements ActionListener, KeyListener
{
	JFrame frame = new JFrame();
	Character player = new Character(false);
	Character prison = new Character(true);
	boolean preGame = true;
	int pX = 600, pY = 100;
	int i = 0, k = 0, x = 700, change, spd;
    JLabel background = new JLabel();
    Timer clock = new Timer(40, this);
    String playerMode = "run";
    ImageIcon []bg = new ImageIcon [50];
    DrawPanel panel = new DrawPanel(player.getImage(0), prison.getImage(0));
    ImageIcon test = new ImageIcon(PrisonBreak.class.getResource("/img/UrbanBuilding" + (int)(Math.random()*2) + ".png"));
	    
	public TheRun() {
	   //donut = Donut.getDonut(k);
	   for (int t = 0; t < 50; t++)
	   {
		   bg[t] = new ImageIcon(PrisonBreak.class.getResource("/img/UrbanBuilding" + ((int)(Math.random()*2)) + ".png"));
	   }
	   frame.addKeyListener(this);
	   frame.setFocusable(true);
	   frame.setResizable(false);
	   frame.setSize(900,500);
	   frame.getContentPane().add(panel, BorderLayout.CENTER);
	   frame.setVisible(true);
	   clock.start();
	   frame.getContentPane().add(this);
	   frame.setVisible(true);
	}
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
    	playerMode = player.getMode();
    	if(playerMode.equals("run"))
    	clock.setDelay(250);
    	if(preGame){
    		//Display countdown
    		if(panel.countDown <= 0){
    			preGame = false;
    			i++;
    			clock.setDelay(5);
    		}
    		panel.updateDown();
    	}
    	else{
	    	spd = player.getSpd();
	        clock.setDelay(spd);
	        if(spd>=60){
	            change = 50-(spd/10);
	        	player.setSpd(spd-=(spd/10));
	        }
	        if(i<5){
	        	i++;
	        	panel.setPrisImage(prison.getImage(i));
	        	i--;
	        }
	        else{
	        	panel.setPrisImage(prison.getImage(0));
	        }
	         //d-=(d/10);
	        panel.setCopImage(player.getImage(i));
	        //donut = Donut.getDonut(k);
	        //this.remove(holder);
	        
	        //if()
	        
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
	        panel.update(change);
	        panel.updateDown();
	        revalidate();
    	}
    }
    
    class DrawPanel extends JPanel{ 
    	ImageIcon cop, prisoner;
    	String mode = "run";
    	int countDown = 4;
    	
    	public DrawPanel(ImageIcon firstCop, ImageIcon firstPris){
    		cop = firstCop;
    		prisoner = firstPris;
    	}
    	
    	public void updateDown(){
    		countDown--;
    	}
    	
        public void updatePrison(int change){
        	pX -= change;
        }
    	
    	public void update(int diff){
    		x-=diff;
    	}
    	
    	public void setCopImage(ImageIcon newCop){
    		cop = newCop;
    	}
    	
    	public void setPrisImage(ImageIcon newPris){
    		prisoner = newPris;
    	}
    	
    	
    	private void paintCount(Graphics g){
    		if(countDown>0){
    			g.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 100));
    			g.drawString(Integer.toString(countDown), 150, 300);
    		}
    		else if(countDown == 0){
    			g.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 200));
    			g.drawString("Go!", 150, 300);
    		}
    	}
    	
    	private void paintBack(Graphics g){
    	    for (int p = 0; p < 50; p++)
    	    {
    	      bg[p].paintIcon(this, g, x + (p * 200), 100);
    	    }
    	    g.setColor(Color.darkGray);
    	    g.fillRect(0,450,900,30);
    	}
    	
    	private void paintCop(Graphics g){
    		cop.paintIcon(this, g, 50, 338);
    	}
    	
    	private void paintPrison(Graphics g){
    		prisoner.paintIcon(this, g, pX, 338);
    	}
    	
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			//donut.paintIcon(this, g, 100, 350);
			paintBack(g);
			paintCop(g);
			paintPrison(g);
			paintCount(g);
		    repaint();
		}
    }

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		System.out.println(e.getKeyCode());
		if(e.getKeyCode() == 39){
			System.out.println("Right");
			panel.updatePrison(5);
		}
		else if(e.getKeyCode() == 37){
			System.out.println("Left");
			panel.updatePrison(-5);
		}
		
		else if(e.getKeyCode() == 32){
			System.out.println("Space");
			player.doJump();
		}
	}


	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == 32){
			System.out.println("Space-Released");
			player.doRoll();
		}
	}

	public void keyTyped(KeyEvent arg0) {}
}