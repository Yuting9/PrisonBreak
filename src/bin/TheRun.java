package bin;

import javax.swing.*;
import java.awt.event.*;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;

public class TheRun extends JPanel implements ActionListener, KeyListener
{
 JFrame   frame    = new JFrame();
 Donut donut = new Donut();
 Character  player   = new Character(false),
       prison   = new Character(true);
 boolean   preGame   = true,
             pressHold  = false, 
             pressedOnce = false, 
             isUrban   = true;
 int     plTime   = 0,
         plTimeDonut = 0,
             prTime    = 0, 
             x      = 720, 
             change, 
             spd;
 JLabel   background = new JLabel();
 Timer    clock    = new Timer(40, this);
 String   playerMode = "run",
             prisonMode  = "run";
 ImageIcon[] bg     = new ImageIcon[50];
 DrawPanel  panel    = new DrawPanel(player.getImage(0), prison.getImage(0), donut.getImage(0));
 //DrawPanel pnlDonut = new DrawPanel(donut.getImage(0));

 public TheRun()
 {
  //donut = Donut.getDonut(k);
  for (int t = 0; t < 50; t++)
  {
   bg[t] = new ImageIcon(PrisonBreak.class.getResource("/img/UrbanBuilding" + ((int) (Math.random() * 3)) + ".png"));
  }

  for (int r = 0; r < 50; r++)
  {
   bg[r] = new ImageIcon(PrisonBreak.class.getResource("/img/Rural" + ((int) (Math.random() * 3)) + ".png"));
  }

  frame.addKeyListener(this);
  frame.setFocusable(true);
  frame.setResizable(false);
  frame.setBounds(100, 100, 900, 500);
  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  frame.getContentPane().add(panel);
  frame.getContentPane().add(panel);
  frame.setVisible(true);
  clock.start();
  frame.getContentPane().add(this);
  frame.setVisible(true);
  prison.setX(600);
 }

 @Override
 public void actionPerformed(ActionEvent e)
 {
  playerMode = player.getMode();
  prisonMode = prison.getMode();
  clock.setDelay(1000);
  if (preGame)
  {
   // Display countdown
   if (panel.countDown <= 0)
   {
    preGame = false;
    plTime++;
    clock.setDelay(50);
   } else if (panel.countDown <= 1)
   {
    clock.setDelay(250);
   }
   panel.updateDown();
  } else
  {
    panel.setDonutImage(donut.getImage(plTimeDonut));
    plTimeDonut++;
    if (plTimeDonut == 4)
    {
     plTimeDonut = 0;
    }
   spd = player.getSpd();
   clock.setDelay(spd);
   if (spd >= 60)
   {
    change = 50 - (spd / 10);
    player.setSpd(spd -= (spd / 10));
   }
   if (playerMode.equals("run"))
   {
    // d-=(d/10);
    panel.setCopImage(player.getImage(plTime));
    // donut = Donut.getDonut(k);
    // this.remove(holder);
    plTime++;
    if (plTime == 6)
    {
     plTime = 0;
    }
   } else if (playerMode.equals("jump"))
   {
    // clock.setDelay(500);
    plTime = 0;
    if (player.getVert() >= 0)
    {
     panel.setCopImage(player.getImage(0));
    } else
    {
     panel.setCopImage(player.getImage(1));
    }
   } else if (playerMode.equals("roll"))
   {
    pressedOnce = false;
    player.isNotReleased();
    if (plTime == 3)
    {
     player.doRun();
    }
    panel.setCopImage(player.getImage(plTime));
    plTime++;
   }
   if (prisonMode.equals("run"))
   {
    if (prTime < 5)
    {
     prTime++;
     panel.setPrisImage(prison.getImage(prTime));
     prTime--;
    } else
    {
     panel.setPrisImage(prison.getImage(0));
    }

    prTime++;
    if (prTime == 6)
    {
     prTime = 0;
    }
   } else if (prisonMode.equals("jump"))
   {
    if (prison.getVert() > 0)
    {
     panel.setCopImage(prison.getImage(0));
    } else
    {
     panel.setCopImage(prison.getImage(1));
    }

   }
   panel.update(change);
   panel.updatePrison(0);
   panel.updateDown();
   revalidate();
   if (prison.getX() - 100 <= player.getX())
   {
    win();
   }
  }
 }

