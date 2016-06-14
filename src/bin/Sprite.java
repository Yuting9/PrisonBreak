package bin;

// Import ImageIcon
import javax.swing.ImageIcon;

// Sprite class
public class Sprite
{
 ImageIcon image = null;
 int x = 700, y = 0, i = 0;
 
 Sprite(int i){
  x = i;
 }
 
 // Returns the image
 public ImageIcon getImage()
 {
  return image;
 }// End getImage
 
 // Gets the x value
 public int getX()
 {
  return x;
 }// End getX
 
 // Gets the y value
 public int getY()
 {
  return y;
 }// End getY
 
 // Sets the x value
 public void setX(int set)
 {
  x = set;
 }// End setX
 
 // Sets the y value
 public void setY(int set)
 {
  y = set;
 }// End setY
 
 public void move(int dist){
  x-=dist;
 }// End move
 
 // Regenerates the sprite
 public void reGen(boolean urb)
 {
  if(urb)
  {
   image = new ImageIcon(PrisonBreak.class.getResource(
    "/img/UrbanBuilding" + (int) (Math.random() * 3) + ".png")
   );
  }// End if
  else
  {
   image = new ImageIcon(PrisonBreak.class.getResource(
    "/img/Rural" + (int) (Math.random() * 5) + ".png")
   );
  }// End else
  x = 1000;
  y = 100;
 }// End reGen
}// End Sprite
