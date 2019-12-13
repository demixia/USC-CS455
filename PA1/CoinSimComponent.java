
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import javax.swing.JComponent;

/**
   This component simulate coin tosses and draws three bars.
*/
public class CoinSimComponent extends JComponent
{
	private static final int NUMBER_OF_BARS = 3;      //constant value of number of bars
	private static final int VERTICAL_MARGIN = 30;    //fixed vertical buffer in pixels
    private static final int BAR_WIDTH = 100;         //fixed width of the bar (in pixels)
    
	private int numOfTrials;                   //total number of trials
    private double scale;                      //how many pixels per application unit
	private int bottom;                        //location of the bottom of the label
	private String label1, label2, label3;    //the label of each bar at the bottom of the bar
	
	private int[] left = new int[3];           //location of the left side of each bar
    private int[] barHeight = new int[3];      //height of each bar in application units
	private long[] percentage = new long[3];     //percentage of each bar 
	
	/**
	 Simulate tossing a pair of coins several times. 
	 Get the total number of trials executed, and the number of each outcome.
	 Find the percentage of each outcome
	 
	 @param number the total number of trials entered form keyboard
	 */
    public void tossSim(int number)
    { 
    	//construct an object of CoinTossSimulator class
    	CoinTossSimulator coin = new CoinTossSimulator(); 
    	coin.run(number);                    //run the simulation of tossing trials
    	numOfTrials = coin.getNumTrials();   //get total number of trials
        barHeight[0] = coin.getTwoHeads();   //get the number of two heads
        barHeight[1] = coin.getHeadTails();  //get the number of a head and a tail
        barHeight[2] = coin.getTwoTails();   //get the number of two tails
     
        // find the percent value for each bar
        for(int i = 0; i < percentage.length; i++)
        {
        	percentage[i] = Math.round(barHeight[i]*100.0/numOfTrials);
        }
    }
	   
    /**
     get the label and draw the three bars
     @param g graphics content
     */
    public void paintComponent(Graphics g)
    {
    	//recover Graphics2D
    	Graphics2D g2 = (Graphics2D) g;               
   
    	// get the width and height in pixels of the frame
        int panelWidth = getWidth();                  
        int panelHeight = getHeight();               
        
        // determine the width between each bar in pixels
        int horiMargin = (panelWidth-NUMBER_OF_BARS * BAR_WIDTH)/(NUMBER_OF_BARS+1);    
        
        // label each bar
        label1 = "Two Heads: "+ barHeight[0] + "(" + percentage[0] +"%)" ;
    	label2 = "A Head and A Tail: "+ barHeight[1] + "(" + percentage[1] +"%)" ;
    	label3 = "Two Tails: "+ barHeight[2] + "(" + percentage[2] +"%)" ;
    	
    	//get the font and bounds of label and find the height of one of the labels for calculate scale
    	//since the heights of the label are the same
        Font font = g2.getFont();
    	FontRenderContext context = g2.getFontRenderContext();
        Rectangle2D labelBounds = font.getStringBounds(label1, context);
    	int heightOfLabel =(int) labelBounds.getHeight();
    	
        // get the scale
        scale = (double) (panelHeight - VERTICAL_MARGIN *2 - heightOfLabel)/numOfTrials;   
      
        //get the location of the bottom of label
        bottom = panelHeight - VERTICAL_MARGIN;
        
        //the location of the left side of each bar
        for(int i = 0; i < left.length; i++)
        {
        	left[i] = (i+1)*horiMargin + i*BAR_WIDTH; 
        }
	    
        //construct three objects of Bar class
        Bar bar1 = new Bar(bottom, left[0], BAR_WIDTH, barHeight[0], scale, Color.RED, label1);
        Bar bar2 = new Bar(bottom, left[1], BAR_WIDTH, barHeight[1], scale, Color.GREEN, label2);
        Bar bar3 = new Bar(bottom, left[2], BAR_WIDTH, barHeight[2], scale, Color.BLUE, label3);
        
        //draw the three bars
        bar1.draw(g2);
        bar2.draw(g2);
        bar3.draw(g2);
            
   }
}