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

public class PrisonBreak implements ActionListener
{
	JFrame main = new JFrame("Prison Break - Main Menu");
	
	public PrisonBreak(){
		main.setBounds(300,100,600,450);
	    main.getContentPane().setLayout(new BorderLayout(0, 0));
	    
	    JPanel namePnl = new JPanel();
	    main.getContentPane().add(namePnl, BorderLayout.NORTH);
	    
	    JPanel centerPnl = new JPanel();
	    main.getContentPane().add(centerPnl, BorderLayout.CENTER);
	    
	    JPanel btnPnl = new JPanel();
	    main.getContentPane().add(btnPnl, BorderLayout.SOUTH);
	    btnPnl.setLayout(new GridLayout(1, 3));
	    btnPnl.setPreferredSize(new Dimension(0, 40));
	    
	    JButton btnNewGame = new JButton("New Game");
	    btnNewGame.addActionListener(this);
	    btnNewGame.setPreferredSize(new Dimension());
	    btnPnl.add(btnNewGame);
	   
	    JButton btnHowToPlay = new JButton("How to Play");
	    btnHowToPlay.addActionListener(this);
	    btnPnl.add(btnHowToPlay);
	    
	    JButton btnLeaderboards = new JButton("Leaderboards");
	    btnLeaderboards.addActionListener(this);
	    btnLeaderboards.setAlignmentX(Component.CENTER_ALIGNMENT);
	    btnPnl.add(btnLeaderboards);
	    
	    JLabel lblTitle = new JLabel("On The Run");
	    lblTitle.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 38));
	    namePnl.add(lblTitle);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(PrisonBreak.class.getResource("/img/mainMenuIcon.jpg")));
		centerPnl.add(lblNewLabel);
		
		main.setResizable(false);
		main.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
  public static void main(String[] args) 
  {
	  PrisonBreak game = new PrisonBreak();
  }
}
