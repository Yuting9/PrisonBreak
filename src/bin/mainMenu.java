package bin;
import javax.swing.*;
import java.awt.*;

public class mainMenu {
	JFrame main = new JFrame("Prison Break - Main Menu");
	
	public mainMenu(){
		main.setBounds(0,0,600,500);
		main.getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel namePnl = new JPanel();
		main.getContentPane().add(namePnl, BorderLayout.NORTH);

		JPanel centerPnl = new JPanel();
		main.getContentPane().add(centerPnl, BorderLayout.CENTER);
		
		JPanel btnPnl = new JPanel();
		main.getContentPane().add(btnPnl, BorderLayout.SOUTH);
		btnPnl.setLayout(new BoxLayout(btnPnl, BoxLayout.Y_AXIS));
		
		JButton btnNewGame = new JButton("New Game");
		btnNewGame.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnPnl.add(btnNewGame);
		
		JButton btnHowToPlay = new JButton("How to Play");
		btnHowToPlay.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnPnl.add(btnHowToPlay);
		
		JButton btnLeaderboards = new JButton("Leaderboards");
		btnLeaderboards.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnPnl.add(btnLeaderboards);
		
		JLabel lblTitle = new JLabel("Prison Break");
		lblTitle.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 38));
		namePnl.add(lblTitle);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("mainMenuIcon.jpg"));
		centerPnl.add(lblNewLabel);
		
		main.setResizable(false);
		main.setVisible(true);
	}
}
