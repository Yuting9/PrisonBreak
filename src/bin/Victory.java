package bin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Victory implements ActionListener, KeyListener
{
  JFrame frame  = new JFrame();
  int   pointD = PrisonBreak.game.pointD,
    pointC = PrisonBreak.game.pointC, pointT = (1100 - PrisonBreak.game.pointT) * 2,
    pointG = PrisonBreak.game.pointG, totP;
  JButton btnSubmit;
  
  Victory(boolean really)
  {
    frame.setBounds(300, 100, 600, 450);
    frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
    
    if (pointT < 0)
      pointT = 0;
    
    JPanel pnlVerdict = new JPanel();
    frame.getContentPane().add(pnlVerdict);
    
    JLabel lblVerdict = new JLabel("");
    pnlVerdict.add(lblVerdict);
    lblVerdict.setHorizontalAlignment(SwingConstants.CENTER);
    
    if (really)
    {
      lblVerdict.setIcon(new ImageIcon(Victory.class.getResource("/img/Victory0.png")));
    }
    
    else
    {
      pointT = 0;
      lblVerdict.setIcon(new ImageIcon(Victory.class.getResource("/img/Victory1.png")));
    }
    
    lblVerdict.setPreferredSize(new Dimension(128, 128));
    
    JPanel pnlScore = new JPanel();
    frame.getContentPane().add(pnlScore);
    pnlScore.setLayout(new BoxLayout(pnlScore, BoxLayout.Y_AXIS));
    
    JPanel pnlDonuts = new JPanel();
    pnlScore.add(pnlDonuts);
    pnlDonuts.setLayout(new BoxLayout(pnlDonuts, BoxLayout.X_AXIS));
    
    JLabel lblDonut = new JLabel("Donuts  = ");
    lblDonut.setToolTipText("The amount of Donuts you got");
    lblDonut.setForeground(new Color(50, 205, 50));
    pnlDonuts.add(lblDonut);
    
    JLabel lblSDon = new JLabel(Integer.toString(pointD));
    pnlDonuts.add(lblSDon);
    
    JPanel pnlCoffee = new JPanel();
    pnlScore.add(pnlCoffee);
    pnlCoffee.setLayout(new BoxLayout(pnlCoffee, BoxLayout.X_AXIS));
    
    JLabel lblCoffee = new JLabel("Coffee = ");
    lblCoffee.setToolTipText("The amount of times you got coffee");
    lblCoffee.setForeground(new Color(50, 205, 50));
    pnlCoffee.add(lblCoffee);
    
    JLabel lblSCof = new JLabel(Integer.toString(pointC));
    pnlCoffee.add(lblSCof);
    
    JPanel pnlGarb = new JPanel();
    pnlScore.add(pnlGarb);
    pnlGarb.setLayout(new BoxLayout(pnlGarb, BoxLayout.X_AXIS));
    
    JLabel lblGarbage = new JLabel("Garbage = ");
    lblGarbage.setToolTipText("The amount of times you hit a garbage can");
    lblGarbage.setForeground(new Color(255, 0, 0));
    pnlGarb.add(lblGarbage);
    
    JLabel lblSGarb = new JLabel(Integer.toString(pointG));
    pnlGarb.add(lblSGarb);
    
    JPanel pnlTime = new JPanel();
    pnlScore.add(pnlTime);
    pnlTime.setLayout(new BoxLayout(pnlTime, BoxLayout.X_AXIS));
    
    JLabel lblTime = new JLabel("Speed Bonus = ");
    lblTime.setToolTipText("The faster you are at catching the prisoner, the higher this score");
    lblTime.setForeground(new Color(50, 205, 50));
    pnlTime.add(lblTime);
    
    JLabel lblSTime = new JLabel(Integer.toString(pointT));
    pnlTime.add(lblSTime);
    
    JPanel pnlVictory = new JPanel();
    pnlScore.add(pnlVictory);
    pnlVictory.setLayout(new BoxLayout(pnlVictory, BoxLayout.X_AXIS));
    
    JLabel lblVictory = new JLabel("");
    lblVictory.setToolTipText("If you won or lost");
    pnlVictory.add(lblVictory);
    
    JLabel lblSVic = new JLabel("");
    pnlVictory.add(lblSVic);
    
    JPanel pnlTotal = new JPanel();
    pnlScore.add(pnlTotal);
    
    JLabel lblTotal = new JLabel("");
    lblTotal.setForeground(Color.BLUE);
    pnlTotal.add(lblTotal);
    
    if (really)
    {
      lblVictory.setForeground(new Color(50, 205, 50));
      lblVictory.setText("Victory = ");
      
      totP += 1000;
      lblSVic.setText("1000");
    }
    
    else
    {
      lblVictory.setForeground(new Color(255, 0, 0));
      lblVictory.setText("Failure = ");
      
      totP -= 500;
      lblSVic.setText("-500");
    }
    
    JPanel pnlName = new JPanel();
    frame.add(pnlName);
    JTextField name = new JTextField(10);
    pnlName.addKeyListener(this);
    pnlName.add(name);
    
    JPanel pnlSubmit = new JPanel();
    frame.add(pnlSubmit);
    btnSubmit = new JButton("Submit");
    btnSubmit.addActionListener(this);
    pnlSubmit.add(btnSubmit);
    
    totP += pointD + pointC + pointG + pointT;
    
    lblTotal.setText("Total = " + Integer.toString(totP));
    
    frame.pack();
    frame.setVisible(true);
  }
  
  public void actionPerformed(ActionEvent e)
  {
    if (e.getSource() == btnSubmit /*|| e.getSource() == enter button*/ )
    {
      
    }
  }
  
  @Override
  public void keyPressed(KeyEvent e)
  {
  }
 
 public void keyReleased(KeyEvent e)
 {
 }

 public void keyTyped(KeyEvent arg0)
 {
 }
}


