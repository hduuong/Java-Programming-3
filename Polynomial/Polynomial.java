import java.util.*;
/**
 * the class Polynomial - model a polynomial expression
 * this polynomial is defined by a size && a linkedlist of terms
 * 
 * One term has one variable x, real number coefficients,
 * and non-negative integer exponents.
 * @author Duong H Chau
 * @version HW5
 */ 
public class Polynomial {
  private PNode front;
  private int size;
  
  /**
   * a default constructor
   */ 
  public Polynomial(){
  }
  /**
   * constructor
   * takes in a string and gets the integer values out of
   * the string and put them into the correct order of the
   * Polynomial linked list
   * @param s the string of integers
   */ 
  public Polynomial(String s){
    if(s.equals("")) return;
    String[] str = s.split(" ");    
/////////////set the size of the list///////////////////////
    if(str.length%2 != 0) throw new IllegalArgumentException();
    size = str.length/2;
    ArrayList<Double> values = new ArrayList<Double>();   
    //Get all the integer values into the arraylist of Integer
    for(int i = 0; i < str.length; i++){
      double num = 0;
      //Parse the String into Int
      if(str[i].substring(0,1).equals("-")){
        String ss = str[i].substring(1);
        num = -Double.parseDouble(ss);
      }else{
        String ss = str[i];
        num = Double.parseDouble(ss);
      }
      values.add(num);
    }
    
    //making the linked list
    int count = 0;
    double coe = values.get(0);
    double exp = values.get(1);
    front = new PNode(coe,(int)exp,null);
    PNode pointer = front;
    for(int i = 2; i < values.size(); i++){
      count++;
      if(count % 2 != 0)
        coe = values.get(i);
      if(count % 2 == 0){
        exp = values.get(i);
        pointer.next = new PNode(coe,(int)exp,null);
        pointer = pointer.next;
      }
    }
    
    //take out the coefficient of 0 inside the list
    takeOutZeros();

    PNode helper2 = front;
    PNode helper = front.next;
    //rearrange the exponents to be in order
    //also add the coefficient of the duplicated terms
    while(helper!=null){
      if(helper.exponent >= helper2.exponent){
        helper2.next = helper.next;
        size --;
        addTerm(helper.coefficient, helper.exponent);
        helper = helper2.next;
      }else{
        helper2 = helper2.next;
        helper = helper2.next;
      }
    }
    
    //thows exception when there is a negative exponent
    helper = front.next;
    helper2 = front;
    while(helper != null){
      if(helper.exponent < 0)
        throw new IllegalArgumentException();
      helper = helper.next;
      helper2 = helper2.next;   
    }
  }
  /**
   * a private method that helps to take out all 0 coefficients
   */
  private void takeOutZeros (){
    //take out the front coefficient of 0
    while(front.coefficient == 0.0){
      front = front.next;
      size--;
    }
    PNode helper2 = front;
    PNode helper = front.next;
    while(helper!=null){
      if(helper.coefficient == 0.0){
        helper2.next = helper.next;
        size --;
        helper = helper2.next;
      }else{
        helper2 = helper2.next;
        helper = helper2.next;
      }
    }
  }
  /**
   * a second constructor that makes this a deep copy of
   * the Polynomial that passed in the parameter
   * @param p a Polynomial
   */ 
  public Polynomial(Polynomial p){
    if(p==null) throw new IllegalArgumentException();
    
    size = p.terms();
    front = new PNode(p.front.coefficient,p.front.exponent,null);
    PNode p1 = front;
    PNode p2 = p.front.next;
    while(p2 != null){
      p1.next = new PNode(p2.coefficient,p2.exponent,null);
      p1 = p1.next;
      p2 = p2.next;
    }
  }
  /**
   * add one more term to the polynomial linked list 
   * if the exponent has not existed
   * if does add the coefficient together
   * @param coef the new coefficient
   * @param exp the new exponent
   */ 
  public void addTerm (double coef, int exp){
    if(exp < 0) throw new IllegalArgumentException();
    if(coef == 0) return;
    if(size == 0){
      front = new PNode(coef,exp,null);
      size++;
      return;
    }
    if(front.exponent == exp){
      front.coefficient += coef;
      //takeOutZeros();
      if(front.coefficient == 0){
        deleteTerm(front.exponent);
      }
      return;
    }
    if(front.exponent < exp){
      PNode newFront = new PNode(coef,exp,front);
      front = newFront;
      size++;
      return;
    }
    PNode helper = front;
    PNode helper2 = front.next;
    boolean found = false;
    while(helper2!= null && !found){
      if(helper2.exponent == exp){
        helper2.coefficient += coef;
        takeOutZeros();
        found = true;
      }
      if(helper2.exponent < exp){
        helper.next = new PNode(coef,exp,helper2);
        found = true;
        size++;
      }
      helper = helper.next;
      helper2 = helper2.next;
    }
    if(!found){
      helper.next = new PNode(coef,exp,null);
      size++;
    }
  }
  /**
   * delete the term that has a specified exponent
   * @param exp the exponent of the term being deleted
   * @return double type the coefficient of the term got deleted
   */
  public double deleteTerm(int exp){
    if(exp < 0) throw new IllegalArgumentException();
    if(size == 0) return 0.0;
    if(exp > front.exponent) return 0.0;
    if(exp == front.exponent) {
      double n = front.coefficient;
      front = front.next;
      size--;
      return n;
    }
    
    PNode helper1 = front;
    PNode helper2 = front.next;
    while(helper2 != null){
      if(helper2.exponent == exp){
        double n = helper2.coefficient;
        size--;
        helper1.next = helper2.next;
        return n;
      }else{
        helper1 = helper1.next;
        helper2 = helper1.next;
      }
    }
    return 0.0;
  }
  /**
   * get the coefficient of a specified exponent
   * @param exp the exponent of a term
   * @return double type the coefficient of that term
   */ 
  public double getCoefficient(int exp){
    if(exp > front.exponent) return 0.0;
    if(exp < 0) throw new IllegalArgumentException();
    double coe = 0.0;
    boolean found = false;
    PNode helper = front;
    while(helper != null && !found){
      if(helper.exponent == exp){
        coe = helper.coefficient;
        found = true;
      }
      helper = helper.next;
    }
    return coe;
  }
  /**
   * evaluate the value of this Polynomial with the X value 
   * @param x - x value
   * @return double type result of the polynomial
   */ 
  public double evaluate(double x){
    double result = 0;
    PNode helper = front;
    while(helper!= null){
      result += helper.coefficient * Math.pow(x,helper.exponent);
      helper = helper.next;
    }
    return result;
  }
  /**
   * derive the polynomial and return that derivative
   * @return the derivate of the polynomial
   */
  public Polynomial derivative(){
    ArrayList<Double> list = new ArrayList<Double>();
    PNode helper = front;
    while(helper!= null){
      if(helper.exponent != 0){
        list.add(helper.coefficient * helper.exponent);
        list.add((double)helper.exponent - 1);
      }
        helper = helper.next; 
    }
    String s = Double.toString(list.get(0));
    for(int i = 1; i < list.size(); i++){
      s += " " + Double.toString(list.get(i));
    }
    return new Polynomial(s);
  }
  /**
   * implement the toString method
   * return a string representation of the polynomial
   * @return that string
   */ 
  public String toString(){
    if(size == 0) return "0.0";
    String s = "";
    PNode helper = front.next;
    if(front.exponent==0){
      s += Double.toString(front.coefficient);
    }
    else if(front.exponent == 1){
      if(front.coefficient == 1) s += "x"; 
      else if(front.coefficient == -1) s += "-x";
      else s += front.coefficient + "x";
    }else{
      if(front.coefficient == 1) s += "x^" + front.exponent;
      else if(front.coefficient == -1) s += "-x^" + front.exponent;
      else s += front.coefficient + "x^" + front.exponent;
    }
    while(helper!=null){
      double coe = helper.coefficient;
      int exp = helper.exponent;
      if(exp == 0){
        if(coe < 0) s += " - " + Math.abs(coe);
        if(coe > 0) s += " + " + coe;
      }else if(exp == 1){
        if(coe == 1) s += " + x";
        if(coe == -1) s += " - x";
        if(coe < -1) s += " - " + Math.abs(coe) + "x";
        if(coe > 1) s += " + " + coe + "x";
      }else{
        if(coe == 1) s += " + x^" + exp;
        if(coe == -1) s += " - x^" + exp;
        if(coe < -1) s += " - " + Math.abs(coe) + "x^" + exp;
        if(coe > 1) s += " + " + coe + "x^" + exp;
      }
      helper = helper.next;
    }
    return s;
  }
  /**
   * a method that return a string that describe the polynomial
   * @return that string
   */ 
  public String description(){
    if(front == null) return "0.0";
    String str = "";
    if(front.exponent == 0){
      str += "constant term " + front.coefficient;
    }else{
      str += "exponent " + front.exponent + ", coefficient " + front.coefficient;
    }
    return recursion(front.next) + str;
  }
  /**
   * a private method that helps return a description of the
   * polynomial
   * @param n a node that is a term of the polynomial
   * @return that string
   */ 
  private String recursion(PNode n){
    if(n == null) return "";
    String str = "";
    if(n.exponent == 0){
      str += "constant term " + n.coefficient + "\n";
    }else{
      str += "exponent " + n.exponent + ", coefficient " + n.coefficient + "\n";
    }
    return recursion(n.next) + str;
  }
  /**
   * override the equals method
   * using early exit
   * @param p the Object that needs to be compared to
   * @return boolean True or False
   */ 
  public boolean equals(Object p){
    if(!(p instanceof Polynomial)) return false;
    if(size != ((Polynomial)p).terms()) return false;
    
    PNode pointer1 = front;
    PNode pointer2 = ((Polynomial)p).getFront();
    while(pointer1!= null){
      if(pointer1.coefficient != pointer2.coefficient
           || pointer1.exponent != pointer2.exponent)
        return false;
      pointer1 = pointer1.next;
      pointer2 = pointer2.next;
    }
    return true;
  }
  /**
   * get the number of terms in the Polynomial
   * @return the size of the linked list
   */
  public int terms(){
    return size;
  }
  /**
   * get the front of the linked list
   * @return the front
   */ 
  public PNode getFront(){
    return front;
  }
  
