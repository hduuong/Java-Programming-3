import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * the controller, hook up viewers, model, and listeners
 * @author Duong H Chau
 * @verion HW3
 */ 
public class DrawingControl {
  private DrawingBoard board;
  private JFrame frame;
  
  /**
   * contructor
   * contructs and hooks up viewers, model and listeners
   */ 
  public DrawingControl (){
    //constructs the frame
    frame = new JFrame("shape viewer");
    //instantiate the DrawingBoard
    board = new DrawingBoard();
    
// creates the 2 viewers and add to the frame //////////////////////////    
    TextView view1 = new TextView(board);
    GraphicView view2 = new GraphicView(board);
    view2.setLayout(new BorderLayout());
    frame.getContentPane().add(view1,BorderLayout.NORTH);
    frame.getContentPane().add(view2,BorderLayout.CENTER);
    
// creates the buttonPanel and add to the frame //////////////////////////
    Button button = new Button(board);
    frame.getContentPane().add(button,BorderLayout.EAST);

// creates the mouseListener and add graphic view //////////////////////////    
    ShapeMouseListener click = new ShapeMouseListener(board);
    view2.addMouseListener(click);
    view2.addMouseMotionListener(click);
    
// create 2 sliders //////////////////////////////////
    JSlider slider1 = new JSlider(0,200,DrawingBoard.centerX);
    JSlider slider2 = new JSlider(0,200,DrawingBoard.centerY);
    
// make the slider2 slides vertically /////////////////////////
    slider2.setOrientation(JSlider.VERTICAL);
    slider2.setInverted(true);
    
// instantiate the Slider Listener and asign it to the 2 sliders
    JSliderListener drag = new JSliderListener(board,slider1,slider2);
    slider1.addChangeListener(drag);
    slider2.addChangeListener(drag);
    
// add the sliders to the frame //////////////////////////////////
    view2.add(slider1, BorderLayout.SOUTH);
    view2.add(slider2, BorderLayout.WEST);
    
// creates the color chooser
    ColorChooser color = new ColorChooser(board);
    
// add the JcolorChooser to the frame    
    frame.getContentPane().add(color,BorderLayout.SOUTH);
    
    frame.pack();
    frame.setVisible(true);
    frame.toFront();
    
  }
}