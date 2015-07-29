import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
/**
 * change the location of all shapes when slide the sliders
 * 
 * @author Duong Chau
 * @version HW3
 */ 
public class JSliderListener implements ChangeListener {
  //instance variables
  private DrawingBoard board;
  private JSlider horizontal;
  private JSlider vertical;
  
  /**
   * constructor
   * instantiate the instance variables
   * @param shapeList the drawing board
   * @param h the horizontal slider
   * @param v the vertical slider
   */ 
  public JSliderListener(DrawingBoard shapeList, JSlider h, JSlider v){
    board = shapeList;
    horizontal = h;
    vertical = v;
  }
  /**
   * call the move method from the drawing board to move all shapes
   * @param e a change event
   */ 
  public void stateChanged (ChangeEvent e){
    board.moveAll(horizontal.getValue(), vertical.getValue());
  }
    
}