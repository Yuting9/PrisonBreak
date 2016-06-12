package bin;

import javax.swing.*;
import java.awt.event.*;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;

public class TheRun extends JPanel implements ActionListener, KeyListener
{
	// The main frame of the game
	JFrame		frame				= new JFrame();

	// Initializing the Donut Class
	// Initializing the Character class as a player and a prisoner
	Character	player			= new Character(false),
	          prison = new Character(true);
	// Sets boolean values, preGame controls the countdown, pressHold determines
	// if the jump key is pressed and held, pressedOnce prevents multi-jumping,
	// and isUrban controls the background.
	boolean		preGame			= true,
	          pressHold = false, pressedOnce = false, urban = true, notEnd = true;
	// Sets integer values, plTime is the frame the player character is to be at,
	// doTime is the frame the donuts are to be at, and prTime is the frame the
	// prisoner is to be at. x controls where the buildings start, xCoor
	int				plTime			= 0,
	          doTime = 0, prTime = 0, buildCount = 0, change, spd, distance;
	JLabel		background	= new JLabel();
	Timer			clock				= new Timer(40, this);
	String		playerMode	= "run",
	          prisonMode = "run";
	Sprite[]	onScreen		= new Sprite[6];
	Donut[]		onDonut			= new Donut[5];
	Coffee		onCoffee		= new Coffee();
	Garbage[]	onGarb			= new Garbage[2];
	DrawPanel	panel				= new DrawPanel();

