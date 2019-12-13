
import java.util.Random;

/**
 * class CoinTossSimulator
 * 
 * Simulates trials of repeatedly tossing two coins and allows the user to access the
 * cumulative results.
 * 
 * Invariant: getNumTrials() = getTwoHeads() + getTwoTails() + getHeadTails()
 * 
 */
public class CoinTossSimulator {

   private int totalTrials;     //number of total trials simulated
   private int numTwoHeads;     //number of trials for outcomes of two heads
   private int numTwoTails;     //number of trials for outcomes of two tails
   private int numHeadTail;     //number of trials for outcomes of one head and one tail
   private int[] coins = new int[2];  //number of coins

   /**
      Creates a coin toss simulator with no trials done yet.
   */
   public CoinTossSimulator() {
      totalTrials = 0;
      numTwoHeads = 0;
      numTwoTails = 0;
      numHeadTail = 0;
   }


   /**
      Runs the simulation for numTrials more trials. Multiple calls to this method
      without a reset() between them *add* these trials to the current simulation.
      
      @param numTrials  number of trials to for simulation; must be >= 1
    */
   public void run(int numTrials) {
      
	  //get the total number of trails before reset
	  totalTrials = totalTrials + numTrials;    
	  
	  //simulate tossing a pair of coins numTrials times
	  for(int count = 0; count < numTrials; count++)
	  {
		  //construct an object of Random class to generate random results
		  Random generator = new Random();
		  coins[0] = generator.nextInt(2);  //outcome for one of the coins
		  coins[1] = generator.nextInt(2);  //outcome for the another coin
		  
		  if(coins[0] == 0)        //first coin is tail
		  {
			  if(coins[1] == 1)    //2nd coin is head
			  {
				  numHeadTail++;
			  }
			  else                 //2nd coin is tail
				  numTwoTails++;   
		  }
		  else                     //1st coin is head
		  {
			  if(coins[1] == 1)    //2nd coin is head
			  {
				  numTwoHeads++;  
			  } 
			  else                 //2nd coin is tail
				  numHeadTail++;   
		  }
	  }
	   
   }


   /**
      Get number of trials performed since last reset.
   */
   public int getNumTrials() {
       return totalTrials; 
   }


   /**
      Get number of trials that came up two heads since last reset.
   */
   public int getTwoHeads() {
       return numTwoHeads; 
   }


   /**
     Get number of trials that came up two tails since last reset.
   */  
   public int getTwoTails() {
	   return numTwoTails; 
   }


   /**
     Get number of trials that came up one head and one tail since last reset.
   */
   public int getHeadTails() {
       return numHeadTail; 
   }


   /**
      Resets the simulation, so that subsequent runs start from 0 trials done.
    */
   public void reset() {
       totalTrials = 0;
       numTwoHeads = 0;
       numTwoTails = 0;
       numHeadTail = 0;
   }

}

