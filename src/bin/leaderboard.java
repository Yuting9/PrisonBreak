package bin;
import java.awt.BorderLayout;
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
import javax.swing.BoxLayout;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;

public class leaderboard 
{
 final JFrame lead = new JFrame("On the Run - Leaderboards");
 leaderboard(final JFrame main){
  lead.getContentPane().setLayout(new BoxLayout(lead.getContentPane(), BoxLayout.X_AXIS));
  lead.setVisible(true);
  lead.setBounds(300,100,600,450);
  
<<<<<<< HEAD
<<<<<<< HEAD
		try {
			in = new FileInputStream("lead.txt");
			//in.read();
		} catch (FileNotFoundException e) {
			File leadFile = new File("/lead.txt");
			e.printStackTrace();
		}
=======
  JPanel pnlTime = new JPanel();
  lead.getContentPane().add(pnlTime);
  pnlTime.setLayout(new BorderLayout(0, 0));
>>>>>>> parent of 2b83bec... Minor Edits to Formatting
=======
  JPanel pnlTime = new JPanel();
  lead.getContentPane().add(pnlTime);
  pnlTime.setLayout(new BorderLayout(0, 0));
>>>>>>> parent of 2b83bec... Minor Edits to Formatting
  
  JLabel lblHiScore = new JLabel("Leaderboards - High Score");
  lblHiScore.setHorizontalAlignment(SwingConstants.CENTER);
  pnlTime.add(lblHiScore, BorderLayout.NORTH);
  
  JLabel lblScores = new JLabel("");
  pnlTime.add(lblScores, BorderLayout.CENTER);
  
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
  
 }
}
