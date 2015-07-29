import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.colorchooser.*;
/**
 * a color chooser that allows user to choose a color in order to
 * change the color of the selected shape to the refered one
 * @author Duong H Chau
 * @version HW3
 */ 
public class ColorChooser extends JPanel implements ChangeListener {
  
  private DrawingBoard board;
  private JColorChooser colorChooser;
  
  /**
   * constructor
   * constructor the JColorChooser
   * get the DrawingBoard reference
   * @param b the drawingBoard reference
   */ 
  public ColorChooser(DrawingBoard b){
    super(new BorderLayout());
    board = b;
    
    colorChooser = new JColorChooser();
    colorChooser.getSelectionModel().addChangeListener(this);
    colorChooser.setBorder(BorderFactory.createTitledBorder("Choose Shape Color"));
    
    add(colorChooser);
  }
  /**
   * stateChanged method, change the color of the selected shape
   * with the chosen color in the JColorChooser
   */ 
  public void stateChanged(ChangeEvent e){
    Color newColor = colorChooser.getColor();
    board.changeColor(newColor);
  }
}