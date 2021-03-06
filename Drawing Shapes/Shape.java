import java.awt.*;
/**
 * an abstract base class
 * contains all of the methods that share among the 2 sub class 
 * and abstract methods that will be overrided
 * 
 * @author Duong H Chau
 * @version HW2
 */ 
public abstract class Shape implements AbstractShape{
  private Point point;
  private int height;
  private Color color;
  private boolean select;
  
  private static final int minHeight = 10;
  /**
   * get the minimun height of a shape
   * @return the minimun height
   */ 
  public int getMin(){
    return minHeight;
  }
  /**
   * Constructor that instantiate the shape
   * @param x1 x-coordinate of the shape 
   * @param y1 y-coordiante of the shape
   * @param dimension the size of the shape
   * @param rgb the color of the shape 
   */ 
  public Shape (Point p, int dimension, Color rgb){
    if(dimension < minHeight) throw new IllegalArgumentException("the size is too small");
    point = p;
    height = dimension;
    color = rgb;
  }
  /**
   * abstract method that require the sub class to have
   * @return true some point (x,y) is on the Shape, false if not.
   */
  public abstract boolean isOn(int x, int y);
  /**
   * shift the location of the shape
   * @param deltaX new x coordinate for the shape
   * @param deltaY new y coordinate for the shape
   */
  public void shiftBy(int deltaX, int deltaY){
    point.x += deltaX; 
    point.y += deltaY;
  }
  /**
   * move the location of the shape
   * @param deltaX new x coordinate for the shape
   * @param deltaY new y coordinate for the shape
   */
  public void moveTo(int newX, int newY){
    point.x = newX; 
    point.y = newY;
  }
  /**
   * a String representation of the shape's state.
   * is an abstract class because different shape has different
   * properties
   */ 
  public abstract String toString();
  /**
   * change the size of the shape
   * @param aSize a new size for the shape
   */ 
  public void changeHeight (int aHeight){
    height = aHeight;
  }
  
  /**
   * change the color of the shape
   * @param aColor a new Color for the shape
   */ 
  public void changeColor (Color aColor){
    color = aColor;
  }
  /**
   * get the x-coordinate of the shape
   * @return the x-coordinate
   */ 
  public int getX() {
    return point.x;
  }
  /**
   * get the y-coordinate of the shape
   * @return an int type y-coordinate
   */ 
  public int getY() {
    return point.y;
  }
  /**
   * get the size of the shape
   * @return an int type size
   */ 
  public int getHeight() {
    return height;
  }
  /**
   * get the color of the shape
   * @return a COlor type color
   */
  public Color getColor() {
    return color;
  }
  /**
   * sets this Shape to be selected or not selected;
   * @param b boolean true or false
   */ 
  public void setSelected(boolean b){
    select = b;
  }
  /**
   * check if the shape is slected or not
   * @return true if this Shape is selected, false if not.
   */ 
  public boolean isSelected(){
    return select;
  }
}