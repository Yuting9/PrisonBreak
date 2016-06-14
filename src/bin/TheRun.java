package bin;

import javax.swing.*;
import java.awt.event.*;
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
	// and isUrban controls how the background looks.
	boolean		preGame			= true,
	          pressHold = false, pressedOnce = false, urban = true, notEnd = true;
	// Sets integer values:
	// plTime is the frame the player character is to be at.
	// prTime is the frame the prisoner is to be at.
	// buildCount counts the amount of buildings that have passed and changes the
	//   background accordingly
	// change is the speed the buildings move at
	// spd is the player's speed
	// distance is the distance between the player and the prisoner
	// pointD, pointC, pointT, and pointG represent the points received
	//   by Donuts, Coffee, Speed, and Garbage Cans respectively.
	// difi is the difficulty that increments with playtime.
	int				plTime			= 0,
	          prTime = 1, buildCount = 0, change, spd, distance, pointD, pointC, pointT, pointG,
	          difi = 2;
	// The timer used to animate the screen
	Timer			clock				= new Timer(40, this);
	// The modes the player and prisoner are in
	String		playerMode	= "run",
	          prisonMode = "run";
	// The array containing all the buildings on-screen at one moment
	Sprite[]	onScreen		= new Sprite[6];																											// #object
	// The array containing all the donuts on-screen at one moment
	Donut[]		onDonut			= new Donut[5];																												// #object
	// The variable controlling the coffee cup
	Coffee		onCoffee		= new Coffee();																												// #object
	// The array containing all the garbage cans on-screen at one moment
	Garbage[]	onGarb			= new Garbage[difi];																									// #object
	// The DrawPanel used to contain all the items in the game
	DrawPanel	panel				= new DrawPanel();																										// #object

	// Game Class
	public TheRun()
	{

		// Initializes all the buildings
		for (int i = 0; i < 6; i++)
		{
			onScreen[i] = new Sprite(-100 + (210 * i));
		}

		// Initializes all the donuts
		for (int i = 0; i < 5; i++)
		{
			onDonut[i] = new Donut(i);
		}

		// Initializes all the garbage cans
		for (int i = 0; i < difi; i++)
		{
			onGarb[i] = new Garbage((int) Math.random() * 2);
		}

		// Adds a key listener to the frame
		frame.addKeyListener(this);

		// Focuses the frame to ensure keyboard listening
		frame.setFocusable(true);

		// Restricts resizing of the frame
		frame.setResizable(false);

		// Creates the bounds for the frame
		frame.setBounds(100, 100, 900, 500);

		// Creates a default close operation to ensure the game stops even though
		// the use force-exited
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// #error

		// Initializes the drawPanel with the images required to start off
		panel = new DrawPanel(player.getImage(0), prison.getImage(1), Donut.getImage());

		// Sets the boundaries for the drawPanel
		panel.setBounds(0, 0, 900, 500);

		// Adds the panel to the content pane of the frame
		frame.getContentPane().add(panel);

		// Makes the frame visible
		frame.setVisible(true);

		// Starts the timer
		clock.start();

		// Deploys the prisoner
		prison.setX(600);
	}

	// Contains the majority of the actionable code
	public void actionPerformed(ActionEvent e)
	{
		// Recieves the mode the player and prisoner are in
		playerMode = player.getMode();
		prisonMode = prison.getMode();

		// Sets the delay for the clock
		clock.setDelay(1000);

		// Recieves the distance between the player and the prisoner
		distance = ((prison.getX() - player.getX()) / 10);

		// If it is before the game (countdown)
		if (preGame)
		{
			// If the coundown timer is past zero
			if (panel.countDown <= 0)
			{
				// It is no longer before the game (Game starts)
				preGame = false;

				// Increments the frame the player is at
				plTime++;

				// Sets the delay to a much shorter one
				clock.setDelay(50);
			}
			// If the countdown is past one
			else if (panel.countDown <= 1)
			{
				// Slow down the delay
				clock.setDelay(250);
			} // End If

			// Update the countdown painter
			panel.updateDown();
		}
		// The game has started already
		else
		{
			// Get the player's initial speed
			spd = player.getSpd();

			// Sets the clock's delay to that speed
			clock.setDelay(spd);

			// If the speed is larger than 60
			if (spd >= 60)
			{
				// Move the buildings a set amount
				change = 50 - (spd / 5);

				// Chenge the speed of the players
				player.setSpd(spd -= (spd / 5));
			}

			// If the player is running
			if (playerMode.equals("run"))
			{
				// Set the frame the player is at
				panel.setCopImage(player.getImage(plTime));

				// Increment the frame
				plTime++;

				// If the frame is the last frame, restart from beginning
				if (plTime == 6)
					plTime = 0;
			}

			// If the player is jumping
			else if (playerMode.equals("jump"))
			{
				// Sets the player's frame to 0
				plTime = 0;

				// If the player is moving up
				if (player.getVert() >= 0)
				{
					// Get the player's moving up image
					panel.setCopImage(player.getImage(0));
				}
				else
				{
					// Get the player's moving down image
					panel.setCopImage(player.getImage(1));
				}
			}

			// If the player is rolling
			else if (playerMode.equals("roll"))
			{
				// Resets the button press variable
				pressedOnce = false;

				// Releases the player
				player.isNotReleased();

				// If the player is at the last frame
				if (plTime == 3)
				{
					// Return to running
					player.doRun();
				} // End If

				// Set the image to be the rolling image
				panel.setCopImage(player.getImage(plTime));

				// Increment the frame
				plTime++;
			} // End If

			// If the prisoner is running (Used for possible future expansion allowing
			// for prisoner jumping)
			if (prisonMode.equals("run"))
			{
				// Set the prisoner's image
				panel.setPrisImage(prison.getImage(prTime));

				// Increments the frame
				prTime++;

				// If the frame is past the end
				if (prTime == 6)
				{
					// Reset the frame
					prTime = 0;
				} // End If
			}

			// If the prisoner is jumping
			else if (prisonMode.equals("jump"))
			{
				// If the prisoner is going up
				if (prison.getVert() > 0)
				{
					// Set the prisoner's up image
					panel.setPrisImage(prison.getImage(0));
				}
				else
				{
					// Set the prisoner's down image
					panel.setPrisImage(prison.getImage(1));
				} // End If

			} // End If

			// Updates the prisoner's position
			panel.updatePrison(0);

			// Moves the buildings a set amount
			for (int i = 0; i < 6; i++)
			{
				onScreen[i].move(40);
			} // End For
			  // For every donut on screen
			for (int i = 0; i < 5; i++)
			{
				// Move the donuts
				onDonut[i].move(20);

				// If the donut is off the screen
				if (onDonut[i].getX() < -300)
				{
					// Randomly chooses to generate another
					if ((int) (Math.random() * 5) == 2)
					{
						onDonut[i].reGen();
					} // End If
				} // End If

				// If the donut is in contact with the player
				if (onDonut[i].getX() > player.getX() - 20 && onDonut[i].getX() < player.getX() + 20
				&& onDonut[i].getY() + 5 > player.getY() && onDonut[i].getY() < player.getY() + 100
				&& !onDonut[i].getEaten())
				{
					// Add points to the player's donut counter
					pointD += 10;

					// Set the donut to eaten
					onDonut[i].eaten();
				}// End If
			}// End For
			if (onCoffee.getX() > player.getX() - 20 && onCoffee.getX() < player.getX() + 20
			&& onCoffee.getY() + 5 > player.getY() && onCoffee.getY() < player.getY() + 100
			&& !onCoffee.getEaten())
			{
				pointC += 100;
				onCoffee.eaten();
				Character.addDiff(1);
				if (Character.getDiff() == 3)
				{
					Character.addDiff(4);
				}
			}
			if (onCoffee.isDeployed())
			{
				onCoffee.move(20);
			}

			if (onCoffee.getX() < -300)
			{
				onCoffee.eaten();
			}

			if (!onCoffee.isDeployed() && (int) (Math.random() * 10) == 0)
			{
				onCoffee.deploy();
			}

			for (int i = 0; i < difi; i++)
			{
				if (onGarb[i].getX() < -300)
				{
					onGarb[i].reGen((int) (Math.random() * 2));
				}
				if (player.getX() > onGarb[i].getX() - 10
				&& player.getX() < onGarb[i].getX() + 75 * (onGarb[i].getSize() + 1)
				&& player.getY() + 60 > onGarb[i].getY() && !onGarb[i].getHit())
				{
					pointG -= 75;
					Character.addDiff(-1);
					if (Character.getDiff() == -3)
					{
						Character.addDiff(-4);
					}
					onGarb[i].hit();
				}
				onGarb[i].move(20);
			}
			pointT++;
			if (2 + pointT / 500 > difi)
			{
				difi += 1;
				Garbage[] temp = new Garbage[difi];
				for (int i = 0; i < difi - 1; i++)
				{
					temp[i] = onGarb[i];
				}
				temp[difi - 1] = new Garbage((int) Math.random() * 2);
				onGarb = temp;
			}
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
			if (Character.getDiff() > 2)
			{
				Character.addDiff(-1);
			}
			if (Character.getDiff() < -2)
			{
				Character.addDiff(1);
			}
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
				if (onDonut[i].getImage() != null && !onDonut[i].getEaten())
				{
					onDonut[i].getImage().paintIcon(this, g, onDonut[i].getX(), onDonut[i].getY());
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
			for (int i = 0; i < difi; i++)
			{
				onGarb[i].getImage().paintIcon(this, g, onGarb[i].getX(), onGarb[i].getY());
			}
		}

		private void paintDistance(Graphics g)
		{
			g.setColor(Color.black);
			g.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
			g.drawString("Distance: " + distance + "m", 50, 50);
			g.drawString("Points: " + (pointD + pointG + pointC), 600, 50);
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
		if (e.getKeyCode() == 39)// #cheat
		{
			Character.addDiff(1);
		}
		else if (e.getKeyCode() == 37)// #cheat
		{
			Character.addDiff(-1);
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