import java.awt.*;
import javax.swing.*;
import java.util.*;
/**
 * a graphic display of the Shapes in the DrawingBoard
 * @author Duong h Chau
 * @version HW3
 */ 
public class GraphicView extends JPanel implements VIEW{
  private DrawingBoard drawing;
  private JLabel graphicDisplay;
  public GraphicView(DrawingBoard board){
    super();
    this.setPreferredSize(new Dimension(500,300));
    this.setBorder(BorderFactory.createLineBorder(Color.red));
    drawing = board;
    drawing.registerViewer(this);
  }
  /**
   * a method that update the view every time the model changed
   */ 
  public void listChanged(){
    this.repaint();
  }
  /**
   * a paintComponent method, called when repaint
   * this pass the graphics object to the drawing method in each shape
   * @param g the graphic object
   */ 
  public void paintComponent(Graphics g){
    super.paintComponent(g);
    ArrayList<Shape> list = drawing.copyList();
    Iterator<Shape> it = list.iterator();
    while(it.hasNext()){
      it.next().drawShape(g);
    }
  }
}