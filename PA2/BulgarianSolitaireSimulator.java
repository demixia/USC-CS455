
import java.util.ArrayList;
import java.util.Scanner;

/**
  Simulate the Bulgarian Solitaire game, and print configuration until game end.
  When -u argument is supplied, entering initial configuration, and numbers entered are positive integer, 
  and the sum is CARD_TOTAL. numbers are space-separated.
  Otherwise, a random initial configuration will be generated. 
  When -s argument is supplied, stop after every round and wait for user to hit the enter to continue.
  Besides,  while running the program, always supply -ea argument for assert-checking
*/
public class BulgarianSolitaireSimulator {

   public static void main(String[] args) {
     
	  ArrayList<Integer> piles =  new ArrayList<Integer>();  //create an ArrayList to save the numbers entered by user
      SolitaireBoard board;  //create a SolitaireBorad reference 
	  Scanner in = new Scanner(System.in); 
      
	  //check whether -u argument or -s argument or both of them are supplied
	  boolean singleStep = false;
      boolean userConfig = false;
      
      for (int i = 0; i < args.length; i++) {
         if (args[i].equals("-u")) {
            userConfig = true;
         }
         else if (args[i].equals("-s")) {
            singleStep = true;
         }
      }
      
      //print configuration according to argument supplied
      if(userConfig == true)
      {
    	  checkInput(in,piles);  
    	  board = new SolitaireBoard(piles); 
    	  printConfig(singleStep, in,board);  	  
      }
      else 
      {
    	  board = new SolitaireBoard();
    	  printConfig(singleStep,in,board);
      }
      
   }
   
  /**
     Print the initial configuration of the board and the configuration after each playRound. 
     If -s argument is supplied, it will wait user to hit enter to print configuration of next round until game terminate.
     Otherwise, it print the configuration of each round automatically until game terminate.
     @param singleStep  whether the -s argument is supplied
     @param in  a Scanner object, which reads from keyboard
     @param sBoard  a SolitaireBoard object
   */
   private static void printConfig(boolean singleStep,Scanner in, SolitaireBoard sBoard){

	   System.out.println("Initial configuration: " + sBoard.configString());
	   int numberOfRuns = 0;   //counts the number of rounds played
	   
	   while(!sBoard.isDone())
	   {
		   sBoard.playRound();
		   numberOfRuns += 1;
		   System.out.print("["+ numberOfRuns +"] Current configuration: " + sBoard.configString());
	       if(singleStep)
		   {
			   //wait for user to hit enter to continue. 
			   in.nextLine();
		   }
		   else
		   {
			   System.out.print("\n");
		   }	   
	   }
	   System.out.println("Done!");	   
   }
   
   /**
      While -u argument is supplied, it check whether user inputs positive integers, whether the sum is CARD_TOTAL.
      If not, showing error message to alert user, and asking them to enter again until numbers entered matches requirements.
      Save the correct input numbers in an ArrayList.
      @param in  a Scanner object, which reads inputs from keyboard
      @param numbers  the correct numbers entered, should be positive integers that sum to CARD_TOTAL
    */
   private static void checkInput(Scanner in, ArrayList<Integer> numbers) {
		  
	   System.out.println("Number of total cards is " + SolitaireBoard.CARD_TOTAL);
	   System.out.println("You will be entering the initial configuration of the cards (i.e., how many in each pile).");      
	   System.out.println("Please enter a space-separated list of positive integers followed by newline:");
	   
	   //continue until get correct numbers from user
	   while(in.hasNextLine()) 
	   {
	       String str = in.nextLine();   //read the whole line input at once
		   Scanner lineScanner = new Scanner(str);
	       int sum = 0; //sum of elements saved in ArrayLlist numbers
	       
	       //put all nonzero integer into array list 
	       while(lineScanner.hasNextInt()) 
	       {
	    	   int num = lineScanner.nextInt();
	    	   if(num > 0) 
	    	   {
	    		   numbers.add(num);
	    		   sum+=num;
	    	   }
	    	   else 
	    	   {
	    		   numbers.clear();
	    		   sum = 0;
	    		   break;
	    	   }
	       }
	       
	       //determine whether all the input have been read and the sum equals CARD_TOTAL
	       if(!lineScanner.hasNext() && sum == SolitaireBoard.CARD_TOTAL) {
	    	   break;
	       }
	       numbers.clear();
	       System.out.println("ERROR: Each pile must have at least one card and the total number of cards must be " + SolitaireBoard.CARD_TOTAL);
	       System.out.println("Please enter a space-separated list of positive integers followed by newline:");
	  } 
   }
}