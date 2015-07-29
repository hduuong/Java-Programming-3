import java.util.*;
/**
 * This class generates a magic square base on the input of odd
 * numbers by the user
 * 
 * @author Duong H Chau
 * @version HW1 - CSC 143
 */
public class OddMagicSquare {
  //instance variables
  private int size;              //the size of the square array
  private int[][] mSquare;       //the array 
  
  public OddMagicSquare(int n){
    if(n < 0){
      throw new IllegalArgumentException();
    }
    if(n % 2 == 0){
      throw new IllegalArgumentException();
    }
    size = n;
    mSquare =  new int[n][n];
    if(size == 1){
      mSquare[0][0] = 1;
    }else{
      buildSquare(mSquare, size);
    }
  }
  /**
   * a private helper method that build the magic square
   * @param square the square array
   * @param size the size of the array
   */ 
  private void buildSquare(int[][] square, int size){
    
    //the starting point
    int n = size - 1;
    int m = (size - 1) / 2;
    square[n][m] = 1;
    
    for (int ii = 2; ii <= size*size; ii++){
      int nHolder = n;
      int mHolder = m;
      n = n + 1;
      m = m + 1;
      
      if(n > size - 1){
        n = n - size;
      }
      if( m > size - 1){
        m = m - size;
      }
      
      if(square[n][m] == 0){
        square[n][m] = ii;
      }
      else{
        n = nHolder - 1;
        m = mHolder;
        square[n][m] = ii;
      }
    }
  }
  /**
   * the static method that check if an array is Magic or not
   * @param a an int type double array
   * @return a boolean type true/false
   */ 
  public static boolean isMagic(int[][] a){
    //check if it is null
    if(a == null) return false;
    
    //check if it is even or empty
    if(a.length % 2 == 0) return false;
    
   //check if the double int array is square
    for(int i = 0; i < a.length; i++){
      int width = 0;
      for(int k = 0; k < a[i].length; k++){
        width ++;
      }
      if(width != a.length) return false;
    }
    //Contains all the numbers
    for(int ii = 1; ii <= a.length*a.length; ii++){        //check nums from 1 to N^2
      boolean found = false;
      for(int i = 0; i < a.length; i++){
        for(int k = 0; k < a[i].length && !found; k++){
          if(a[i][k] == ii) found = true;
        }
      }
      if(!found) return false;
    }
    //check the center numbet
    int center = a[(a.length-1)/2][(a.length-1)/2];
    int check = (a.length*a.length + 1)/2;                 // (N^2 + 1) / 2
    if(check != center) return false;
    
    ////////////////////////////////////////////
    //check the sum of the rols, cols, diagonals
    ////////////////////////////////////////////
    int sum = 0;
    int sumD1 = 0;
    int sumD2 = 0;
    for(int i = 0; i < a.length; i++){ 
      sum += a[0][i];                                      //find the sum of the 1st row
      //sum of the 2 diagonals
      sumD1 += a[i][i];                                    
      sumD2 += a[(a.length - 1) - i][(a.length - 1) - i];
    }
    if(sumD1 != sum || sumD2 != sum) return false;
    
    for(int i = 0; i < a.length; i++){
      int sumR = 0;                                        
      int sumC = 0;                                           
      for(int k = 0; k < a[i].length; k++){
        sumR += a[i][k];                                   //find the sum of the rows    
        sumC += a[k][i];                                   //find the sum of the cols 
      }
      if(sumR != sum || sumC != sum) return false;
    }
    
    //passes all test
    return true;  
  }
  /**
   * a toString method that shows the String representation 
   * of this MagicSquare
   * @return this String 
   */
  public String toString(){
    String display = "";
    for (int j = 0; j < size; j++){
      for (int k = 0; k < size; k++){
        display += " " + Integer.toString(mSquare[k][j]);
      }
      display += "\n";
    }
    return display;
  }
  /**
   * a method that query a square
   * @return the square of this instance
   */
  public int[][] getSquare(){
    return mSquare;
  }
  /**
   * a method that get the input from user and print out the magic square
   * this is a recursive method
   */ 
  public void getInput(){
    System.out.print("please enter a positive odd integer __ or 0 to stop");
    Scanner scan = new Scanner(System.in);
    
    //check if the input is an intger
    if(!scan.hasNextInt()){
      System.err.println("please enter a positive odd integer");
      getInput();
      
    } else {
      int input = Integer.parseInt(scan.next());
      if(input != 0){
        if(input < 0 || input%2 == 0){
          System.err.println("please enter a positive odd integer");
          getInput();
        }else{
          OddMagicSquare s = new OddMagicSquare(input);
          String str = s.toString();
          System.out.println("a magic square with dimension of " + input +"X"+ input);
          System.out.println(str);
          getInput();
        }
      }
    }
    return;
  }
  /**
   *
   * @param args
   */
  public static void main(String[] args){
    OddMagicSquare s = new OddMagicSquare(7);
    String str = s.toString();
    System.out.println("a test with input of 7");
    System.out.println(str);
    boolean tf = isMagic(s.getSquare());
    System.out.println("is the square magic: " + tf);
    s.getInput();
  }
}