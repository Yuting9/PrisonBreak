/*********************************************************************************
 * Assignment: Final Summative 
 * Description: The final results screen, with file input, output, and sorting
 *
 * Main Author: Yuting L. 
 * Date: 19/05/16 through 13/06/16 
 * Course: ICS4U1
 *********************************************************************************/
package bin;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;
import java.io.*;

public class Victory implements ActionListener, KeyListener
{
	// The main frame of the results
	JFrame frame = new JFrame(), menu;

	// The points, with pointD, pointC, pointT, pointG, and totP correlating to
	// Donuts, Coffee, Time, Garbage, and Totals. Index is used for sorting.
	int pointD = PrisonBreak.game.pointD, pointC = PrisonBreak.game.pointC,
	pointT = (1100 - PrisonBreak.game.pointT) * 2, pointG = PrisonBreak.game.pointG, index = 20,
	totP;

	// The global componenets used.
	JButton btnSubmit;
	JTextField name;

	// The variables used to hold input from file
	String holding, newName;
	String[] info = new String[3], names = new String[21];
	String score;
	int[] scoreList = new int[21];

	/**
	 * Constructor to create final results screen
	 * 
	 * @param really
	 *          - Whether or not you actually won
	 * @param main
	 *          - The Main Menu.
	 */
	Victory(boolean really, JFrame main)
	{
		// Sets the global variable to the parameter
		menu = main;

		// Sets up the frame.
		frame.setFocusable(true);
		frame.setBounds(300, 100, 600, 450);
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

		// If there is negative speed score, there is no speed score.
		if (pointT < 0)
			pointT = 0;

		// Sets up the panels
		JPanel pnlVerdict = new JPanel();
		frame.getContentPane().add(pnlVerdict);

		JLabel lblVerdict = new JLabel("");
		pnlVerdict.add(lblVerdict);
		lblVerdict.setHorizontalAlignment(SwingConstants.CENTER);

		// If you really won
		if (really)
		{
			// Set the victory image
			lblVerdict.setIcon(new ImageIcon(Victory.class.getResource("/img/Victory0.png")));
		}

		else
		{
			// You get no time bonus and sets the failure image
			pointT = 0;
			lblVerdict.setIcon(new ImageIcon(Victory.class.getResource("/img/Victory1.png")));
		}

		// Sets preferred size
		lblVerdict.setPreferredSize(new Dimension(128, 128));

		// Sets up scoring panels and their components
		JPanel pnlScore = new JPanel();
		frame.getContentPane().add(pnlScore);
		pnlScore.setLayout(new BoxLayout(pnlScore, BoxLayout.Y_AXIS));

		JPanel pnlDonuts = new JPanel();
		pnlScore.add(pnlDonuts);
		pnlDonuts.setLayout(new BoxLayout(pnlDonuts, BoxLayout.X_AXIS));

		JLabel lblDonut = new JLabel("Donuts  = ");
		lblDonut.setToolTipText("The amount of Donuts you got");
		lblDonut.setForeground(new Color(50, 205, 50));
		pnlDonuts.add(lblDonut);

		JLabel lblSDon = new JLabel(Integer.toString(pointD));
		pnlDonuts.add(lblSDon);

		JPanel pnlCoffee = new JPanel();
		pnlScore.add(pnlCoffee);
		pnlCoffee.setLayout(new BoxLayout(pnlCoffee, BoxLayout.X_AXIS));

		JLabel lblCoffee = new JLabel("Coffee = ");
		lblCoffee.setToolTipText("The amount of times you got coffee");
		lblCoffee.setForeground(new Color(50, 205, 50));
		pnlCoffee.add(lblCoffee);

		JLabel lblSCof = new JLabel(Integer.toString(pointC));
		pnlCoffee.add(lblSCof);

		JPanel pnlGarb = new JPanel();
		pnlScore.add(pnlGarb);
		pnlGarb.setLayout(new BoxLayout(pnlGarb, BoxLayout.X_AXIS));

		JLabel lblGarbage = new JLabel("Garbage = ");
		lblGarbage.setToolTipText("The amount of times you hit a garbage can");
		lblGarbage.setForeground(new Color(255, 0, 0));
		pnlGarb.add(lblGarbage);

		JLabel lblSGarb = new JLabel(Integer.toString(pointG));
		pnlGarb.add(lblSGarb);

		JPanel pnlTime = new JPanel();
		pnlScore.add(pnlTime);
		pnlTime.setLayout(new BoxLayout(pnlTime, BoxLayout.X_AXIS));

		JLabel lblTime = new JLabel("Speed Bonus = ");
		lblTime
		.setToolTipText("The faster you are at catching the prisoner, the higher this score");
		lblTime.setForeground(new Color(50, 205, 50));
		pnlTime.add(lblTime);

		JLabel lblSTime = new JLabel(Integer.toString(pointT));
		pnlTime.add(lblSTime);

		JPanel pnlVictory = new JPanel();
		pnlScore.add(pnlVictory);
		pnlVictory.setLayout(new BoxLayout(pnlVictory, BoxLayout.X_AXIS));

		JLabel lblVictory = new JLabel("");
		lblVictory.setToolTipText("If you won or lost");
		pnlVictory.add(lblVictory);

		JLabel lblSVic = new JLabel("");
		pnlVictory.add(lblSVic);

		JPanel pnlTotal = new JPanel();
		pnlScore.add(pnlTotal);

		JLabel lblTotal = new JLabel("");
		lblTotal.setForeground(Color.BLUE);
		pnlTotal.add(lblTotal);

		if (really)
		{
			lblVictory.setForeground(new Color(50, 205, 50));
			lblVictory.setText("Victory = ");

			totP += 1000;
			lblSVic.setText("1000");
		}

		else
		{
			lblVictory.setForeground(new Color(255, 0, 0));
			lblVictory.setText("Failure = ");

			totP -= 500;
			lblSVic.setText("-500");
		}

		// The file output panels are added
		JPanel pnlName = new JPanel();
		frame.add(pnlName);
		name = new JTextField(10);
		new GhostText(name, "Enter your Name");
		pnlName.addKeyListener(this);
		pnlName.add(name);

		JPanel pnlSubmit = new JPanel();
		frame.add(pnlSubmit);
		btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(this);
		pnlSubmit.add(btnSubmit);

		totP += pointD + pointC + pointG + pointT;

		lblTotal.setText("Total = " + Integer.toString(totP));

		frame.pack();
		frame.setVisible(true);
	}// End Victory