	public TheRun()
	{

		/*
		 * for (int d = 0; d < 2; d++) { onDonut[d] = new Sprite(-100 + (210*d)); }
		 */
		for (int i = 0; i < 6; i++)
		{
			onScreen[i] = new Sprite(-100 + (210 * i));
		}

		for (int i = 0; i < 5; i++)
		{
			onDonut[i] = new Donut(i);
		}

		for (int i = 0; i < 2; i++)
		{
			onGarb[i] = new Garbage((int) Math.random() * 2);
		}

		frame.addKeyListener(this);
		frame.setFocusable(true);
		frame.setResizable(false);
		frame.setBounds(100, 100, 900, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel = new DrawPanel(player.getImage(0), prison.getImage(0), Donut.getImage(0));
		panel.setBounds(100, 100, 900, 500);
		frame.getContentPane().add(panel);
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
  distance = ( (prison.getX() - player.getX()) / 10);
  if (preGame)
  {
   // Display countdown
   if (panel.countDown <= 0)
   {
    preGame = false;
    plTime++;
    clock.setDelay(50);
   }
   else if (panel.countDown <= 1)
   {
    clock.setDelay(250);
   }
   panel.updateDown();
  }
  else
  {
   panel.setDonutImage(Donut.getImage(doTime));
   doTime++;
   if (doTime == 4)
   {
    doTime = 0;
   }

   spd = player.getSpd();
   clock.setDelay(spd);
   if (spd >= 60)
   {
    change = 50 - (spd / 5);
    player.setSpd(spd -= (spd / 5));
   }
   if (playerMode.equals("run"))
   {
    panel.setCopImage(player.getImage(plTime));
    plTime++;
    if (plTime == 6)
    {
     plTime = 0;
    }
   }
   else if (playerMode.equals("jump"))
   {
    plTime = 0;
    if (player.getVert() >= 0)
    {
     panel.setCopImage(player.getImage(0));
    }
    else
    {
     panel.setCopImage(player.getImage(1));
    }
   }
   else if (playerMode.equals("roll"))
   {
    pressedOnce = false;
    player.isNotReleased();
    if (plTime == 3)
    {
     player.doRun();
    }
    panel.setCopImage(player.getImage(plTime));
    plTime++;
   }
   if (prisonMode.equals("run"))
   {
    if (prTime < 5)
    {
     prTime++;
     panel.setPrisImage(prison.getImage(prTime));
     prTime--;
    }
    else
    {
     panel.setPrisImage(prison.getImage(0));
    }

    prTime++;
    if (prTime == 6)
    {
     prTime = 0;
    }
   }

   else if (prisonMode.equals("jump"))
   {
    if (prison.getVert() > 0)
    {
     panel.setCopImage(prison.getImage(0));
    }
    else
    {
     panel.setCopImage(prison.getImage(1));
    }

   }
   panel.updatePrison(0);
   panel.updateDown();
   for (int i = 0; i < 6; i++)
   {
    onScreen[i].move(40);
   }
   for (int i = 0; i < 5; i++)
   {
    onDonut[i].move(20);
    if (onDonut[i].getX() < -300)
    {
     int x = (int) (Math.random() * 5);
     System.out.println(x);
     if (x == 2)
     {
      System.out.println("New Donut");
      onDonut[i].reGen();
     }
    }
    if (onDonut[i].getX() > player.getX()-20 && onDonut[i].getX() < player.getX() + 20
      && onDonut[i].getY() + 5 > player.getY() && onDonut[i].getY() < player.getY() + 100 && !onDonut[i].getEaten())
    {
     onDonut[i].eaten();
    }
   }
   if (onCoffee.getX() > player.getX()-20 && onCoffee.getX() < player.getX() + 20
     && onCoffee.getY() + 5 > player.getY() && onCoffee.getY() < player.getY() + 100 && !onCoffee.getEaten())
   {
    onCoffee.eaten();
    Character.addDiff(5);
   }
   if (onCoffee.isDeployed())
   {
    onCoffee.move(20);
   }
   
   if (onCoffee.getX() < -300)
   {
    onCoffee.eaten();
   }
   
   if (!onCoffee.isDeployed() && (int)(Math.random()*10) == 0)
   {
    System.out.println("Deployed");
    onCoffee.deploy();
   }
   
   for(int i = 0; i<2; i++)
   {
  	 if(onGarb[i].getX() < -300){
  		 onGarb[i].reGen((int)(Math.random()*2));
  	 }
  	 if(player.getX() > onGarb[i].getX() && player.getX() < onGarb[i].getX() + 75*(onGarb[i].getSize()+1) &&
  	 		player.getY() + 40 > onGarb[i].getY() && player.getY()< onGarb[i].getY() + 100 && 
  	 		!onGarb[i].getHit())
  	 {
  		 System.out.println("Slowed" + player.getX() + ' ' + onGarb[i].getX());
  		 Character.addDiff(-5);
  		 onGarb[i].hit();
  	 }
  	 onGarb[i].move(20);
   }
   
   if (prTime % 5 == 0)
    Donut.advance();
   revalidate();
   if (prison.getX() <= player.getX())
   {
    win();
    notEnd = false;
   }
   else if (prison.getX() > 900)
   {
  	 lose();
  	 notEnd = false;
   }
  }
 }

	private void win()
	{
		frame.dispose();
		clock.stop();
		Victory win = new Victory(true);
	}

	private void lose()
	{
		frame.dispose();
		clock.stop();
		Victory win = new Victory(false);
	}

	class DrawPanel extends JPanel
	{
		ImageIcon	cop, prisoner, dondon;
		String		mode			= "run";
		int				countDown	= 4;

		public DrawPanel(ImageIcon firstCop, ImageIcon firstPris, ImageIcon donut)
		{
			cop = firstCop;
			prisoner = firstPris;
			dondon = donut;
		}

		public DrawPanel()
		{
			// TODO Auto-generated constructor stub
		}

		public void updateDown()
		{
			countDown--;
		}

		public void updatePrison(int change)
		{
			prison.setX(prison.getX() - change - Character.getDiff());
			if (Character.getDiff() > 1)
				Character.setDiff(Character.getDiff() - 1);
			if (Character.getDiff() < -1)
				Character.setDiff(Character.getDiff() + 1);
		}

		public void setCopImage(ImageIcon newCop)
		{
			cop = newCop;
			if (player.getMode().equals("jump"))
			{
				player.jump();
			}
		}

		public void setDonutImage(ImageIcon newDonut)
		{
			dondon = newDonut;
		}

		public void setPrisImage(ImageIcon newPris)
		{
			prisoner = newPris;
		}

		private void paintCount(Graphics g)
		{
			if (countDown > 0)
			{
				g.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 100));
				g.drawString(Integer.toString(countDown), 150, 300);
			}
			else if (countDown == 0)
			{
				g.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 200));
				g.drawString("Go!", 150, 300);
			}
		}

		private void paintBack(Graphics g)
		{
			g.setColor(new Color(0, 128, 231));
			g.fillRect(0, 0, 1000, 600);

			for (int i = 0; i < 6; i++)
			{
				if (onScreen[i].getImage() != null)
					onScreen[i].getImage().paintIcon(this, g, onScreen[i].getX(), onScreen[i].getY());
				if (onScreen[i].getX() < -300)
				{
					onScreen[i].reGen(urban);
					buildCount++;
					if (buildCount >= 50)
					{
						buildCount = 0;
						urban = !urban;
					}
				}
			}

			g.setColor(Color.darkGray);
			g.fillRect(0, 450, 1000, 100);
		}

		private void paintCop(Graphics g)
		{
			if (player.getMode().equals("jump"))
			{
				cop.paintIcon(this, g, 50, player.getY());
			}
			cop.paintIcon(this, g, 50, player.getY());
		}

		private void paintPrison(Graphics g)
		{
			prisoner.paintIcon(this, g, prison.getX(), 338);
		}

		private void paintDonut(Graphics g)
		{
			for (int i = 0; i < 5; i++)
			{
				if (onDonut[i].getImage(doTime) != null && !onDonut[i].getEaten())
				{
					onDonut[i].getImage(doTime).paintIcon(this, g, onDonut[i].getX(), onDonut[i].getY());
				}
			}
		}

		private void paintCoffee(Graphics g)
		{
			if (onCoffee.isDeployed())
			{
				onCoffee.getImage().paintIcon(this, g, onCoffee.getX(), onCoffee.getY());
			}
		}

		private void paintGarbage(Graphics g)
		{
			for (int i = 0; i < 2; i++)
			{
				onGarb[i].getImage().paintIcon(this, g, onGarb[i].getX(), onGarb[i].getY());
			}
		}

		private void paintDistance(Graphics g)
		{
			g.setColor(Color.black);
			g.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
			g.drawString("Distance: " + distance, 50, 50);
		}

		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			paintBack(g);
			paintCop(g);
			paintPrison(g);
			paintCount(g);
			paintDonut(g);
			paintCoffee(g);
			paintDistance(g);
			paintGarbage(g);
			repaint();
		}
	}

	@Override
	public void keyPressed(KeyEvent e)
	{
		// TODO Auto-generated method stub
		if (e.getKeyCode() == 39)
		{
			Character.addDiff(5);
		}
		else if (e.getKeyCode() == 37)
		{
			Character.addDiff(-5);
		}

		else if (e.getKeyCode() == 32)
		{
			if (!pressHold && !pressedOnce && spd == 54)
			{
				;
				player.doJump();
				player.setVert(30);
				pressHold = true;
				pressedOnce = true;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
		if (e.getKeyCode() == 32 && spd == 54)
		{
			System.out.println("Space-Released");
			pressHold = false;
			if (player.getMode().equals("jump"))
			{
				player.isReleased();
			}
		}
	}

	public void keyTyped(KeyEvent arg0)
	{
	}
}