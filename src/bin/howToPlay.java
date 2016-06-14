/*********************************************************************************
  * Assignment: Final Summative
  * Description: A sidescroller where you play as a police officer chasing down
  *     a runaway prisoner. You win if you catch the prisoner, and you
  *     lose if the prisoner gets too far away. Donuts give you points
  *     and coffee speeds you up. You also get a "Skill" bonus to your
  *     points if you catch the prisoner quickly or if you avoid a lot
  *     of obstacles.
  *
  * Author: Yuting L. and Aunik D.C.
  * Date: 19/05/16 through 13/06/16
  * Course: ICS4U1
  *********************************************************************************/
package bin;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class howToPlay implements ActionListener
{
	// The buttons used in howToPlay
	JButton btnRtn, btnNext, btnBack;
	final JFrame howTo, goBack;
	// Creates a panel for each page
	JPanel[] pnlHelp = new JPanel[2];
	JLabel image;
	// initializes a page counter
	int page = 0;

	/**
	 * Default howToPlay Constructor
	 */
	howToPlay(final JFrame main)
	{
		goBack = main;
		// sets up the frame
		howTo = new JFrame("On the Run - How To Play");
		// sets up the window title, size, and layout of frame. Also makes it
		// visible
		howTo.getContentPane().setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 18));
		howTo.setVisible(true);
		howTo.setBounds(300, 100, 700, 450);
		howTo.getContentPane().setLayout(new BoxLayout(howTo.getContentPane(), BoxLayout.Y_AXIS));

		// Sets up a panel and its layout to add the title too
		// adds the panel to the main frame
		JPanel pnlName = new JPanel();
		howTo.getContentPane().add(pnlName);
		pnlName.setLayout(new BoxLayout(pnlName, BoxLayout.X_AXIS));

		// Creates a title and centers it
		// adds the title to pnlName
		JLabel lblTitle = new JLabel("How To Play");
		pnlName.add(lblTitle);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 18));

		// Checks which page the user is on and sets the panel
		pnlHelp[page] = new JPanel();
		// adds the image to the label
		image = new JLabel(
		new ImageIcon(howToPlay.class.getResource("/img/HowToPlayPage" + page + ".png")));
		// adds the label to the panel
		pnlHelp[page].add(image);
		// adds the panel to the main frame
		howTo.getContentPane().add(pnlHelp[page]);

		// Creates a button panel and sets its layout
		// adds the panel to the main frame
		JPanel pnlBtn = new JPanel();
		howTo.getContentPane().add(pnlBtn);
		pnlBtn.setLayout(new BoxLayout(pnlBtn, BoxLayout.X_AXIS));

		// Sets the Back button and adds it to the button panel
		// The buttton is initially not clickable
		btnBack = new JButton("Back");
		btnBack.addActionListener(this);
		btnBack.setEnabled(false);
		pnlBtn.add(btnBack);

		// Sets the Return to Menu button and adds it to the button panel
		btnRtn = new JButton("Return to Menu");
		btnRtn.addActionListener(this);
		pnlBtn.add(btnRtn);

		// Sets the Next button and adds it to the button panel
		btnNext = new JButton("Next");
		btnNext.addActionListener(this);
		pnlBtn.add(btnNext);
	}// End howToPlay

	/**
	 * The actionPerformed method containing info on what to do if buttons are
	 * pushed
	 * 
	 * @param ActionEvent
	 *          e
	 */
	public void actionPerformed(ActionEvent e)
	{

		// If the Return to Menu button was clicked
		if (e.getSource() == btnRtn)
		{
			// Returns to main menu
			goBack.setVisible(true);
			// Disposes howToPlay frame
			howTo.dispose();
		}// End if

		// If the Next button was clicked
		if (e.getSource() == btnNext)
		{
			page++;
			image.setIcon(
			new ImageIcon(howToPlay.class.getResource("/img/HowToPlayPage" + page + ".png")));
			// Allows user to click the Back button
			btnBack.setEnabled(true);

			// If the page number is greater than or equal to the number of pages the
			// next button is not clickable
			if (page >= pnlHelp.length)
			{
				btnNext.setEnabled(false);
			}// End if
		}// End if

		// If the Back button was clicked
		if (e.getSource() == btnBack)
		{
			page--;
			// Image is set and Next button is clickable
			image.setIcon(
			new ImageIcon(howToPlay.class.getResource("/img/HowToPlayPage" + page + ".png")));
			btnNext.setEnabled(true);

			// If the page number is 0 the Back button is not clickable
			if (page == 0)
			{
				btnBack.setEnabled(false);
			}// End if
		}// End if
	}// End actionPerformed
}// End howToPlay