	/**
	 * Sorts the scoreList array
	 */
	public void sort() // #save
	{
		int temp;

		for (int i = 20; i > 0; i--)
		{
			if (scoreList[i] > scoreList[i - 1])
			{
				temp = scoreList[i];
				scoreList[i] = scoreList[i - 1];
				scoreList[i - 1] = temp;
				index--;
			}
			else
			{
				break;
			}// End if
		}// End for
	}// End sort

	/**
	 * Reads from file into scoreList and names
	 */
	public void read()
	{
		// Attempt to open and read the file
		try
		{
			// Create a file Scanner
			Scanner scanFile = new Scanner(new File("lead.info"));

			// Keep going until the end of the file
			for (int i = 0; i < 20; i++)
			{
				// read the current line
				if (scanFile.hasNextLine())
				{
					holding = scanFile.nextLine();
					info = holding.split("<</>>");
					names[i] = info[0];
					scoreList[i] = Integer.parseInt(info[1]);
				}
				else
				{
					names[i] = null;
					scoreList[i] = -9999;
				}// End if
			}// End for
			 // Close the file
			scanFile.close();
		}
		catch (FileNotFoundException e)
		{
			System.out.println("The following problem reading from a file occurred:\n" + e);
		}// End try/catch
	}// End read

	/**
	 * Writes to the file
	 */
	public void write() // #save
	{
		int nActual = 0;
		try
		{
			// Create a file Scanner
			FileWriter write = new FileWriter("lead.info");
			PrintWriter file = new PrintWriter(write);

			// Keep going until the end of the file
			for (int i = 0; i < 20; i++)
			{
				if (index == i)
				{
					file.println(newName + "<</>>" + scoreList[i]);
				}
				else if (scoreList[i] == -9999)
				{
					break;
				}
				else
				{
					file.println(names[nActual] + "<</>>" + scoreList[i]);
					nActual++;
				}// End if
			}// End for
			file.close();
			write.close();
		}
		catch (FileNotFoundException e)
		{
			System.out.println("The following problem writing from a file occurred:\n" + e);
		}// end try/catch
		catch (IOException e)
		{
			System.out.println("The following problem writing from a file occurred:\n" + e);
		}// End try/catch
	}// End write

	/**
	 * Listens for the button
	 */
	public void actionPerformed(ActionEvent e)
	{
		// If the button was clicked
		if (e.getSource() == btnSubmit)
		{
			// If the text has the special split character, popup an error
			if (name.getText().contains("<</>>"))
			{
				JOptionPane.showMessageDialog(null, "You are using <</>>, which is not allowed.");
			}
			else
			{
				// Setup text and score, and being reading, sorting, then writing
				newName = name.getText();
				scoreList[20] = totP;
				read();
				sort();
				write();

				// Return to menu
				frame.dispose();
				menu.setVisible(true);
			}// End if
		}//End if
	}// End actionPerformed

	@Override
	public void keyPressed(KeyEvent e)
	{
	}

	public void keyReleased(KeyEvent e)
	{
	}

	public void keyTyped(KeyEvent e)
	{// If the button was clicked
		if (e.getSource() == btnSubmit)
		{
			// If the text has the special split character, popup an error
			if (name.getText().contains("<</>>"))
			{
				JOptionPane.showMessageDialog(null, "You are using <</>>, which is not allowed.");
			}
			else
			{
				// Setup text and score, and being reading, sorting, then writing
				newName = name.getText();
				scoreList[20] = totP;
				read();
				sort();
				write();

				// Return to menu
				frame.dispose();
				menu.setVisible(true);
			}// End if
		}// End if
	}//End keyTyped
}// End Victory
