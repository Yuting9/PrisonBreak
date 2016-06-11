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

public class howToPlay implements ActionListener
{
	JButton btnRtn, btnNext, btnBack;
	final JFrame howTo, goBack;
	JPanel[] pnlHelp = new JPanel[2];
	int page = 0;
	
  howToPlay(final JFrame main)
  {
  	goBack = main;
    howTo = new JFrame("On the Run - How To Play");
    howTo.getContentPane().setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 18));
    howTo.setVisible(true);
    howTo.setBounds(300,100,700,450);
    howTo.getContentPane().setLayout(new BoxLayout(howTo.getContentPane(), BoxLayout.Y_AXIS));
    
    JPanel pnlName = new JPanel();
    howTo.getContentPane().add(pnlName);
    pnlName.setLayout(new BoxLayout(pnlName, BoxLayout.X_AXIS));
    
    JLabel lblTitle = new JLabel("How To Play");
    pnlName.add(lblTitle);
    lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
    lblTitle.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 18));
    
    pnlHelp[0] = new JPanel();
    howTo.getContentPane().add(pnlHelp[page]);
    
    JPanel pnlObjective = new JPanel();
    pnlObjective.setLayout(new GridLayout(2, 1) );
    JLabel lblObjective = new JLabel("The objective of the game is to catch the prisoner.");
    lblObjective.setHorizontalAlignment(SwingConstants.CENTER);
    JLabel prisonerImg = new JLabel();
    prisonerImg.setHorizontalAlignment(SwingConstants.CENTER);
    ImageIcon objectiveIcon; 
    pnlHelp[0].setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
    prisonerImg.setIcon(new ImageIcon(howToPlay.class.getResource("/img/prisoner0.png")));
    pnlObjective.add(lblObjective);
    pnlObjective.add(prisonerImg);
    pnlHelp[0].add(pnlObjective);
    
    JPanel pnlJump = new JPanel();
    JLabel lblJump = new JLabel("You will use the spacebar to jump. \n Press longer to jump higher");
    ImageIcon jumpIcon;
    pnlJump.setLayout(new BoxLayout(pnlJump, BoxLayout.X_AXIS));
    pnlJump.add(lblJump);
    
    JPanel pnlJumpImgs = new JPanel();
    pnlJump.add(pnlJumpImgs);
    pnlJumpImgs.setLayout(new BoxLayout(pnlJumpImgs, BoxLayout.Y_AXIS));
    
    JLabel label = new JLabel();
    label.setIcon(new ImageIcon(howToPlay.class.getResource("/img/copjump0.png")));
    pnlJumpImgs.add(label);
    
    JLabel lblNewLabel = new JLabel("");
    lblNewLabel.setIcon(new ImageIcon(howToPlay.class.getResource("/img/copjump1.png")));
    pnlJumpImgs.add(lblNewLabel);
    pnlHelp[0].add(pnlJump);
    
    JPanel pnlDonut = new JPanel();
    ImageIcon donutIcon = new ImageIcon(PrisonBreak.class.getResource("/img/donut0.png"));
    pnlDonut.setLayout(new BoxLayout(pnlDonut, BoxLayout.Y_AXIS));
    pnlHelp[0].add(pnlDonut);
    
    JPanel pnlDonutTxt = new JPanel();
    pnlDonut.add(pnlDonutTxt);
    JLabel lblDonut = new JLabel("Doughnuts will slow you down.");
    pnlDonutTxt.add(lblDonut);
    lblDonut.setHorizontalAlignment(SwingConstants.CENTER);
    
    JPanel pnlDonutImg = new JPanel();
    pnlDonut.add(pnlDonutImg);
    JLabel donutImg = new JLabel();
    pnlDonutImg.add(donutImg);
    donutImg.setHorizontalAlignment(SwingConstants.CENTER);
    donutImg.setIcon(donutIcon);
    
    JPanel pnlCoffee = new JPanel();
    ImageIcon coffeeIcon = new ImageIcon(PrisonBreak.class.getResource("/img/coffee.png"));
    pnlCoffee.setLayout(new BoxLayout(pnlCoffee, BoxLayout.Y_AXIS));
    pnlHelp[0].add(pnlCoffee);
    
    JPanel pnlCoffeeTxt = new JPanel();
    pnlCoffee.add(pnlCoffeeTxt);
    JLabel lblCoffee = new JLabel("Coffee will speed you up.");
    pnlCoffeeTxt.add(lblCoffee);
    lblCoffee.setHorizontalAlignment(SwingConstants.CENTER);
    
    JPanel pnlCoffeeImg = new JPanel();
    pnlCoffee.add(pnlCoffeeImg);
    JLabel coffeeImg = new JLabel();
    pnlCoffeeImg.add(coffeeImg);
    coffeeImg.setHorizontalAlignment(SwingConstants.CENTER);
    coffeeImg.setIcon(coffeeIcon);
    
    pnlHelp[1] = new JPanel();
    
    
    
    JPanel pnlBtn = new JPanel();
    howTo.getContentPane().add(pnlBtn);
    pnlBtn.setLayout(new BoxLayout(pnlBtn, BoxLayout.X_AXIS));
    
    btnBack = new JButton("Back");
    btnBack.addActionListener(this);
    btnBack.setEnabled(false);
    pnlBtn.add(btnBack);
    
    btnRtn = new JButton("Return to Menu");
    btnRtn.addActionListener(this);
    pnlBtn.add(btnRtn);
    
    btnNext = new JButton("Next");
    btnNext.addActionListener(this);
    pnlBtn.add(btnNext);
  }
  
  public void actionPerformed(ActionEvent e){
  	if(e.getSource() == btnRtn){
  		goBack.setVisible(true);
  		howTo.dispose();
  	}
  	if(e.getSource() == btnNext){
  		page++;
  		btnBack.setEnabled(true);
  		howTo.pack();
  		if(page >= pnlHelp.length){
  			btnNext.setEnabled(false);
  		}
  	}
  	if(e.getSource() == btnBack){
  		page--;
  		btnNext.setEnabled(true);
  		if(page == 0){
  			btnBack.setEnabled(false);
  		}
  	}
  }
}
