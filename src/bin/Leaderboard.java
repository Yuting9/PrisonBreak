/*********************************************************************************
 * Assignment: Final Summative
 * Description: This is the leaderboard, reading from the lead.info file and
 * 							displaying it.
 *
 * Main Author: Yuting L.
 * Date: 19/05/16 through 13/06/16
 * Course: ICS4U1
 *********************************************************************************/

package bin;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.SwingConstants;

import java.awt.FlowLayout;
import java.io.*;
import java.util.Scanner;

public class Leaderboard
{
	// The file in which all the information is stored
	static File file = new File("lead.info");
	// The strings that contain information from the file
	// These are written in HTML
	String name = new String("<html>"), buff = new String("<html>"),
	score = new String("<html>"), holding = new String();
	// The string array that holds the file information in array form
	String[] info = new String[3];
	// The main frame containing all the relevant panels
	final JFrame lead = new JFrame("On the Run - Leaderboards");

	/**
	 * Default Leaderboard Constructor
	 */
	Leaderboard(final JFrame main) throws IOException
	{
		// Sets up lead and sets layout
		lead.getContentPane().setLayout(new BoxLayout(lead.getContentPane(), BoxLayout.X_AXIS));
		lead.setVisible(true);
		lead.setBounds(300, 100, 275, 450);

		// Create a time panel and add it to lead
		JPanel pnlTime = new JPanel();
		lead.getContentPane().add(pnlTime);
		pnlTime.setLayout(new BorderLayout(0, 0));

		// Creates a title
		JLabel lblHiScore = new JLabel(
		"<html>Leaderboards<br>---------------------------------------</html>");
		lblHiScore.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 32));
		lblHiScore.setHorizontalAlignment(SwingConstants.CENTER);
		pnlTime.add(lblHiScore, BorderLayout.NORTH);

		// Creates a button panel
		JPanel pnlBtn = new JPanel();
		pnlTime.add(pnlBtn, BorderLayout.SOUTH);
		pnlBtn.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		// Return to main menu button
		JButton btnRtn = new JButton("Return to Menu");
		btnRtn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				main.setVisible(true);
				lead.dispose();
			}// End actionPerformed
		});
		pnlBtn.add(btnRtn);

		JPanel panel = new JPanel();
		pnlTime.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));

		JLabel lblNames = new JLabel();
		lblNames.setHorizontalAlignment(SwingConstants.TRAILING);
		// lblNames.setBorder(BorderFactory.createLineBorder(Color.black));
		panel.add(lblNames, BorderLayout.WEST);
		lblNames.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 12));

		JLabel lblBuffer = new JLabel();
		lblBuffer.setHorizontalAlignment(SwingConstants.CENTER);
		// lblBuffer.setBorder(BorderFactory.createLineBorder(Color.black));
		panel.add(lblBuffer, BorderLayout.CENTER);
		lblBuffer.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 12));

		JLabel lblScores = new JLabel();
		lblScores.setVerticalAlignment(SwingConstants.TOP);
		// lblScores.setBorder(BorderFactory.createLineBorder(Color.blue));
		panel.add(lblScores, BorderLayout.EAST);
		lblScores.setHorizontalAlignment(SwingConstants.LEADING);
		lblScores.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 12));

		file.createNewFile();
		// Attempt to open and read the file
		try// #read
		{
			// Create a file Scanner
			Scanner scanFile = new Scanner(file);

			// Keep going until the end of the file
			for (int i = 1; i <= 20; i++)
			{
				if (scanFile.hasNextLine())
				{
					// read the current line
					holding = scanFile.nextLine();
					info = holding.split("<</>>");
					if (i < 10)
						name += i + "... " + info[0] + "<br>";
					else
						name += i + ". " + info[0] + "<br>";
					buff += "------------<br>";
					score += info[1] + "<br>";
				}// End if
				else
				{
					if (i < 10)
						name += i + "... " + "<br>";
					else
						name += i + ". " + "<br>";
					buff += "------------<br>";
				}// End if
			} // End while

			// Close the file (IMPORTANT)
			scanFile.close();
		}
		catch (FileNotFoundException e)
		{
			JOptionPane.showMessageDialog(null,
			"The following problem reading from a file occurred:\n" + e);
		} // End try/catch
		name += "</html>";
		buff += "</html>";
		score += "</html>";

		lblNames.setText(name);
		lblBuffer.setText(buff);
		lblScores.setText(score);
		lead.pack();

	}// End default constructor
}// End class
