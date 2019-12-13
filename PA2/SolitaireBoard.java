

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/*
  class SolitaireBoard
  The board for Bulgarian Solitaire.  You can change what the total number of cards is for the game
  by changing NUM_FINAL_PILES, below.  Don't change CARD_TOTAL directly, because there are only some values
  for CARD_TOTAL that result in a game that terminates.
*/
public class SolitaireBoard {
   
   public static final int NUM_FINAL_PILES = 9;
   // number of piles in a final configuration
   
   public static final int CARD_TOTAL = NUM_FINAL_PILES * (NUM_FINAL_PILES + 1) / 2;
   // bulgarian solitaire only terminates if CARD_TOTAL is a triangular number.
   
   /**
      Representation invariant:
      pilesOfCards is the number of piles, the capacity = pilesOfCards.length = CARD_TOTAL
      currentSize is the current piles
      0 < every piles <= CARD_TOTAL 
      0 <= currentSize must <= CARD_TOTAL
      if curretnSize >0, the location of piles is: [0, currentSize-1]
      the sum of all the elements in pilesOfCards = CARD_TOTAL
   */
   private int[] pilesOfCards = new int[CARD_TOTAL];; 
   private int currentSize = 0;

 
   /**
      Creates a solitaire board with the configuration specified in piles.
      piles has the number of cards in the first pile, then the number of cards in the second pile, etc.
      PRE: piles contains a sequence of positive numbers that sum to SolitaireBoard.CARD_TOTAL
   */
   public SolitaireBoard(ArrayList<Integer> piles) {
	   
      for(int i = 0; i < piles.size(); i++)
      {
    	  pilesOfCards[i] = piles.get(i);
      }
      currentSize = piles.size();
      
      assert isValidSolitaireBoard();
   }
 
   
   /**
      Creates a solitaire board with a random initial configuration.
   */
   public SolitaireBoard() {
	   
	   Random generator = new Random(); //create a Random generator
	   
	   int cardsLeft = CARD_TOTAL; //current number of cards
	   while(cardsLeft > 0)
	   {
		   pilesOfCards[currentSize] = generator.nextInt(cardsLeft) + 1;
		   cardsLeft = cardsLeft - pilesOfCards[currentSize];
		   currentSize = currentSize + 1;
	   }
	   
	   assert isValidSolitaireBoard();
   }
  
   
   /**
      Plays one round of Bulgarian solitaire.  Updates the configuration according to the rules
      of Bulgarian solitaire: Takes one card from each pile, and puts them all together in a new pile.
      The old piles that are left will be in the same relative order as before, 
      and the new pile will be at the end.
   */
   public void playRound() {
	   
	   int newPile = 0; //number of cards in the new pile
	   for(int i = 0; i < currentSize; i++)
	   {
		   pilesOfCards[i] = pilesOfCards[i] - 1;
		   newPile += 1;
	   }
	   pilesOfCards[currentSize] = newPile;
	   currentSize += 1;
	  
	   int loc = 0;  //for the location of the new array without 0
	   for(int i = 0; i < currentSize; i++)
       {
		   if(pilesOfCards[i] > 0)
		   {
			   pilesOfCards[loc] = pilesOfCards[i];
			   loc++;
		   }
       }
	   currentSize = loc;
	  
	   assert isValidSolitaireBoard();
   }
   
   /**
      Returns true iff the current board is at the end of the game.  That is, there are NUM_FINAL_PILES
      piles that are of sizes 1, 2, 3, . . . , NUM_FINAL_PILES, in any order.
   */  
   public boolean isDone() {
	   
	  //if exist is true, then the number is already exist in the array 
	  boolean[] exist = new boolean[NUM_FINAL_PILES + 1];
      
	  //the size of the array at the end of game must be NUM_FINAL_PILES
	  if(currentSize != NUM_FINAL_PILES)
      {
    	 return false;
      }
      
	  //every piles at the end of game must <=NUM_FINAL_PILES
	  //Also, number of piles should be unique
      for(int i = 0; i < currentSize; i++) 
      {
    	  if(pilesOfCards[i] <= NUM_FINAL_PILES) 
    	  {
    		  if(exist[pilesOfCards[i]]) 
    		  {
    			  return false;
    		  }
    		  exist[pilesOfCards[i]] = true;
    	  }
    	  else 
    	  {
    		  return false;
    	  }
      }
      
      assert isValidSolitaireBoard();
      return true;
   }

   
   /**
      Returns current board configuration as a string with the format of
      a space-separated list of numbers with no leading or trailing spaces.
      The numbers represent the number of cards in each non-empty pile.
   */
   public String configString() {
       
	   String numbers = "";
	   //save every number in pilesOfCards as a string
	   for(int i = 0; i < currentSize; i++) 
	   { 
		   numbers = numbers + pilesOfCards[i] + " ";
	   }	
	   assert isValidSolitaireBoard();
	   return numbers.trim(); //return string with deleted leading or trailing spaces
   }
   
   
   /**
      Returns true iff the solitaire board data is in a valid state
      (See representation invariant comment for more details.)
   */
   private boolean isValidSolitaireBoard() {
      
	  int sum = 0; //sum of elements in pilesOfCards
	  
	  //check if it's partially filled array
	  if(currentSize >= CARD_TOTAL)
	  {
		  return false;
	  }
	  else 
      {
		  for(int i = 0; i < currentSize; i++)
		  {
			  //check if elements in pilesOfCards > 0 and <= CARD_TOTAL
			  if(pilesOfCards[i] <= 0 || pilesOfCards[i] > CARD_TOTAL)
			  {
				  return false;
			  }
			  sum = sum + pilesOfCards[i];
		  }
		  //check for the sum of elements
		  if(sum != CARD_TOTAL)
		  {
			  return false;
		  }
		  return true;
      }
   }
   

   /**
      Return String of current elements in pilesOfCards
      For debug purpose only 
    */
   public String toString(){
	   
	  return Arrays.toString(pilesOfCards);
	
   }
   

}