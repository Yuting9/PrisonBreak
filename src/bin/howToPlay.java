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

public class howToPlay
{
  howToPlay(final JFrame main)
  {
    final JFrame howTo = new JFrame("On the Run - How To Play");
    howTo.setLayout(new BorderLayout() );
    howTo.setVisible(true);
    howTo.setBounds(300,100,700,550);
    
    JPanel pnlInstructions = new JPanel();
    pnlInstructions.setLayout(new GridLayout(2, 2) );
    howTo.add(pnlInstructions);
    
    JLabel lblTitle = new JLabel("How To Play");
    lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
    howTo.add(lblTitle, BorderLayout.NORTH);
    
    JPanel pnlObjective = new JPanel();
    pnlObjective.setLayout(new GridLayout(2, 1) );
    JLabel lblObjective = new JLabel("The objective of the game is to catch the prisoner.");
    JLabel prisonerImage = new JLabel();
    ImageIcon icon = new ImageIcon(PrisonBreak.class.getResource("/img/prisoner1.png")); 
    prisonerImage.setIcon(icon);
    pnlObjective.add(lblObjective);
    pnlObjective.add(prisonerImage);
    pnlInstructions.add(pnlObjective);
    
    JLabel lblJump = new JLabel("You will use the spacebar to jump.");
    pnlInstructions.add(lblJump);
    
    JLabel lblDonut = new JLabel("slow");
    pnlInstructions.add(lblDonut);
    
    JLabel lblCoffee = new JLabel("fast");
    pnlInstructions.add(lblCoffee);
    
    JPanel pnlBtn = new JPanel();
    howTo.add(pnlBtn, BorderLayout.SOUTH);
    pnlBtn.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
    
    JButton btnRtn = new JButton("Return to Menu");
    btnRtn.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) 
      {
        main.setVisible(true);
        howTo.dispose();
      }
    });
    pnlBtn.add(btnRtn);
  }
}
