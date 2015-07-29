import javax.swing.*;
import java.awt.*;
import java.util.*;
/**
 * a graphic viewer
 * contains the paintComponent method that calls the actual
 * draw method in the model.
 * calls repaint when ever the level is changed
 * 
 * @author Duong Chau
 * @version HW4
 */ 

public class CarpetView extends JPanel implements View{
  private SierpinskiCarpet model;
  /**
   * constructor
   * sets the states of the panel
   * assign refference to the instance variable
   * @param sc the refference to the model
   */ 
  public CarpetView(SierpinskiCarpet sc){
    super();
    // sets the states of the panel
    setPreferredSize( new Dimension(295,295) );
    setBackground(Color.white);
    this.setBorder(BorderFactory.createLineBorder(Color.red));
    
    //assigns refference and register to model
    model = sc;
    model.registerViewer(this);
  }
  /**
   * overrided method from interface
   * calls repaint whenever the level is changed
   */ 
  public void levelChanged(){
    this.repaint();
  }
  /**
   * a paintComponent method that pass the graphics object
   * to the actual draw method in the model
   * @param g the graphics object
   */ 
  public void paintComponent(Graphics g){
    super.paintComponent(g);
    g.setColor(Color.black);
    model.drawCarpet(g);
    
  }
}