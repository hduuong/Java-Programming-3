import java.awt.*;
/**
 * a shape interface
 * 
 * @author Duong Chau
 * @version HW2
 */ 
public interface AbstractShape{
  /**
   * shift the location of the shape
   * @param deltaX new x coordinate for the shape
   * @param deltaY new y coordinate for the shape
   */ 
  public void shiftBy(int deltaX, int deltaY);
  /**
   * move the location of the shape
   * @param deltaX new x coordinate for the shape
   * @param deltaY new y coordinate for the shape
   */
  public void moveTo(int newX, int newY);
  /**
   * a toString method the display the properties of the object
   * @return a string represents the object
   */ 
  public String toString();
  /**
   * change the size of the shape
   * @param aSize a new size for the shape
   */ 
  public void changeHeight (int aHeight);
  /**
   * change the color of the shape
   * @param aColor a new Color for the shape
   */ 
  public void changeColor (Color aColor);
  /**
   * get the x-coordinate of the shape
   * @return an int type x-coordinate
   */ 
  public int getX();
  /**
   * get the y-coordinate of the shape
   * @return an int type y-coordinate
   */ 
  public int getY();
  /**
   * get the height of the shape
   * @return the height of the shape
   */ 
  public int getHeight();
  /**
   * get the color of the shape
   * @return the color of the shape
   */ 
  public Color getColor();
  /**
   * set the shape to be selected
   * @param b true or false
   */ 
  public void setSelected(boolean b);
  /**
   * check if the shape is selected
   * @return true or false
   */ 
  public boolean isSelected();
  /**
   * check if a shape is on a given point
   * @return true some point (x,y) is on the Shape, false if not.
   */
  public boolean isOn(int x, int y);
  /**
   * paint method, draw the shape
   * @param g a Graphic object
   */ 
  public void drawShape(Graphics g);
}