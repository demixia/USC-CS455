
import java.util.Scanner;
import javax.swing.JFrame;

/**
   Prompts for the number of trials, and creates the JFrame containing the CoinSimComponent.
   Entering the number of trials (should be greater than 0), the bar chart will be created to show the outcomes
 */
public class CoinSimViewer
{
   public static void main(String[] args)
   {
      JFrame frame = new JFrame();  //construct an object of JFrame class
      frame.setSize(800, 500);      //set the size of the frame: 800 pixels wide and 500 pixels tall
      frame.setTitle("CoinSim");    //set the title of the frame
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //set the default close operation 
      
      //read the number of trials from keyboard
      Scanner in = new Scanner(System.in);  
      System.out.print("Enter number of trials: ");
      int trials = in.nextInt();
      
      //error checking to ensure a positive value is entered
      while(trials <= 0)
      {
    	  System.out.println("ERROR: NUmber entered must be greater than 0.");  
    	  System.out.print("\nEnter number of trials: ");
    	  trials = in.nextInt();
      }
      System.out.println("Bar Chart Built Successfully !");
      in.close();
      
      CoinSimComponent component = new CoinSimComponent();  //construct an object of CoinSimComponent class
      component.tossSim(trials);    //simulate trials
      frame.add(component);         //add the component to the frame
      frame.setVisible(true);       //make the frame visible

   }
}
