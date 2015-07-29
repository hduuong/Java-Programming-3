import java.util.*;
import java.io.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.filechooser.*;
/**
 * Makes a HuffmanTree - a binary tree - with given text file
 * uses the character and how often each character shows up
 * 
 * @author Duong H Chau
 * @version HW6
 */ 
public class HuffmanTree {
  private HNode root;
  private Map<Character, HNode> characterMap;
  private PriorityQueue<HNode> queue;
  private String codeString;
  private String message;
  private File selectedFile;
  private double averageBits;
  /**
   * constructor
   * constructs the Huffman Tree through reading a file
   * and finding the frequency of each character.
   * makes HNodes with values of frequency and character
   * puts them in priority queue and also their combined frequency
   */ 
  public HuffmanTree(){
    characterMap = new HashMap<Character, HNode>();
    queue = new PriorityQueue<HNode>();
    codeString = "";
    //using the JFileChooser
    selectedFile = null;
    JFileChooser fileChooser = new JFileChooser();
    int returnValue = fileChooser.showOpenDialog(null);
    if (returnValue == JFileChooser.APPROVE_OPTION) {
      selectedFile = fileChooser.getSelectedFile();
      System.out.println(selectedFile.getName());
    }
    //reading a file using scanner
    try{
      Scanner scan = new Scanner(selectedFile).useDelimiter("");
      while(scan.hasNext()){
        String string = scan.next();
        for(int i = 0; i < string.length(); i++){
          Character cr = string.charAt(i);
          if(!characterMap.containsKey(cr)){
            HNode node = new HNode(cr,1);
            characterMap.put(cr,node);
          }else{
            characterMap.get(cr).frequency++;
          }
        }
        
      }
      scan.close();
    //if FileNotFoundException occurs stop the program  
    }catch(FileNotFoundException e){
      return;
    }
    //add all the HNodes from the map to the priority queue
    Set<Character> set = characterMap.keySet();
    if(set.size() == 0) {
      System.err.println("File is empty");
      return;
    }
    Iterator it = set.iterator();
    while(it.hasNext()){
      queue.add(characterMap.get(it.next()));
    }
    //add the combined weight to the priority queue
    while(queue.size() != 1){
      HNode left = queue.poll();
      HNode right = queue.poll();
      queue.add(new HNode(left,right));
    }
    //assigns the root of the tree
    root = queue.poll();
    //assigns the code for the leaves
    if(root.right == null && root.left == null){
      root.code = "0";
    }else{
      root.left = findAllLeaves(root.left, "0");
      root.right = findAllLeaves(root.right, "1");
    }
  }
  /**
   * a private recursion method that does the traversal task
   * visits every leaf node and encode them with a string
   * @param node a root of a subtree
   * @param str a representation of a code
   * @return reference to the root of a subtree
   */
  private HNode findAllLeaves(HNode node,String str){
    if(node.left == null && node.right == null){
      node.code = str;
      return node;
    }
    node.left = findAllLeaves(node.left, str + "0");
    node.right = findAllLeaves(node.right, str + "1");
    return node;
  }
  /**
   * print the HuffmanTree
   * public methods that uses a private recursive method
   */
  public void printHuffmanTree(){
    System.out.println("Huffman Tree:");
    if(root == null) return;
    System.out.println("node (" + root.frequency + ")");
    if(root.left!=null && root.right!=null){
      privatePrintTree("     ",root.left);
      privatePrintTree("     ",root.right);
    }
  }
  /**
   * private method that helps prints the tree recursively
   * @param space add space to the S.O.P depends on level of tree
   * @param node the root node of a subtrees or a leave
   */
  private void privatePrintTree(String space,HNode node){
    String str = "     ";
    if(node.left == null && node.right == null){
      if(node.character == '\n')
        System.out.println(space + "node (" + node.frequency + ") '\\n'");
      else if(node.character == '\t')
        System.out.println(space + "node (" + node.frequency + ") '\\t'");
      else
        System.out.println(space + "node (" + node.frequency + ") '"+node.character+"'");
    }else{
      System.out.println(space + "node (" + node.frequency + ")");
      privatePrintTree(space + str,node.left);
      privatePrintTree(space + str,node.right);
    }
  }
  /**
   * print the look up table for all characters in the tree
   */ 
  public void printLookUpTable(){
    System.out.println("symbol/code table:");
    Set<Character> set = characterMap.keySet();
    if(set.size() == 0) return;
    Iterator it = set.iterator();
    while(it.hasNext()){
      Character key = (Character)it.next();
      if(key == '\n')
        System.out.println("'\\n'" +"    "+ characterMap.get(key).code );
      else if(key == '\t')
        System.out.println("'\\t'" +"    "+ characterMap.get(key).code );
      else  
        System.out.println("'"+key+"'" +"    "+ characterMap.get(key).code );
    }
  }
  /**
   * creates new sring that have code values represents
   * the orginial text
   * print the code string
   * the string that represents the original string
   */ 
  public void printCode(){
    try{
      Scanner scan = new Scanner(selectedFile).useDelimiter("");
      while(scan.hasNext()){
        String string = scan.next();
        for(int i = 0; i < string.length(); i++){
          Character cr = string.charAt(i);
          codeString += characterMap.get(cr).code;
        }
      }
      scan.close();
    }catch(FileNotFoundException e){return;}
    System.out.println("encoded message:");
    System.out.println(codeString);
  }
  /**
   * print the character and frequency table
   */ 
  public void printFrequencyTable(){
    System.out.println("symbol frequency table:");
    for (Map.Entry<Character, HNode> entry : characterMap.entrySet()) {
      HNode node = entry.getValue();
      if(node == null) return;
      if(node.character == '\n')
        System.out.println("\\n" + "    " + Integer.toString(node.frequency));
      else if(node.character == '\t')
        System.out.println("\\t" + "    " + Integer.toString(node.frequency));
      else
        System.out.println(node.character + "    " + Integer.toString(node.frequency));
    }
  }
  /**
   * Makes thes the orgininal message from the codeString
   * prints the message
   */
  public void printMessage(){
    System.out.println("decoded message:");
    if(root == null) return;
    if(root.left != null && root.right !=null){
      message = "";
      String str = codeString.substring(0);
      HNode node = root;
      
      //the loop stops right after it reaches the last node
      while(str.length() != 0){
        String number = str.substring(0,1);
        //stores the character value into the message if
        //reaches leaves - sets node back to roots
        if(node.left == null && node.right == null){
          message += Character.toString(node.character);
          node = root;
        }
        //goes the the right if 1 - left if otherwise
        if(number.equals("1")){
          node = node.right;
        }else{
          node = node.left;
        }
        //update so can get to the next number
        str = str.substring(1);
      }
      //add the last character to the message
      message += Character.toString(node.character);
      System.out.println(message);
    }else{
      message = Character.toString(root.character);
      System.out.println(message);
    }
  }
  /**
   * Finds the average number of bits per symbol
   * prints that average number
   */
  public void printAverageBits(){
    System.out.println("average number of bits per symbol used in the encoded message:");
    if(root == null){
      System.out.println("N/A");
      return;
    }
    if(root.left != null && root.left!= null){
      averageBits = (double)codeString.length() / (double)message.length();
      System.out.println(averageBits);  
    }else{
      averageBits = 1;
      System.out.println(averageBits);  
    }
  }
  /**
   * main method that allows the program to run
   * calls the methods to show the properties of a HuffmanTree
   * @param args a string argument
   */
  public static void main(String[] args){
    HuffmanTree tree = new HuffmanTree();
    tree.printFrequencyTable();
    tree.printHuffmanTree();
    tree.printLookUpTable();
    tree.printCode();
    tree.printMessage();
    tree.printAverageBits();
  }
  /**
   * an inner class HNode
   * it is defined with a character, a frequency, and an encoded message
   * has links to the left and right sub child
   * 
   * implements Comparable interface in order to provide a compareTo method
   */ 
  class HNode implements Comparable<HNode>{
    private char character;
    private int frequency;
    private HNode left,right;
    private String code;
    /**
     * a default empty constructor
     */ 
    public HNode(){}
    /**
     * a constructor that creates a node based on 
     * given character and frequency
     * @param c a character
     * @param f a frequency
     */ 
    public HNode(char c, int f){
      character = c;
      frequency = f;
    }
    /**
     * a constructor that creates a node based on given 2 children
     * @param l a left sub child
     * @param r a right sub child
     */ 
    public HNode(HNode l, HNode r){
      frequency = l.frequency + r.frequency;
      left = l;
      right = r;
    }
    /**
     * implements the compareTo method
     * based on the frequency of the node to compare each other
     * @param node the node that need to be compared
     * @return 0 if 2 frequency are equals. 1 if greater. -1 if smaller
     */ 
    public int compareTo(HNode node){
      if(frequency - node.frequency == 0) return 0;
      if(frequency - node.frequency < 0) return -1;
      else return 1;
    }
  }
}