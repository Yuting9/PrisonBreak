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
    
    //if (page == 0)
    //{
      pnlHelp[page] = new JPanel();
      JLabel image = new JLabel (new ImageIcon (howToPlay.class.getResource("/img/HowToPlayPage" + page + ".png") ) );
      pnlHelp[page].add(image);
      howTo.getContentPane().add(pnlHelp[page]);
      System.out.println("one");
    /*}
    
    else if (page == 1)
    {
      pnlHelp[1] = new JPanel();
      JLabel image2 = new JLabel (new ImageIcon(howToPlay.class.getResource("/img/HowToPlayPage2.png")));
      pnlHelp[1].add(image2);
      howTo.getContentPane().add(pnlHelp[1]);
      System.out.println("two");
    }*/ 
    
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
    System.out.println(page);
    btnBack.setEnabled(true);
    if(page >= pnlHelp.length){
     btnNext.setEnabled(false);
    }
   }
   if(e.getSource() == btnBack){
    page--;
    System.out.println(page);
    btnNext.setEnabled(true);
    if(page == 0){
     btnBack.setEnabled(false);
    }
   }
  }
}
