import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * a button listener that send the model message on what to draw
 * @author Duong H Chau
 * @version HW3
 */ 
public class ButtonListener implements ActionListener {
  private DrawingBoard board;
  /**
   * constructor
   * @param b the drawingboard reference
   */ 
  public ButtonListener(DrawingBoard b){
    board = b;
  }
  /**
   * send the message to the model what should be draw next
   * @param e ActionEvent object
   */ 
  public void actionPerformed(ActionEvent e) {
    JButton b =  (JButton)e.getSource();
    if(b.getActionCommand().equals("Circle")) board.setPressed(1);
    if(b.getActionCommand().equals("Tee")) board.setPressed(2);
    if(b.getActionCommand().equals("Delta")) board.setPressed(3);
    if(b.getActionCommand().equals("STOP")) board.setPressed(4);
    if(b.getActionCommand().equals("Delete")) board.setPressed(5);
  }
}