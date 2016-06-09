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
	boolean preGame = true, pressHold = false, pressedOnce = false;
	int plTime = 0, prTime = 0, x = 720, change, spd;
    JLabel background = new JLabel();
    Timer clock = new Timer(40, this);
    String playerMode = "run", prisonMode = "run";
    ImageIcon []bg = new ImageIcon [50];
    DrawPanel panel = new DrawPanel(player.getImage(0), prison.getImage(0));
	    
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
	   prison.setX(600);
	}
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
    	playerMode = player.getMode();
    	prisonMode = prison.getMode();
    	clock.setDelay(1000);
    	if(preGame){
    		//Display countdown
    		if(panel.countDown <= 0){
    			preGame = false;
    			plTime++;
    			clock.setDelay(50);
    		}
    		else if(panel.countDown <= 1){
    			clock.setDelay(250);
    		}
    		panel.updateDown();
    	}
    	else{
	    	spd = player.getSpd();
	    	System.out.println(spd);
	        clock.setDelay(spd);
	        if(spd>=60){
	            change = 50-(spd/10);
	        	player.setSpd(spd-=(spd/10));
	        }
	        if(playerMode.equals("run")){
		         //d-=(d/10);
		        panel.setCopImage(player.getImage(plTime));
		        //donut = Donut.getDonut(k);
		        //this.remove(holder);
		        
		        plTime++;
		        if (plTime == 6 )
		        {
		            plTime = 0;
		        }
	        }
	        else if(playerMode.equals("jump")){
	        	//clock.setDelay(500);
	        	plTime = 0;
	        	if(player.getVert() >= 0){
		        	panel.setCopImage(player.getImage(0));
		        }
		        else{
		        	panel.setCopImage(player.getImage(1));
		        }
	        }
	        else if(playerMode.equals("roll")){
	        	pressedOnce = false;
	        	player.isNotReleased();
	        	if(plTime == 3){
	        		player.doRun();
	        	}
	        	panel.setCopImage(player.getImage(plTime));
	        	plTime++;
	        }
	        if(prisonMode.equals("run")){
		        if(prTime<5){
		        	prTime++;
		        	panel.setPrisImage(prison.getImage(prTime));
		        	prTime--;
		        }
		        else{
		        	panel.setPrisImage(prison.getImage(0));
		        }
		        
		        prTime++;
		        if (prTime == 6 )
		        {
		            prTime = 0;
		        }
	        }
	        else if(prisonMode.equals("jump")){
	        	if(prison.getVert() > 0){
		        	panel.setCopImage(prison.getImage(0));
		        }
		        else{
		        	panel.setCopImage(prison.getImage(1));
		        }
	        	
	        }
	        panel.update(change);
	        panel.updateDown();
	        revalidate();
	        if(prison.getX()-100 == player.getX()){
	        	win();
	        }
    	}
    }
    
    private void win() {
    	frame.dispose();
    	clock.stop();
    	Victory win = new Victory(true);
	}
    
    private void lose() {
    	frame.dispose();
    	clock.stop();
    	Victory win = new Victory(false);
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
        	prison.setX(prison.getX()-change);
        }
    	
    	public void update(int diff){
    		x-=diff;
    	}
    	
    	public void setCopImage(ImageIcon newCop){
    		cop = newCop;
    		if(player.getMode().equals("jump")){
    			player.jump();
    		}
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
    	    g.fillRect(0,450,1000,50);
    	}
    	
    	private void paintCop(Graphics g){
    		if(player.getMode().equals("jump")){
    			cop.paintIcon(this, g, 50, player.getHeight());
    		}
    		cop.paintIcon(this, g, 50, player.getHeight());
    	}
    	
    	private void paintPrison(Graphics g){
    		prisoner.paintIcon(this, g, prison.getX(), 338);
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
		if(e.getKeyCode() == 39){
			System.out.println("Right");
			panel.updatePrison(5);
		}
		else if(e.getKeyCode() == 37){
			System.out.println("Left");
			panel.updatePrison(-5);
		}
		
		else if(e.getKeyCode() == 32){
			if(!pressHold && !pressedOnce && spd == 54){;
				player.doJump();
				player.setVert(20);
				pressHold = true;
				pressedOnce = true;
			}
		}
	}


	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == 32 && spd == 54){
			System.out.println("Space-Released");
			pressHold = false;
			player.isReleased();
		}
	}

	public void keyTyped(KeyEvent arg0) {}
}