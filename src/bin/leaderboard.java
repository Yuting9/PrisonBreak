package bin;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import java.awt.FlowLayout;
import java.io.*;
import java.util.Scanner;

public class leaderboard 
{
	File file = new File("lead.info");
	String name = new String("<html>");
	String buff = new String("<html>");
	String score = new String("<html>");
	String holding = new String();
	String[] info = new String[3];
	final JFrame lead = new JFrame("On the Run - Leaderboards");
	
	leaderboard(final JFrame main) throws IOException{
		lead.getContentPane().setLayout(new BoxLayout(lead.getContentPane(), BoxLayout.X_AXIS));
		lead.setVisible(true);
		lead.setBounds(300,100,275,450);

	    JPanel pnlTime = new JPanel();
	    lead.getContentPane().add(pnlTime);
	    pnlTime.setLayout(new BorderLayout(0, 0));
	    
		JLabel lblHiScore = new JLabel("<html>Leaderboards<br>---------------------------------------</html>");
	    lblHiScore.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 32));
		lblHiScore.setHorizontalAlignment(SwingConstants.CENTER);
		pnlTime.add(lblHiScore, BorderLayout.NORTH);
		
        
		JPanel pnlBtn = new JPanel();
		pnlTime.add(pnlBtn, BorderLayout.SOUTH);
		pnlBtn.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
  
		JButton btnRtn = new JButton("Return to Menu");
		btnRtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				main.setVisible(true);
				lead.dispose();
			}
		});
		pnlBtn.add(btnRtn);
		
		JPanel panel = new JPanel();
		pnlTime.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNames = new JLabel();
		lblNames.setHorizontalAlignment(SwingConstants.TRAILING);
		//lblNames.setBorder(BorderFactory.createLineBorder(Color.black));
		panel.add(lblNames, BorderLayout.WEST);
	    lblNames.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 12));
		
		JLabel lblBuffer = new JLabel();
		//lblBuffer.setBorder(BorderFactory.createLineBorder(Color.black));
		panel.add(lblBuffer, BorderLayout.CENTER);
	    lblBuffer.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 12));

		JLabel lblScores = new JLabel();
		//lblScores.setBorder(BorderFactory.createLineBorder(Color.blue));
		panel.add(lblScores, BorderLayout.EAST);
		lblScores.setHorizontalAlignment(SwingConstants.LEADING);
	    lblScores.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 12));
		

		file.createNewFile();
        //Attempt to open and read the file
        try
        {
             //Create a file Scanner
             Scanner scanFile = new Scanner(new File("lead.info"));
             
             //Keep going until the end of the file
             for(int i = 1; i<=20; i++)
             {
            	 if(scanFile.hasNextLine())
            	 {
            		 //read the current line
		             holding = scanFile.nextLine();
		             info = holding.split("<</>>");
		             System.out.println(info[0]);
		             System.out.println(info[1]);
		             if(i<10)
            			 name += i + "... " + info[0] + "<br>";
            		 else
            			 name += i + ". " + info[0] + "<br>";
		             buff += "------------<br>";
		             score += info[1] + "<br>";
            	 }
            	 else{
            		 if(i<10)
            			 name += i + "... " + "<br>";
            		 else
            			 name += i + ". " + "<br>";
		             buff += "------------<br>";
		             score += 0 + "<br>";
            	 }
             }//end while
             
             //Close the file (IMPORTANT)
             scanFile.close();
        }
        catch(FileNotFoundException e)
        {
             System.out.println("The following problem reading from a file occurred:\n" + e);
        }//end try/catch
		name += "</html>";
		buff += "</html>";
		score += "</html>";
  
		lblNames.setText(name);
		System.out.println("lenlen"+lblNames.getWidth());
		lblBuffer.setText(buff);
		lblScores.setText(score);
		lead.pack();
		
	}
}
