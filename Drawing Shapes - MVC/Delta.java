import java.awt.*;
/**
 * a Delta class extends shapes
 * 
 * @author Duong Chau
 * @version HW2
 */ 
public class Delta extends Shape{
  private Point apex;
  private int height;
  /**
   * constructor
   * @param ap the apex point of the delta
   * @param height the height of the shape
   *        height is an even number.
   * @param color the color of the shape 
   */ 
  public Delta (Point ap, int h, Color color){
    super (new Point(ap.x - h/2,ap.y), h, color);
    if(h%2 != 0) throw new IllegalArgumentException("height must be a multiple of 2.");
    height = h;
    apex = ap;
  }
  /**
   * a toString method the display the properties of the object
   * @return a string represents the object
   */ 
  public String toString(){
    String str = "";
    str += "a Delta with its apex at ("+apex.x+","+apex.y+") and height of "+this.getHeight()
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
    double slope1 = -2.0;
    double slope2 = 2.0;
    double leftBound = ((y - apex.y) / slope1) + apex.x;
    double rightBound = ((y - apex.y) / slope2) + apex.x;
    if( y >= apex.y && y <= (apex.y + height)){
      if( (double)x >= leftBound && (double)x <= rightBound ){
        return true;
      }
    }
    return false;
  }
  /**
   * overiding the shiftBy method
   * also shift the apex of the delta
   * @param deltaX new x coordinate for the shape
   * @param deltaY new y coordinate for the shape
   */
  public void shiftBy(int deltaX, int deltaY){
    apex.x += deltaX; 
    apex.y += deltaY;
    super.shiftBy(deltaX,deltaY);
  }
  /**
   * overiding the moveTo method
   * also move the apex of the delta
   * @param deltaX new x coordinate for the shape
   * @param deltaY new y coordinate for the shape
   */
  public void moveTo(int newX, int newY){
    apex.x = newX; 
    apex.y = newY;
    super.moveTo(newX,newY);
  }
  /**
   * overiding the drawShape method
   * draw a shape with black line if selected
   * @param g Graphic object
   */
  public void drawShape(Graphics g){
    Point point1 = new Point(apex.x,apex.y);
    Point point2 = new Point(getX(),getY()+height);
    Point point3 = new Point(getX()+height,getY()+height);
    
    int[] x = {point1.x,point2.x,point3.x};
    int[] y = {point1.y,point2.y,point3.y};
    if(isSelected()){
      g.setColor(Color.black);
      g.drawPolygon(x,y,3);
      g.setColor(getColor());
      g.fillPolygon(x,y,3);
    }
    if(!isSelected()){
      g.setColor(getColor());
      g.fillPolygon(x,y,3);
    }
  }
}