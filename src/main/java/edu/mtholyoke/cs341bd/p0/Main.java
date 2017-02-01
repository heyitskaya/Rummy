package edu.mtholyoke.cs341bd.p0;

public class Main {
  public static void main(String[] args) {
    System.out.println("Programming Assignment #0: Rummy Scoring");
    System.out.println("CS341 - Building a Digital Library");
    // TODO adjust your author name.
    System.out.println("Author: John Foley");

    while(true) {
    
      String input = CLI.readString("Input> ");
      if(input == null || input.length() == 0) {
    	 System.out.println("kkkkkk");
        break;
      }
      System.out.println("input is "+input);
      System.out.println("You have entered: ");
      System.out.println("\t"+input);
      System.out.println();
      Scrummy game= new Scrummy(input);
     
      //KS 2H 3H 4H 5H 5D 5S
      //AH 2H 3H 5C 5D 5S JH JD QH KH 9H 9C 9D 9S

      game.findRun();
      System.out.println("************");
     // System.out.println("runs found");
      game.findSet();
     
      System.out.println("************");
      System.out.println("cards left");
      game.printCurrentCards();
      System.out.println("************");
      game.calcScore(); 
		
      
      
      
    
    
    }
  }
}