  /**
   * combine 2 polynomials together and return the sum
   * @param a first polynomial
   * @param b second polynomial
   * @return the sum of those 2 polynomials
   */
  public static Polynomial sum(Polynomial a, Polynomial b){
    if(a == null || b == null){
      throw new IllegalArgumentException();
    }
    if(a.front == null) return new Polynomial(b);
    if(b.front == null) return new Polynomial(a);
    
    Polynomial result = new Polynomial(a);
    PNode helper = b.front;
    while(helper!=null){
      result.addTerm(helper.coefficient, helper.exponent);
      helper = helper.next;
    }
    return result;
  }
  /**
   * multiply 2 polynomials together and return the product
   * @param a first polynomial
   * @param b second polynomial
   * @return the product of those 2 polynomials
   */
  public static Polynomial product(Polynomial a, Polynomial b){
    if(a == null || b == null){
      throw new IllegalArgumentException();
    }
    Polynomial result = new Polynomial();
    PNode helper1 = a.front;
    while(helper1!=null){
      PNode helper2 = b.front;
      while(helper2!=null){
        double coe = helper1.coefficient * helper2.coefficient;
        int exp = helper1.exponent + helper2.exponent;
        result.addTerm(coe,exp);
        helper2 = helper2.next;
      }
      helper1 = helper1.next;
    }
    return result;
  }
  /**
   * a node class that define one term of a polynomial
   * contains a double coefficient, int exponent and the next term
   */ 
  public class PNode{
    private double coefficient;
    private int exponent;
    private PNode next;
    /**
     * contructor
     * @param c the coefficient
     * @param e the exponent
     * @param n the next term
     */ 
    public PNode(double c, int e, PNode n){
      coefficient = c;
      exponent = e;
      next = n;
    }
    /**
     * get the next term of this term
     * @return the next term
     */ 
    public PNode getNext(){
      return next;
    }
    /**
     * get the coefficient of this term
     * @return the coefficient
     */ 
    public double getCoe(){
      return coefficient;
    }
    /**
     * get the exponent of this term
     * @return the exponent
     */ 
    public int getExp(){
      return exponent;
    }
  }
}