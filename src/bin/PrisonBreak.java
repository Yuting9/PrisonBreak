/*********************************************************************************
 * Assignment: Final Summative
 * Description: A sidescroller where you play as a police officer chasing down
 * 				a runaway prisoner. You win if you catch the prisoner, and you
 * 				lose if the prisoner gets too far away. Donuts give you points
 * 				and coffee speeds you up. You also get a "Skill" bonus to your
 * 				points if you catch the prisoner quickly or if you avoid a lot
 * 				of obstacles.
 *
 * Author: Yuting L.
 * Date: 19/05/16 through 13/06/16
 * Course: ICS4U1
 *********************************************************************************/

package bin;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class PrisonBreak implements ActionListener
{
	// Sets up the frame for the Main Menu
	JFrame	main	= new JFrame("On the Run - Main Menu");
	static PrisonBreak menu;
	static TheRun game;
	// The buttons used in the main menu
	JButton	btnNew, 
					btnLead, 
					btnHow;

	/**************************
	 * Constructors
	 **************************/

	/**
	 * Default PrisonBreak Constructor
	 */
	public PrisonBreak()
	{
		// Sets the size and layout of the main frame
		main.setBounds(300, 100, 600, 450);
		main.getContentPane().setLayout(new BorderLayout(0, 0));

		// Initializes the panel containing the name of the project,
		// as well as adding it to the north area of the main frame
		JPanel namePnl = new JPanel();
		main.getContentPane().add(namePnl, BorderLayout.NORTH);

		// Initializes the panel containing the image, as well as adding it to the
		// center of the panel
		JPanel centerPnl = new JPanel();
		main.getContentPane().add(centerPnl, BorderLayout.CENTER);

		// Initializes and sets the layout of panel containing the buttons
		JPanel btnPnl = new JPanel();
		btnPnl.setLayout(new GridLayout(1, 3));
		btnPnl.setPreferredSize(new Dimension(0, 40));
		
		// Adds the panel to the main frame
		main.getContentPane().add(btnPnl, BorderLayout.SOUTH);
		
		// Sets the New-Game button and adds it to the button panel
		btnNew = new JButton("New Game");
		btnNew.addActionListener(this);
		btnNew.setPreferredSize(new Dimension());
		btnPnl.add(btnNew);
		
		// Sets the How-To-Play button and adds it to the button panel
		btnHow = new JButton("How to Play");
		btnHow.addActionListener(this);
		btnPnl.add(btnHow);

		// Sets the leaderboard button and adds it to the button panel
		btnLead = new JButton("Leaderboards");
		btnLead.addActionListener(this);
		btnLead.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnPnl.add(btnLead);
		
		// Formats the label containing the title of the game 
		// and adds it to the name panel
		JLabel lblTitle = new JLabel("On The Run");
		lblTitle.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 38));
		namePnl.add(lblTitle);

		// Sets the icon for the image label and adds it to the panel
		JLabel lblImage = new JLabel("");
		lblImage.setIcon(new ImageIcon(PrisonBreak.class.getResource("/img/mainMenuIcon.jpg")));
		centerPnl.add(lblImage);
		
		// Sets the main frame as being not resizable and visible
		main.setResizable(false);
		main.setVisible(true);
	}// End default constructor

	/**
	 * The actionPerformed method containing info on what to do if buttons are pushed
	 * 
	 * @param ActionEvent e
	 */
	public void actionPerformed(ActionEvent e)
	{
		// If the new game button was clicked
		if (e.getSource() == btnNew)
		{
			// Set the main frame as invisible
			main.setVisible(false);
			// Run the game
			game = new TheRun();
		}// End If
		
		// If the how to play button was clicked
		if (e.getSource() == btnHow)
		{
			// Set the main frame as invisible
			main.setVisible(false);
			// Run the how to play screen
			howToPlay how = new howToPlay(main);
		}// End If
		
		// If the leaderboards button was clicked
		if (e.getSource() == btnLead)
		{
			// Set the main frame as invisible
			main.setVisible(false);
			
			// Attempt to open leaderboards
			try
			{
				// Run the leaderboards screen
				leaderboard second = new leaderboard(main);
			} 
			// If the leaderboard returns and error
			catch (IOException exept)
			{
				// Create a popup informing the user of an error recieving leaderboard information
				JOptionPane.showMessageDialog(main, "Error recieving leaderboard info");
			}// End try-catch
		}// End If
	}// End actionPerformed

	public static void main(String[] args)
	{
		//#main
		menu = new PrisonBreak();
	}// End Main
}// End PrisonBreak
