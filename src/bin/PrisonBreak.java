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
	JFrame main = new JFrame("On the Run - Main Menu");
	JButton btnNew,btnLead,btnHow;
	
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
	    
	    btnNew = new JButton("New Game");
	    btnNew.addActionListener(this);
	    btnNew.setPreferredSize(new Dimension());
	    btnPnl.add(btnNew);
	   
	    btnHow = new JButton("How to Play");
	    btnHow.addActionListener(this);
	    btnPnl.add(btnHow);
	    
	    btnLead = new JButton("Leaderboards");
	    btnLead.addActionListener(this);
	    btnLead.setAlignmentX(Component.CENTER_ALIGNMENT);
	    btnPnl.add(btnLead);
	    
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
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnNew)
		{
			
		}
		if(e.getSource() == btnHow)
		{
			
		}
		if(e.getSource() == btnLead)
		{
			main.setVisible(false);
			leaderboard second = new leaderboard(main);
		}
	}
  public static void main(String[] args) 
  {
	  PrisonBreak game = new PrisonBreak();
  }
}
