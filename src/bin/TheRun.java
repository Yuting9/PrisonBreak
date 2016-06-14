package bin;

import javax.swing.*;
import java.awt.event.*;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;

public class TheRun extends JPanel implements ActionListener, KeyListener
{
	// The main frame of the game
	JFrame frame = new JFrame();

	// Initializing the Donut Class
	// Initializing the Character class as a player and a prisoner
	Character player = new Character(false), prison = new Character(true);

	// Sets boolean values, preGame controls the countdown, pressHold determines
	// if the jump key is pressed and held, pressedOnce prevents multi-jumping,
	// and isUrban controls how the background looks.
	boolean preGame = true, pressHold = false, pressedOnce = false, urban = true;

	// Sets integer values:
	// plTime is the frame the player character is to be at.
	// prTime is the frame the prisoner is to be at.
	// buildCount counts the amount of buildings that have passed and changes the
	// background accordingly
	// change is the speed the buildings move at
	// spd is the player's speed
	// distance is the distance between the player and the prisoner
	// pointD, pointC, pointT, and pointG represent the points received
	// by Donuts, Coffee, Speed, and Garbage Cans respectively.
	// difi is the difficulty that increments with playtime.
	int plTime = 0, prTime = 1, buildCount = 0, change, spd, distance, pointD, pointC, pointT,
	pointG, difi = 2;

	// The timer used to animate the screen
	Timer clock = new Timer(40, this);

	// The modes the player and prisoner are in
	String playerMode = "run", prisonMode = "run";

	// The array containing all the buildings on-screen at one moment
	Sprite[] onScreen = new Sprite[6];// #object

	// The array containing all the donuts on-screen at one moment
	Donut[] onDonut = new Donut[5];// #object

	// The variable controlling the coffee cup
	Coffee onCoffee = new Coffee();// #object

	// The array containing all the garbage cans on-screen at one moment
	Garbage[] onGarb = new Garbage[difi];// #object

