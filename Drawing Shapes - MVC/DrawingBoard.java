import java.awt.*;
import java.util.*;
/**
 * a DrawingBoard that contains a list of shapes
 * 
 * @author Duong Chau
 * @version HW2
 */ 
public class DrawingBoard {
  private ArrayList<Shape> shapeList;
  private Shape selected;
  private ArrayList<VIEW> viewers;
  private int pressed;   // 1 for circle - 2 for tee - 3 for delta - 4 to STOP
  
  public static int centerX = 50; // drag point of the horizontal slider
  public static int centerY = 50; // drag point of the vertical slider
  /**
   * constructor
   * instantiate the list of shape
   */
  public DrawingBoard(){
    shapeList = new ArrayList<Shape>();
    viewers = new ArrayList<VIEW>();
  }
  /**
   * get the current reference of the pressed button
   * @return the current button
   */ 
  public int getPressed(){
    return pressed;
  }
  /**
   * set the current reference of the pressed button
   * @param p the current button's reference
   */ 
  public void setPressed(int p){
    pressed = p;
  }
  /**
   * a query method to get the shapeList information
   * @return the list of shapes
   */ 
  public ArrayList<Shape> getList(){
    return shapeList;
  }
  /**
   * add a shape to the list
   */ 
  public void addShapes(Shape shape){ 
    if(shape == null) throw new IllegalStateException();
    shapeList.add(shape);
    selected = shape;
    selected.setSelected(true);
    for(int i = 0; i < shapeList.size()-1;i++){
      shapeList.get(i).setSelected(false);
    }
    notifyAllViewers();
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
    if(found){
      selected.setSelected(true);
      shapeList.remove(selected);
      shapeList.add(selected);
      for(int i = 0; i < shapeList.size();i++){
        if(shapeList.get(i)!=selected)
          shapeList.get(i).setSelected(false);
      }
    }
    if(!found) {
//      selected.setSelected(false);  //throw exception ????
      for(int i = 0; i < shapeList.size();i++){
        if(shapeList.get(i)!=selected)
          shapeList.get(i).setSelected(false);
      }
      selected = null;
    }
    notifyAllViewers();
  }
  /**
   * get the selected shape and return
   * @return the selected shape
   */ 
  public Shape getSelected(){
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
      selected.setSelected(true);
      shapeList.remove(selected);
      shapeList.add(selected);
    } else {
      selected = null;
    }
    notifyAllViewers();
  }
  /**
   * change the color of the selected shape
   * @param color the color that the shape will be changed into
   */ 
  public void changeColor(Color color){
    if(selected == null) throw new IllegalStateException();
    selected.changeColor(color);
    notifyAllViewers();
  }
  /**
   * change the x and y coordinate of the shape
   * @param changeInX the new x coordinate
   * @param changeInY the new y coordinate
   */
  public void shift (int changeInX, int changeInY){
    if(selected == null) throw new IllegalStateException();
    selected.shiftBy(changeInX, changeInY);
    notifyAllViewers();
  }
  /**
   * move all the shape
   * @param changeInX the new x coordinate
   * @param changeInY the new y coordinate
   */
  public void moveAll (int x, int y){
    int changeInX = x - centerX;
    int changeInY = y - centerY;
    
    for (int i = 0; i < shapeList.size() ; i++){
      Shape shape = shapeList.get(i);
      shape.shiftBy(changeInX, changeInY);
    }
    centerX = x;
    centerY = y;
    notifyAllViewers();
  }
  /**
   * return the copy of the list of shapes
   * @return a copy of the shapeList
   */
  public ArrayList<Shape> copyList(){
    return new ArrayList<Shape>(shapeList);
    
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
  /**
   * register the viewrs to the board
   * @param view a VIEW object
   */ 
  public void registerViewer( VIEW view ) {
    if ( view != null ) {
      viewers.add( view );
    }
  }
  /**
   * notify all viewrs that registered to the board
   * calling the change method  to update the view
   */ 
  private void notifyAllViewers() {
    Iterator<VIEW> it = viewers.iterator();
    while ( it.hasNext() ) {
      VIEW v = it.next();
      v.listChanged();
    }
  }
}