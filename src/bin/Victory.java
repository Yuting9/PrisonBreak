package bin;

import javax.swing.*;
import java.awt.*;

public class Victory
{
	JFrame frame = new JFrame();
	Victory(boolean really)
	{
		frame.setBounds(300, 100, 600, 450);
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
		
		JPanel pnlVerdict = new JPanel();
		frame.getContentPane().add(pnlVerdict);
		
		JLabel lblVerdict = new JLabel("");
		pnlVerdict.add(lblVerdict);
		lblVerdict.setHorizontalAlignment(SwingConstants.CENTER);
		lblVerdict.setIcon(new ImageIcon(Victory.class.getResource("/img/Victory0.png")));
		lblVerdict.setPreferredSize(new Dimension(128, 128));
		
		JPanel pnlScore = new JPanel();
		frame.getContentPane().add(pnlScore);
		pnlScore.setLayout(new BoxLayout(pnlScore, BoxLayout.X_AXIS));
		
		JLabel lblReason = new JLabel("New label");
		pnlScore.add(lblReason);
		
		JLabel lblScore = new JLabel("<html>");
		pnlScore.add(lblScore);
		frame.pack();
		frame.setVisible(true);
	}
}
