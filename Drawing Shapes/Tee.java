import java.awt.*;
/**
 * 
 * @author Duong Chau
 * @version HW2
 */ 
public class Tee extends Shape{
  private int thickness;
  private Point point;
  private int height;
  /**
   * constructor
   * @param p the point where the shape is drawn
   * @param height the height of the shape
   *        height is a multiple of 10.
   * @param color the color of the shape 
   */ 
  public Tee (Point p, int h, Color color){
    super (p, h, color);
    if(h%10 != 0) throw new IllegalArgumentException("height must be a multiple of 10.");
    height = h;
    thickness = height/5;
    point = p;
  }
  /**
   * a toString method the display the properties of the object
   * @return a string represents the object
   */ 
  public String toString(){
    String str = "";
    str += "a Tee Shape that has starting point ("+point.x+","+point.y+") and height of "+ height 
      +" and has " + getColor() + " color";
    return str;
  }
  /**
   * check if the shape is lies on a point
   * @param x the x coordinate
   * @param y the y coordinate
   * @return true if the point (x,y) is on the Shape, false if not.
   */
  public boolean isOn(int x, int y){
    if(y >= getY() && y <= getY() + getHeight()){
      if(y >= getY() && y <= getY() + getHeight()/5){
        if(x >= getX() && x <= getX() + getHeight()) return true;
      }
      if(y > getY() + getHeight()/5 && y <= getY() + getHeight()){
        if(x >= getX() + getHeight()*2/5 && x <= getX() + getHeight()*3/5) return true;
      }
    }
    return false;
  }
}