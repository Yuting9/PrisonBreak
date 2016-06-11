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
	JFrame			frame				= new JFrame();
	
	// Initializing the Donut Class
	Donut				donut				= new Donut();
	// Initializing the Character class as a player and a prisoner
	Character		player			= new Character(false),
	            prison 			= new Character(true);
	// Sets boolean values, preGame controls the countdown, pressHold determines
	// if the jump key is pressed and held, pressedOnce prevents multi-jumping,
	// and isUrban controls the background.
	boolean			preGame			= true,
	            pressHold 	= false, 
	            pressedOnce = false, 
	            isUrban 		= true;
	// Sets integer values, plTime is the frame the player character is to be at,
	// doTime is the frame the donuts are to be at, and prTime is the frame the
	// prisoner is to be at. x controls where the buildings start, xCoor
	int					plTime			= 0,
	            doTime 			= 0,
	            prTime 			= 0,
	            x 					= 720,
	            xCoor 			= 0,
	            dHeight			= 0,
	            change,
	            spd;
	JLabel			background	= new JLabel();
	Timer				clock				= new Timer(40, this);
	String			playerMode	= "run",
	            prisonMode 	= "run";
	ImageIcon[]	bg					= new ImageIcon[50];
	DrawPanel		panel				= new DrawPanel();

	public TheRun()
	{
		dHeight = (int) (Math.random() * 200);
		xCoor = (int) ((Math.random() * 500) - 720);
		for (int t = 0; t < 50; t++)
		{
			bg[t] = new ImageIcon(PrisonBreak.class.getResource("/img/UrbanBuilding" + ((int) (Math.random() * 3)) + ".png"));
		}

		for (int r = 0; r < 50; r++)
		{
			bg[r] = new ImageIcon(PrisonBreak.class.getResource("/img/Rural" + ((int) (Math.random() * 3)) + ".png"));
		}

		frame.addKeyListener(this);
		frame.setFocusable(true);
		frame.setResizable(false);
		frame.setBounds(100, 100, 900, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel = new DrawPanel(player.getImage(0), prison.getImage(0), Donut.getImage());
		panel.setBounds(100,100,900,500);
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
		if (preGame)
		{
			// Display countdown
			if (panel.countDown <= 0)
			{
				preGame = false;
				plTime++;
				clock.setDelay(50);
			} else if (panel.countDown <= 1)
			{
				clock.setDelay(250);
			}
			panel.updateDown();
		} else
		{
			panel.setDonutImage(donut.getImage());
			spd = player.getSpd();
			clock.setDelay(spd);
			if (spd >= 60)
			{
				change = 50 - (spd / 10);
				player.setSpd(spd -= (spd / 10));
			}
			if (playerMode.equals("run"))
			{
				panel.setCopImage(player.getImage(plTime));
				plTime++;
				if (plTime == 6)
				{
					plTime = 0;
				}
			} else if (playerMode.equals("jump"))
			{
				plTime = 0;
				if (player.getVert() >= 0)
				{
					panel.setCopImage(player.getImage(0));
				} else
				{
					panel.setCopImage(player.getImage(1));
				}
			} else if (playerMode.equals("roll"))
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
				} else
				{
					panel.setPrisImage(prison.getImage(0));
				}

				prTime++;
				if (prTime == 6)
				{
					prTime = 0;
				}
			} else if (prisonMode.equals("jump"))
			{
				if (prison.getVert() > 0)
				{
					panel.setCopImage(prison.getImage(0));
				} else
				{
					panel.setCopImage(prison.getImage(1));
				}

			}
			panel.update(change);
			panel.updatePrison(0);
			panel.updateDown();
			if (prTime % 3 == 0)
				donut.advance();
			revalidate();
			if (donut.getX() - 50 <= player.getX() && donut.getHeight() - 50 <= player.getHeight())
			{
				// System.out.println("donut");
			}
			if (prison.getX() - 100 <= player.getX())
			{
				win();
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
		}

		public void update(int diff)
		{
			x -= diff;
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
			} else if (countDown == 0)
			{
				g.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 200));
				g.drawString("Go!", 150, 300);
			}
		}

		private void paintBack(Graphics g)
		{
			g.setColor(new Color(0, 128, 231));
			g.fillRect(0, 0, 1000, 600);

			if (isUrban)
			{
				for (int p = 0; p < 50; p++)
				{
					bg[p].paintIcon(this, g, x + (p * 200), 100);
				}
				isUrban = false;
			}

			else
			{
				for (int q = 0; q < 50; q++)
				{
					bg[q].paintIcon(this, g, x + (q * 270), 200);
				}
			}

			g.setColor(Color.darkGray);
			g.fillRect(0, 450, 1000, 100);
		}

		private void paintCop(Graphics g)
		{
			if (player.getMode().equals("jump"))
			{
				cop.paintIcon(this, g, 50, player.getHeight());
			}
			cop.paintIcon(this, g, 50, player.getHeight());
		}

		private void paintPrison(Graphics g)
		{
			prisoner.paintIcon(this, g, prison.getX(), 338);
		}

		private void paintDonut(Graphics g)
		{
			g.drawRect(100, 100, 50, 50);
			dondon.paintIcon(this, g, x + xCoor, dHeight);
		}

		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			paintBack(g);
			paintCop(g);
			paintPrison(g);
			paintCount(g);
			paintDonut(g);
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
		} else if (e.getKeyCode() == 37)
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
			if(player.getMode().equals("jump"))
			{
				player.isReleased();
			}
		}
	}

	public void keyTyped(KeyEvent arg0)
	{
	}
}