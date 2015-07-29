import java.awt.*;
import java.util.*;
/**
 * a DrawingBoard that contains a list of shapes
 * 
 * @author Duong Chau
 * @version HW2
 */ 
public class DrawingBoard {
  private ArrayList<Shapes> shapeList;
  private Shapes selected;
  /**
   * constructor
   * instantiate the list of shape
   */
  public DrawingBoard(){
    shapeList = new ArrayList<Shapes>();
  }
  /**
   * add a shape to the list
   */ 
  public void addShapes(Shapes shape){ 
    if(shape == null) throw new IllegalStateException();
    shapeList.add(shape);
    selected = shape;
  }
  /**
   * select the top most shape that contains the x y coordinate
   * @param x the x coordinate 
   * @param y the y coordinate 
   */ 
  public void shapeSelect(int x, int y){
    boolean found = false;
    for(int i = shapeList.size() - 1; i >= 0 && !found; i--){
      if(shapeList.get(i).isOn(x,y)){
        selected = shapeList.get(i);
        found = true;
      }
    }
    selected = null;
  }
  /**
   * get the selected shape and return
   * @return the selected shape
   */ 
  public Shapes getSelected(){
    return selected;
  }
  /**
   * remove the selected shape and set the top most shape in the
   * list to be selected
   */ 
  public void remove(){
    if(selected == null) throw new IllegalStateException();
    shapeList.remove(selected);
    if(shapeList.size() != 0){
      selected = shapeList.get(shapeList.size() - 1);
    } else {
      selected = null;
    }
  }
  /**
   * change the color of the selected shape
   * @param color the color that the shape will be changed into
   */ 
  public void changeColor(Color color){
    if(selected == null) throw new IllegalStateException();
    selected.changeColor(color);
  }
  /**
   * change the x and y coordinate of the shape
   * @param changeInX the new x coordinate
   * @param changeInY the new y coordinate
   */
  public void shift (int changeInX, int changeInY){
    if(selected == null) throw new IllegalStateException();
    selected.shiftBy(changeInX, changeInY);
  }
  /**
   * return the copy of the list of shapes
   * @return a copy of the shapeList
   */
  public ArrayList<Shapes> copyList(){
    return new ArrayList<Shapes>(shapeList);
  }
  /**
   * a toString method the display the properties of the object
   * @return a string represents the object
   */
  public String toString(){
    String a = "";
    a += " a list of shapes that contains (" + shapeList +") and the selected shape is "+ selected;
    return a;
    
    
  }
}