import javax.swing.*;
import java.awt.*;
/**
 * 
 * @author Duong H Chau
 * @version HW3
 */ 
public class GraphicsEditorApp {
  private JFrame frame;
  private DrawingBoard board;
  private TextView view1;
  private GraphicView view2;
  
  public static void main( String[] args ) { 
    //new GraphicsEditorApp();
    new DrawingControl();
  }
  
  public GraphicsEditorApp(){
    frame = new JFrame ("Shapes viewer");
    board = new DrawingBoard();
    board.addShapes(new Circle(new Point(130,120), 20, Color.blue));
    board.addShapes(new Tee(new Point(50,100), 40, Color.red));
    view1 = new TextView(board);
    view2 = new GraphicView(board);
    frame.getContentPane().add(view1);
    frame.getContentPane().add(view2,BorderLayout.SOUTH);
    frame.pack();
    frame.setVisible(true);
    board.addShapes(new Delta(new Point(100,100), 40, Color.green));
    
  }
}