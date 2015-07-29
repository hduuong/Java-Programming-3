import java.awt.*;
import java.util.*;
import javax.swing.*;
/**
 * a text view panel that shows the level of the model
 * 
 * @author DUong H Chau
 * @verion HW4
 */ 
public class LevelView extends JPanel implements View{
  private SierpinskiCarpet model;
  private JLabel textDisplay;
  
  /**
   * constructor
   * sets the states of the panel
   * assign refference to the instance variable
   * @param sc the refference to the model
   */ 
  public LevelView(SierpinskiCarpet sc){
    super();
   
    this.setPreferredSize(new Dimension(290,50));
    this.setBorder(BorderFactory.createLineBorder(Color.blue));
    textDisplay = new JLabel();
    this.add(textDisplay);
    
    model = sc;
    model.registerViewer(this);
  }
  /**
   * overrided method from the interface
   * change the display text whenever the level is changed
   */ 
  public void levelChanged(){
    textDisplay.setText("the level of the carpet is: " + model.getLevel());
  }
}