import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * a button listener that send the model message on 
 * increasing or decreasing the level
 * @author Duong H Chau
 * @version HW4
 */ 
public class ButtonListener implements ActionListener {
  private SierpinskiCarpet carpet;
  /**
   * constructor
   * assigns refference to the instance variable
   * @param c the model carpet reference
   */ 
  public ButtonListener(SierpinskiCarpet c){
    carpet = c;
  }
  /**
   * sends the message to the model when the button is pressed
   * if the "up" button is pressed calls the lvlUp() method
   * if the "down" button is pressed calls the lvlDown() method
   * @param e ActionEvent object
   */ 
  public void actionPerformed(ActionEvent e) {
    JButton b =  (JButton)e.getSource();
    if(b.getActionCommand().equals("UP")) carpet.lvlUp();
    if(b.getActionCommand().equals("DOWN")) carpet.lvlDown();
  }
}