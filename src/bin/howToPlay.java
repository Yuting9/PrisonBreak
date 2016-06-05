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
    JLabel prisonerImg = new JLabel();
    ImageIcon objectiveIcon = new ImageIcon(PrisonBreak.class.getResource("/prisoner1.png")); 
    prisonerImg.setIcon(objectiveIcon);
    pnlObjective.add(lblObjective);
    pnlObjective.add(prisonerImg);
    pnlInstructions.add(pnlObjective);
    
    JPanel pnlJump = new JPanel();
    pnlJump.setLayout(new GridLayout(2, 1) );
    JLabel lblJump = new JLabel("You will use the spacebar to jump.");
    JLabel jumpImg = new JLabel();
    ImageIcon jumpIcon = new ImageIcon(PrisonBreak.class.getResource("/prisoner1.png")); 
    jumpImg.setIcon(jumpIcon);
    pnlJump.add(lblJump);
    pnlJump.add(jumpImg);
    pnlInstructions.add(pnlJump);
    
    JPanel pnlDonut = new JPanel();
    pnlDonut.setLayout(new GridLayout(2, 1) );
    JLabel lblDonut = new JLabel("Donuts will slow you down.");
    JLabel donutImg = new JLabel();
    ImageIcon donutIcon = new ImageIcon(PrisonBreak.class.getResource("/prisoner1.png"));
    donutImg.setIcon(donutIcon);
    pnlDonut.add(lblDonut);
    pnlDonut.add(donutImg);
    pnlInstructions.add(pnlDonut);
    
    JPanel pnlCoffee = new JPanel();
    pnlCoffee.setLayout(new GridLayout(2, 1) );
    JLabel lblCoffee = new JLabel("Coffee will speed you up.");
    JLabel coffeeImg = new JLabel();
    ImageIcon coffeeIcon = new ImageIcon(PrisonBreak.class.getResource("/coffee.png"));
    coffeeImg.setIcon(coffeeIcon);
    pnlCoffee.add(lblCoffee);
    pnlCoffee.add(coffeeImg);
    pnlInstructions.add(pnlCoffee);
    
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
