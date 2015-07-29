import java.awt.*;
import java.lang.*;
/**
 * a Circle class that extends shapes
 * @author Duong Chau
 * @version HW2
 */ 
public class Circle extends Shape{
  private Point center;
  private int radius;
  /**
   * constructor
   * @param c the center of the shape
   * @param r the radius of the shape
   * @param color the color of the shape 
   */ 
  public Circle (Point c, int r, Color color){
    super (new Point(c.x - r, c.y - r), 2*r, color);
    center = c;
    radius = r;
  }
  /**
   * a toString method the display the properties of the object
   * @return a string represents the object
   */ 
  public String toString(){
    String str = "";
    str += "a circle with center at ("+ center.x +","+ center.y +") and radius of "+radius
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
    double lbound = center.x - Math.sqrt(radius*radius - (y - center.y)*(y - center.y));
    double rbound = center.x + Math.sqrt(radius*radius - (y - center.y)*(y - center.y));
    if(getY() <= y && y <= getY() + getHeight()){
      if(x >= lbound && x <= rbound){
        return true;
      }
    }
    return false;
  }
  /**
   * overiding the shiftBy method
   * also shift the center of the circle
   * @param deltaX new x coordinate for the shape
   * @param deltaY new y coordinate for the shape
   */
  public void shiftBy(int deltaX, int deltaY){
    center.x += deltaX; 
    center.y += deltaY;
    super.shiftBy(deltaX,deltaY);
  }
  /**
   * overiding the moveTo method
   * also move the center of the circle
   * @param deltaX new x coordinate for the shape
   * @param deltaY new y coordinate for the shape
   */
  public void moveTo(int newX, int newY){
    center.x = newX; 
    center.y = newY;
    super.moveTo(newX,newY);
  }
}