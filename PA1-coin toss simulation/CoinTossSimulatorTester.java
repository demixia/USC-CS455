
import java.util.Random;

/**
 * Test the CoinTossSimulator class, and run the tossing simulation 20 times automatically
 * Every run time simulate tossing coins many number of trials, and the number of trials is a random number within 1000
 * The simulation is reset at the 15th run automatically
 * The outcome will show the number of two heads, tow tails, a head and a tail for each run
 * Each run will error check if the total number of trials equals the expected value
 * Besides, it will also error check if the sum of the three outcomes equals the total number of trials simulated
 */
public class CoinTossSimulatorTester {
	
	public static void main(String[] args) {
		
		CoinTossSimulator sim = new CoinTossSimulator();  //construct an object of CoinTossSimulator class
		Random generator = new Random();   //construct an object of Random class
		
		int exp = 0;     //initial expected value for number of trials;
		
		//run simulation 20 times 
		for(int i = 0; i < 20; i++)
		{
			//reset at 15th run
			if(i == 14)
			{
				sim.reset();
				exp = 0; //reset the expected value
				
				//get the number of each outcome
				int totalTrials = sim.getNumTrials();
				int twoHeads = sim.getTwoHeads();
				int twoTails = sim.getTwoTails();
				int headTails = sim.getHeadTails();
				System.out.println( (i+1) + " RUN (RESET)");
				
				System.out.println("Number of trials [exp: " + exp + "]: " + totalTrials);
				System.out.println("Two head tosses: " + twoHeads);
				System.out.println("Two tail tosses: " + twoTails);
				System.out.println("One-head and one-tail tosses: " + headTails);
				System.out.print("Tosses add up correctly? ");	
				
				//error check if the total trials matches the expected value
				if(totalTrials == twoHeads + twoTails + headTails)
				{
					System.out.println("ture\n");
				}
				else 
					System.out.println("false\n");
			}
			else
			{
				//every time run, simulate tossing coins trials times
				//trials is a random number within 1000
				int trials = generator.nextInt(1000) + 1;
				System.out.println( (i+1) + " RUN (" + trials +")");
				
				//expected total trials
				exp = exp + trials;   
				
				//run the simulation and get the number of each outcome
				sim.run(trials);
				int totalTrials = sim.getNumTrials();  
				int twoHeads = sim.getTwoHeads();
				int twoTails = sim.getTwoTails();
				int headTails = sim.getHeadTails();
				
				System.out.println("Number of trials [exp: " + exp + "]: " + totalTrials);
				System.out.println("Two head tosses: " + twoHeads);
				System.out.println("Two tail tosses: " + twoTails);
				System.out.println("One-head and one-tail tosses: " + headTails);
				System.out.print("Tosses add up correctly? ");
				
				//error check if the total trials matches the expected value
				if(totalTrials == twoHeads + twoTails + headTails)
				{
					System.out.println("ture\n");
				}
				else 
					System.out.println("false\n");
			}
					
		}
		
	}

}