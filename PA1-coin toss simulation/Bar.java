
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;

/**
 * Bar class
 * A labeled bar that can serve as a single bar in a bar graph.
 * The text for the label is centered under the bar.
 * 
 */
public class Bar {
   
      private int bottom;     //location of the bottom of the label
      private int left;       //location of the left side of the bar
      private int width;      //width of the bar (in pixels)
      private int barHeight;  //height of the bar in application units
      private double scale;   //how many pixels per application unit
      private Color color;    //the color of the bar
      private String label;   //the label at the bottom of the bar

   /**
      Creates a labeled bar. 
      
      @param bottom location of the bottom of the label
      @param left location of the left side of the bar
      @param width width of the bar (in pixels)
      @param barHeight height of the bar in application units
      @param scale how many pixels per application unit
      @param color the color of the bar
      @param label the label at the bottom of the bar 
   */
   public Bar(int bottom, int left, int width, int barHeight, double scale, Color color, String label) 
   {
      this.bottom = bottom;
      this.left = left;
      this.width = width;
      this.barHeight = barHeight;
      this.scale = scale;
      this.color = color;
      this.label = label;
   }
   
   /**
      Draw the labeled bar. 
      @param g2  the graphics context
   */
   public void draw(Graphics2D g2) 
   {
	   //get the font and bounds of label
	   Font font = g2.getFont();
   	   FontRenderContext context = g2.getFontRenderContext();
       Rectangle2D labelBounds = font.getStringBounds(label, context);
      
       //get the width and the height of the label
   	   int widthOfLabel = (int) labelBounds.getWidth();      
   	   int heightOfLabel =(int) labelBounds.getHeight();     
	   
   	   //get the x location of the label
   	   int xOfLabel = left + (width-widthOfLabel)/2;             
   	   
	   int height = (int) (barHeight*scale);  //height of bar in pixels
	   int yOfRect = bottom - heightOfLabel- height;  //y position of the rectangle
	   
	   //construct an object of Rectangle class
	   Rectangle rect = new Rectangle (left, yOfRect, width, height); 
	  
	   g2.setColor(Color.BLACK);   //set color of string label to black
	   g2.drawString(label,xOfLabel , bottom);  //draw the label
	   
	   //ensure there is no rectangle to be draw
	   //if the height of the rectangle <= 0
	   if(height > 0)
	   {
		   g2.setColor(color);  //set the color of the bar
		   g2.draw(rect);  //draw the rectangle
		   g2.fill(rect);  //fill the rectangle with color
	   }
	   	   
   }
}
