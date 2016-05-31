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
	JFrame lead = new JFrame("On the Run - Leaderboards");
	leaderboard(JFrame main){
		lead.getContentPane().setLayout(new BoxLayout(lead.getContentPane(), BoxLayout.X_AXIS));
		
		JPanel pnlTime = new JPanel();
		lead.getContentPane().add(pnlTime);
		pnlTime.setLayout(new BorderLayout(0, 0));
		
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
