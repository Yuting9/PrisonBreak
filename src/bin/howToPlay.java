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
    howTo.getContentPane().setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 18));
    howTo.setVisible(true);
    howTo.setBounds(300,100,700,450);
    howTo.getContentPane().setLayout(new BoxLayout(howTo.getContentPane(), BoxLayout.Y_AXIS));
    
    JPanel panel_1 = new JPanel();
    howTo.getContentPane().add(panel_1);
    panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));
    
    JLabel lblTitle = new JLabel("How To Play");
    panel_1.add(lblTitle);
    lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
    lblTitle.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 18));
    
    JPanel pnlInstructions = new JPanel();
    howTo.getContentPane().add(pnlInstructions);
    
    JPanel pnlObjective = new JPanel();
    pnlObjective.setLayout(new GridLayout(2, 1) );
    JLabel lblObjective = new JLabel("The objective of the game is to catch the prisoner.");
    lblObjective.setHorizontalAlignment(SwingConstants.CENTER);
    JLabel prisonerImg = new JLabel();
    prisonerImg.setHorizontalAlignment(SwingConstants.CENTER);
    ImageIcon objectiveIcon; 
    pnlInstructions.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
    prisonerImg.setIcon(new ImageIcon(howToPlay.class.getResource("/img/prisoner0.png")));
    pnlObjective.add(lblObjective);
    pnlObjective.add(prisonerImg);
    pnlInstructions.add(pnlObjective);
    
    JPanel pnlJump = new JPanel();
    JLabel lblJump = new JLabel("You will use the spacebar to jump.");
    ImageIcon jumpIcon;
    pnlJump.setLayout(new BoxLayout(pnlJump, BoxLayout.X_AXIS));
    pnlJump.add(lblJump);
    
    JPanel panel = new JPanel();
    pnlJump.add(panel);
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    
    JLabel label = new JLabel();
    label.setIcon(new ImageIcon(howToPlay.class.getResource("/img/copjump0.png")));
    panel.add(label);
    
    JLabel lblNewLabel = new JLabel("");
    lblNewLabel.setIcon(new ImageIcon(howToPlay.class.getResource("/img/copjump1.png")));
    panel.add(lblNewLabel);
    pnlInstructions.add(pnlJump);
    
    JPanel pnlDonut = new JPanel();
    ImageIcon donutIcon = new ImageIcon(PrisonBreak.class.getResource("/img/donut0.png"));
    pnlDonut.setLayout(new BoxLayout(pnlDonut, BoxLayout.Y_AXIS));
    pnlInstructions.add(pnlDonut);
    
    JPanel panel_2 = new JPanel();
    pnlDonut.add(panel_2);
    JLabel lblDonut = new JLabel("Donuts will slow you down.");
    panel_2.add(lblDonut);
    lblDonut.setHorizontalAlignment(SwingConstants.CENTER);
    
    JPanel panel_3 = new JPanel();
    pnlDonut.add(panel_3);
    JLabel donutImg = new JLabel();
    panel_3.add(donutImg);
    donutImg.setHorizontalAlignment(SwingConstants.CENTER);
    donutImg.setIcon(donutIcon);
    
    JPanel pnlCoffee = new JPanel();
    ImageIcon coffeeIcon = new ImageIcon(PrisonBreak.class.getResource("/img/coffee.png"));
    pnlCoffee.setLayout(new BoxLayout(pnlCoffee, BoxLayout.Y_AXIS));
    pnlInstructions.add(pnlCoffee);
    
    JPanel panel_4 = new JPanel();
    pnlCoffee.add(panel_4);
    JLabel lblCoffee = new JLabel("Coffee will speed you up.");
    panel_4.add(lblCoffee);
    lblCoffee.setHorizontalAlignment(SwingConstants.CENTER);
    
    JPanel panel_5 = new JPanel();
    pnlCoffee.add(panel_5);
    JLabel coffeeImg = new JLabel();
    panel_5.add(coffeeImg);
    coffeeImg.setHorizontalAlignment(SwingConstants.CENTER);
    coffeeImg.setIcon(coffeeIcon);
    
    JPanel pnlBtn = new JPanel();
    howTo.getContentPane().add(pnlBtn);
    
    JButton btnRtn = new JButton("Return to Menu");
    btnRtn.setVerticalAlignment(SwingConstants.BOTTOM);
    btnRtn.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) 
      {
        main.setVisible(true);
        howTo.dispose();
      }
    });
    pnlBtn.setLayout(new BoxLayout(pnlBtn, BoxLayout.X_AXIS));
    pnlBtn.add(btnRtn);
  }
}