	// The DrawPanel used to contain all the items in the game
	DrawPanel panel = null;// #object

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
		panel = new DrawPanel(player.getImage(0), prison.getImage(1));

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
			} // End if

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
				} // End if

				// Set the image to be the rolling image
				panel.setCopImage(player.getImage(plTime));

				// Increment the frame
				plTime++;
			} // End if

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
				} // End if
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
				} // End if

			} // End if

			// Updates the prisoner's position
			panel.updatePrison(0);

			// Moves the buildings a set amount
			for (int i = 0; i < 6; i++)
			{
				onScreen[i].move(40);
			} // End for
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
					} // End if
				} // End if

				// If the donut is in contact with the player and not already eaten
				if (onDonut[i].getX() > player.getX() - 20 && onDonut[i].getX() < player.getX() + 20
				&& onDonut[i].getY() + 5 > player.getY() && onDonut[i].getY() < player.getY() + 100
				&& !onDonut[i].getEaten())
				{
					// Add points to the player's donut counter
					pointD += 10;

					// Set the donut to eaten
					onDonut[i].eaten();
				} // End if
			} // End for

			// If the coffee is in contact with the player and not already eaten
			if (onCoffee.getX() > player.getX() - 20 && onCoffee.getX() < player.getX() + 20
			&& onCoffee.getY() + 5 > player.getY() && onCoffee.getY() < player.getY() + 100
			&& !onCoffee.getEaten())
			{
				// Adds points to the coffee point counter
				pointC += 100;

				// Sets the coffee to eaten
				onCoffee.eaten();

				// Speeds up the character
				Character.addDiff(1);

				// If the character's speed after incrementing is 3, add another 4 .
				if (Character.getDiff() == 3)
				{
					Character.addDiff(4);
				} // End if
			} // End if

			// If the coffee is deployed
			if (onCoffee.isDeployed())
			{
				// Move the coffee
				onCoffee.move(20);
			} // End if

			// If the coffee is off the screen
			if (onCoffee.getX() < -300)
			{
				// Set the coffee to eaten
				onCoffee.eaten();
			} // End if

			// If the coffee is not deployed, randomly pass this if check
			if (!onCoffee.isDeployed() && (int) (Math.random() * 7) == 0)
			{
				// Deploys the coffee
				onCoffee.deploy();
			} // End if

			// For all garbage cans
			for (int i = 0; i < difi; i++)
			{
				// If the garbage is off the screen
				if (onGarb[i].getX() < -300)
				{
					// Regenerate the garbage
					onGarb[i].reGen((int) (Math.random() * 2));
				} // End if

				// If the garbage is in contact with the player
				if (player.getX() > onGarb[i].getX() - 10
				&& player.getX() < onGarb[i].getX() + 75 * (onGarb[i].getSize() + 1)
				&& player.getY() + 60 > onGarb[i].getY() && !onGarb[i].getHit())
				{
					// Reduce the garbage point counter
					pointG -= 75;

					// Slow down the player
					Character.addDiff(-1);

					// If the player's speed is -3 after decrementing
					if (Character.getDiff() == -3)
					{
						// Decremate the speed further by 4
						Character.addDiff(-4);
					} // End if

					// Set garbage to Hit
					onGarb[i].hit();
				} // End if

				// Move the garbage
				onGarb[i].move(20);
			} // End for

			// Increment the Speed points counter
			pointT++;

			// If the points counter divided by five plus two is larger than the
			// current difficulty
			if (2 + pointT / 500 > difi)
			{
				// Increment the difficulty
				difi += 1;

				// Create a temporary garbage array
				Garbage[] temp = new Garbage[difi];

				// For all but the last index in that array
				for (int i = 0; i < difi - 1; i++)
				{
					// Copy the current garbage's info
					temp[i] = onGarb[i];
				}

				// Set the last index in that array
				temp[difi - 1] = new Garbage((int) Math.random() * 2);

				// Set the main garbage array to the temporary
				onGarb = temp;
			}// End if

			// If the prisoner is in contact with the player
			if (prison.getX() <= player.getX())
			{
				// You win

				// Dispose of the current frame
				frame.dispose();

				// Stop the timer
				clock.stop();

				// Initialize a new Victory screen with true
				Victory win = new Victory(true);
			}

			// If the prisoner is off the screen
			else if (prison.getX() > 900)
			{
				// You lose

				// Dispose of the current frame
				frame.dispose();

				// Stop the timer
				clock.stop();

				// Initialize a new Victory screen with false
				Victory win = new Victory(false);
			}// End if
		}// End if
	}// End actionPerformed

	/**
	 * The panel used to display all game objects
	 * 
	 * @author Yuting, Aunik
	 */
	class DrawPanel extends JPanel
	{
		// ImageIcons for the player and prisoner
		ImageIcon cop, prisoner;

		// The integer used for the countdown
		int countDown = 4;

		/**
		 * Recieves the initial states of the images and sets them as Instance
		 * ImageIcons
		 * 
		 * @param firstCop
		 *          - The first frame of the player
		 * @param firstPris
		 *          - The first frame of the prisoner
		 */
		public DrawPanel(ImageIcon firstCop, ImageIcon firstPris)
		{
			cop = firstCop;
			prisoner = firstPris;
		}// End Constructor

		/**
		 * Decrements the countdown
		 */
		public void updateDown()
		{
			countDown--;
		}// End updateDown

		/**
		 * Updates the prisoner's position
		 * 
		 * @param change
		 *          - Any change made other than simple character speed difference
		 */
		public void updatePrison(int change)
		{
			// Sets the prisoner's new X
			prison.setX(prison.getX() - change - Character.getDiff());

			// If the difference between speeds is greater than 2, decrement;
			if (Character.getDiff() > 2)
			{
				Character.addDiff(-1);
			}// End if

			// If the difference between speeds is smaller than -2, increment;
			if (Character.getDiff() < -2)
			{
				Character.addDiff(1);
			}// End if
		}// End updatePrison

		/**
		 * Sets the player's new image
		 * 
		 * @param newCop
		 *          - The new image
		 */
		public void setCopImage(ImageIcon newCop)
		{
			// Assigns the new image to the instance variable
			cop = newCop;

			// If the mode is jump, jump.
			if (player.getMode().equals("jump"))
			{
				player.jump();
			}// End if
		}// End setCopImage

		/**
		 * Sets the prisoner's new image
		 * 
		 * @param newPris
		 *          - The new image
		 */
		public void setPrisImage(ImageIcon newPris)
		{
			// Assigns the new image to the instance variable
			prisoner = newPris;
		}// End setPrisImage

		/**
		 * Paints the countdown
		 * 
		 * @param g
		 *          - The graphics variable
		 */
		private void paintCount(Graphics g)
		{
			// If the countdown is larger than 0
			if (countDown > 0)
			{
				// Print out the number in black
				g.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 100));
				g.drawString(Integer.toString(countDown), 150, 300);
			}
			// If the countdown is zero
			else if (countDown == 0)
			{
				// Print out "Go!"
				g.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 200));
				g.drawString("Go!", 150, 300);
			}// End if
		}// End paintCount

		/**
		 * Paints the background
		 * 
		 * @param g
		 *          - The graphics variable
		 */
		private void paintBack(Graphics g)
		{
			// Sets the color of the background and draws it
			g.setColor(new Color(0, 128, 231));
			g.fillRect(0, 0, 1000, 600);

			// For every building
			for (int i = 0; i < 6; i++)
			{
				// If there's an image, draw the image
				if (onScreen[i].getImage() != null)
					onScreen[i].getImage().paintIcon(this, g, onScreen[i].getX(), onScreen[i].getY());

				// If the building is off the screen
				if (onScreen[i].getX() < -300)
				{
					// Regenerate the building
					onScreen[i].reGen(urban);

					// Increment building counter
					buildCount++;

					// If the building counter is 50
					if (buildCount >= 50)
					{
						// Set the building counter to 0
						buildCount = 0;
						// Invert urban
						urban = !urban;
					}// End if
				}// End if
			}// End for

			// Sets the color and draws the ground
			g.setColor(Color.darkGray);
			g.fillRect(0, 440, 1000, 100);
		}// End paintBack

		/**
		 * Paints the player
		 * 
		 * @param g
		 *          - The graphics variable
		 */
		private void paintCop(Graphics g)
		{
			cop.paintIcon(this, g, 50, player.getY());
		}// End paintCop

		/**
		 * Paints the prisoner
		 * 
		 * @param g
		 *          - The graphics variable
		 */
		private void paintPrison(Graphics g)
		{
			prisoner.paintIcon(this, g, prison.getX(), 338);
		}// End paintPrison

		/**
		 * Paints the donuts
		 * 
		 * @param g
		 *          - The graphics variable
		 */
		private void paintDonut(Graphics g)
		{
			// For all the images in the donut array
			for (int i = 0; i < 5; i++)
			{
				// If the donut is not eaten and exists
				if (onDonut[i].getImage() != null && !onDonut[i].getEaten())
				{
					// Paint the donut
					onDonut[i].getImage().paintIcon(this, g, onDonut[i].getX(), onDonut[i].getY());
				}// End if
			}// End for
		}// End paintDonut

		/**
		 * Paints coffee onto the screen
		 * 
		 * @param g
		 *          - The graphics variable
		 */
		private void paintCoffee(Graphics g)
		{
			// If the coffee is deployed
			if (onCoffee.isDeployed())
			{
				// Paint it onto the screen
				onCoffee.getImage().paintIcon(this, g, onCoffee.getX(), onCoffee.getY());
			}// End if
		}// End paintCoffee

		/**
		 * Paints garbage cans onto the screen
		 * 
		 * @param g
		 *          - The graphics variable
		 */
		private void paintGarbage(Graphics g)
		{
			// For all garbage cans in the array
			for (int i = 0; i < difi; i++)
			{
				// Paint the garbage cans
				onGarb[i].getImage().paintIcon(this, g, onGarb[i].getX(), onGarb[i].getY());
			}// End for
		}// End paintGarbage

		/**
		 * Paints the information, like score and distance
		 * 
		 * @param g
		 *          - The graphics variable
		 */
		private void paintInfo(Graphics g)
		{
			// Sets the color and font
			g.setColor(Color.black);
			g.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));

			// Paints the distance and points
			g.drawString("Distance: " + distance + "m", 50, 50);
			g.drawString("Points: " + (pointD + pointG + pointC), 600, 50);
		}// End paintInfo

		/**
		 * Paints the whole thing
		 * 
		 * @param g
		 *          - The graphics variable
		 */
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);

			// Paints everything in order
			paintBack(g);
			paintCop(g);
			paintPrison(g);
			paintCount(g);
			paintDonut(g);
			paintCoffee(g);
			paintInfo(g);
			paintGarbage(g);
			repaint();
		}// End paintComponent
	}// End DrawPanel

	/**
	 * If a key has been pressed
	 */
	public void keyPressed(KeyEvent e)
	{
		// If the key is the right arrow key
		if (e.getKeyCode() == 39)// #cheat
		{
			// Speed up by 1
			Character.addDiff(1);
		}
		// If the key is the left arrow key
		else if (e.getKeyCode() == 37)// #cheat
		{
			// Slow down by 1
			Character.addDiff(-1);
		}
		// If the key is the space bar
		else if (e.getKeyCode() == 32)
		{
			// If the spacebar is not pressed while jumping and is not being held
			if (!pressHold && !pressedOnce && spd == 54) //#error
			{
				// Set vertical movement to 30, jump, and
				player.doJump();
				player.setVert(30);

				// The spacebar is now for all intents and purposes being held, and the
				// character is now jumping
				pressHold = true;
				pressedOnce = true;
			}// End if
		}// End if
	}// End keyPressed

	/**
	 * If a key was released
	 */
	public void keyReleased(KeyEvent e)
	{
		// If the key is a spacebar and it isn't the acceleration period
		if (e.getKeyCode() == 32 && spd == 54)
		{
			// You are no longer holding the spacebar down
			pressHold = false;
			// If you are jumping, you are released.
			if (player.getMode().equals("jump"))
			{
				player.isReleased();
			}// End if
		}// End if
	}// End keyReleased

	/**
	 * Empty Class, required for Implements
	 */
	public void keyTyped(KeyEvent arg0)
	{
	}// End keyTyped
}// End The Run