import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * A mouse listener that respond to mouse events
 * @author Duong H Chau
 * @version HW3
 */ 
public class ShapeMouseListener extends JPanel
  implements MouseListener, MouseMotionListener {
  
  private DrawingBoard board;
  private static Random random;
  private boolean isDrag;
  private Shape moving;
  int pressedX,pressedY;
  /**
   * constructor
   * @param b the drawingboard
   */
  public ShapeMouseListener(DrawingBoard b){
    super();
    board = b;
    isDrag = false;
    random = new Random(System.currentTimeMillis());
  }
  /**
   * react to the event of when a mouse is click
   * @param e a MouseEvent object
   */ 
  public void mouseClicked(MouseEvent e) {
    if(board.getPressed() == 1){
      Point point = new Point(e.getX(), e.getY());
      Shape c = new Circle(point, random.nextInt(81)+20,
                           new Color(random.nextInt(256),
                                     random.nextInt(256),
                                     random.nextInt(256)));
      board.addShapes(c);
    }
    if(board.getPressed() == 2){
      int r = random.nextInt(81);
      int d = r%10;
      Point point = new Point(e.getX(), e.getY());
      Shape t = new Tee(point, r+(10-d)+20,
                           new Color(random.nextInt(256),
                                     random.nextInt(256),
                                     random.nextInt(256)));
      board.addShapes(t);
    }
    if(board.getPressed() == 3){
      int r = random.nextInt(81);
      int a = r%2;
      Point point = new Point(e.getX(), e.getY());
      Shape d = new Delta(point, r+(2-a)+20,
                           new Color(random.nextInt(256),
                                     random.nextInt(256),
                                     random.nextInt(256)));
      board.addShapes(d);
    }
    if(board.getPressed() == 4){
      board.shapeSelect(e.getX(), e.getY());
    }
    if(board.getPressed() == 5){
      board.shapeSelect(e.getX(), e.getY());
      board.remove();
    }
  }
  
  /**
   * react to the event of when a mouse is pressed
   * @param e a MouseEvent object
   */ 
  public void mousePressed(MouseEvent e) {
    if(board.getPressed() == 4){
      board.shapeSelect(e.getX(), e.getY());
      if(board.getSelected()!=null){
        moving = board.getSelected();
        isDrag = true;
      }
    }
    pressedX = e.getX();
    pressedY = e.getY();
  }
  /**
   * react to the event of when a mouse is dragged
   * @param e a MouseEvent object
   */ 
  public void mouseDragged( MouseEvent e ) {
    
    if(isDrag){
      board.shift(e.getX() - pressedX, e.getY() - pressedY);
    }
    pressedX = e.getX();
    pressedY = e.getY();
  }
  /**
   * react to the event of when a mouse is released
   * @param e a MouseEvent object
   */ 
  public void mouseReleased(MouseEvent e) {
    isDrag = false;
  }
  
  // all of the other mouse event methods
  public void mouseEntered(MouseEvent e) { }
  public void mouseExited(MouseEvent e) { }
  public void mouseMoved(MouseEvent e) { }
}
  