 private void win()
 {
  frame.dispose();
  clock.stop();
  Victory win = new Victory(true);
 }

 private void lose()
 {
  frame.dispose();
  clock.stop();
  Victory win = new Victory(false);
 }

 class DrawPanel extends JPanel
 {
  ImageIcon cop, prisoner, donut;
  String  mode   = "run";
  int    countDown = 4;

  public DrawPanel(ImageIcon firstCop, ImageIcon firstPris, ImageIcon donut)
  {
   cop = firstCop;
   prisoner = firstPris;
   donut = donut;
  }

  public void updateDown()
  {
   countDown--;
  }

  public void updatePrison(int change)
  {
   prison.setX(prison.getX() - change - Character.getDiff());
  }

  public void update(int diff)
  {
   x -= diff;
  }

  public void setCopImage(ImageIcon newCop)
  {
   cop = newCop;
   if (player.getMode().equals("jump"))
   {
    player.jump();
   }
  }
  
  public void setDonutImage(ImageIcon newDonut)
  {
   donut = newDonut;
  }

  public void setPrisImage(ImageIcon newPris)
  {
   prisoner = newPris;
  }

  private void paintCount(Graphics g)
  {
   if (countDown > 0)
   {
    g.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 100));
    g.drawString(Integer.toString(countDown), 150, 300);
   } else if (countDown == 0)
   {
    g.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 200));
    g.drawString("Go!", 150, 300);
   }
  }

  private void paintBack(Graphics g)
  {
   g.setColor(new Color(0, 128, 231));
   g.fillRect(0, 0, 1000, 600);

   if (isUrban)
   {
    for (int p = 0; p < 50; p++)
    {
     bg[p].paintIcon(this, g, x + (p * 200), 100);
    }
    isUrban = false;
   }

   else
   {
    for (int q = 0; q < 50; q++)
    {
     bg[q].paintIcon(this, g, x + (q * 270), 200);
    }
   }

   g.setColor(Color.darkGray);
   g.fillRect(0, 450, 1000, 100);
  }

  private void paintCop(Graphics g)
  {
   if (player.getMode().equals("jump"))
   {
    cop.paintIcon(this, g, 50, player.getHeight());
   }
   cop.paintIcon(this, g, 50, player.getHeight());
  }

  private void paintPrison(Graphics g)
  {
   prisoner.paintIcon(this, g, prison.getX(), 338);
  }
  
  private void paintDonut(Graphics g)
  {
    //if (donut != null)
    //{
   donut.paintIcon(this, g, x, 100);
    //}
  }

  public void paintComponent(Graphics g)
  {
   super.paintComponent(g);
   paintDonut(g);
   paintBack(g);
   paintCop(g);
   paintPrison(g);
   paintCount(g);
   repaint();
  }
 }

 @Override
 public void keyPressed(KeyEvent e)
 {
  // TODO Auto-generated method stub
  if (e.getKeyCode() == 39)
  {
   Character.addDiff(5);
  } else if (e.getKeyCode() == 37)
  {
   Character.addDiff(-5);
  }

  else if (e.getKeyCode() == 32)
  {
   if (!pressHold && !pressedOnce && spd == 54)
   {
    ;
    player.doJump();
    player.setVert(20);
    pressHold = true;
    pressedOnce = true;
   }
  }
 }

 @Override
 public void keyReleased(KeyEvent e)
 {
  if (e.getKeyCode() == 32 && spd == 54)
  {
   System.out.println("Space-Released");
   pressHold = false;
   player.isReleased();
  }
 }

 public void keyTyped(KeyEvent arg0)
 {
 }
}