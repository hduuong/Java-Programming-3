import javax.swing.*;
import java.util.*;
import java.awt.*;
/**
 * a controller that hook up the model - SierpinskiCarpet
 * with the viewers - CarpetView(Graphic) and LevelView(Text)
 * 
 * @author Duong H Chau
 * @version HW4
 */ 
public class CarpetController {
  private SierpinskiCarpet model;
  private JFrame frame;
  
  /**
   * constructor
   * contructs the frame and instantiate the model
   */ 
  public CarpetController(){
    //constructs the frame
    frame = new JFrame("carpet viewer");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    //instantiate the DrawingBoard
    model = new SierpinskiCarpet(99,99,99,1);
    
    // creates the 2 viewers and add to the frame 
    LevelView view1 = new LevelView(model);
    CarpetView view2 = new CarpetView(model);
    frame.getContentPane().add(view1,BorderLayout.NORTH);
    frame.getContentPane().add(view2,BorderLayout.CENTER);
    
    // creates the button panel and add to the frame
    LevelButton button = new LevelButton(model);
    frame.getContentPane().add(button,BorderLayout.SOUTH);
    
    frame.pack();
    frame.setVisible(true);
    frame.toFront();
  }
  /**
   * a main method that call the constructor
   * @param args a string array arguments
   */ 
  public static void main( String[] args ) { 
    new CarpetController();
  }
}