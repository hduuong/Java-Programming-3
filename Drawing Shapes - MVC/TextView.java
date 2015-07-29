import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
/**
 * display the current number of shapes in the DrawingBoard, 
 * and the details of the selected shape
 * @author Duong h Chau
 * @version HW3
 */ 
public class TextView extends JPanel implements VIEW{
  private DrawingBoard list;
  private JLabel textDisplay1;
  private JLabel textDisplay2;
  /**
   * contructor
   * 
   * @param board a reference to drawingboard
   */ 
  public TextView(DrawingBoard board){
    super();
    this.setPreferredSize(new Dimension(900,50));
    this.setLayout(new BorderLayout());
    this.setBorder(BorderFactory.createLineBorder(Color.blue));
    textDisplay1 = new JLabel("                                                                    "+
                              "                   no shapes");
    textDisplay2 = new JLabel("no selected shape");
    this.add(textDisplay1);
    this.add(textDisplay2,BorderLayout.SOUTH);
    list = board;
    list.registerViewer(this);
    
  }
  /**
   * a method that update the view every time the model changed
   */ 
  public void listChanged(){
    textDisplay1.setText("                                                                         "+
                         "                   "+ list.getList().size() + " shapes");
    textDisplay2.setText("The selected shape is: \n " + list.getSelected());
  }
  
}