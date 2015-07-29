import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/**
 * 
 * @author Duong H Chau
 * @version HW3
 */ 
public class Button extends JPanel{
  /**
   * contructor
   * instantiate the buttons, and the button listener
   * hook up the listener to each button
   * set size of the panel
   * @param board the reference to drawingboard
   */ 
  public Button(DrawingBoard board){
    JButton button1 = new JButton("add Circle");
    JButton button2 = new JButton("add Tee");
    JButton button3 = new JButton("add Delta");
    JButton button4 = new JButton("stop adding");
    JButton button5 = new JButton("delete a shape");
    
    button1.setActionCommand("Circle");
    button2.setActionCommand("Tee");
    button3.setActionCommand("Delta");
    button4.setActionCommand("STOP");
    button5.setActionCommand("Delete");
    
    this.setPreferredSize(new Dimension(100,500));
    this.setBorder(BorderFactory.createLineBorder(Color.green));
    
    ButtonListener listener = new ButtonListener(board);
    button1.addActionListener( listener );
    button2.addActionListener( listener );
    button3.addActionListener( listener );
    button4.addActionListener( listener );
    button5.addActionListener( listener );
    
    add( button1 );
    add( button2 );
    add( button3 );
    add( button4 );
    add( button5 );
  }
}