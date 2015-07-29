import javax.swing.*;
import java.awt.*;
/**
 * a button panel that contains two buttons (up and down)
 * 
 * @author Duong H Chau
 * @verision HW4
 */ 

public class LevelButton extends JPanel{
  
  /**
   * constructor
   * constructs the two buttons, sets their action commands
   * adds listener to buttons and put them in the panel
   * @param carpet the reffernce to the model
   */ 
  public LevelButton (SierpinskiCarpet carpet){
    // creates 2 buttons
    JButton button1 = new JButton("down");
    JButton button2 = new JButton("up");
    
    //sets action command
    button1.setActionCommand("DOWN");
    button2.setActionCommand("UP");
    
    //sets the states of the panel
    this.setPreferredSize(new Dimension(100,50));
    this.setBorder(BorderFactory.createLineBorder(Color.green));
    
    //creates the listenner and add it to the 2 buttons
    ButtonListener listener = new ButtonListener(carpet);
    button1.addActionListener( listener );
    button2.addActionListener( listener );
    
    //adds buttons to the panel
    add(button1);
    add(button2);
  }
  
}