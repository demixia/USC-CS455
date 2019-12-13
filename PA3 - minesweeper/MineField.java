
// CS 455 PA3
// Fall 2018

import java.util.Random;

/** 
   MineField
      class with locations of mines for a game.
      This class is mutable, because we sometimes need to change it once it's created.
      mutators: populateMineField, resetEmpty
      includes convenience method to tell the number of mines adjacent to a location.
 */
public class MineField {
   
   /**
      representation invariant:
      mineData is a 2D array, if mineData[row][col] is true, it indicates there is a mine in this location
      numRows is the total number of rows of the mine field
      numCols is the total number of columns of the mine field
      numMines is the total number of mines in the mine field, which is also the number of trues in mineData
      numRows > 0; numCols > 0; 
      0 <= numMines < (1/3 of total number of mine field locations)
    */
   private boolean[][] mineData; 
   private int numRows; 
   private int numCols; 
   private int numMines; 
   
   
   /**
      Create a minefield with same dimensions as the given array, and populate it with the mines in the array
      such that if mineData[row][col] is true, then hasMine(row,col) will be true and vice versa.  numMines() for
      this minefield will corresponds to the number of 'true' values in mineData.
    * @param mineData  the data for the mines; must have at least one row and one col.
    */
   public MineField(boolean[][] mineData) {    
	   
	   this.mineData = mineData;
	   numRows = mineData.length;
	   numCols = mineData[0].length;
	   
       for(int i = 0; i < numRows; i++) {
	    	  for(int j = 0; j < numCols; j++) {
	    		  if(mineData[i][j]) {
	    			 numMines++;
	    		  }  		  
	    	  }
	   }
	   
   }
   
   
   /**
      Create an empty minefield (i.e. no mines anywhere), that may later have numMines mines (once 
      populateMineField is called on this object).  Until populateMineField is called on such a MineField, 
      numMines() will not correspond to the number of mines currently in the MineField.
      @param numRows  number of rows this minefield will have, must be positive
      @param numCols  number of columns this minefield will have, must be positive
      @param numMines   number of mines this minefield will have,  once we populate it.
      PRE: numRows > 0 and numCols > 0 and 0 <= numMines < (1/3 of total number of field locations). 
    */
   public MineField(int numRows, int numCols, int numMines) {   
	   
	   this.numRows = numRows;
	   this.numCols = numCols;
	   this.numMines = numMines;    
	   mineData = new boolean[numRows][numCols];

   }
   

   /**
      Removes any current mines on the minefield, and puts numMines() mines in random locations on the minefield,
      ensuring that no mine is placed at (row, col).
      @param row the row of the location to avoid placing a mine
      @param col the column of the location to avoid placing a mine
      PRE: inRange(row, col)
    */
   public void populateMineField(int row, int col) {
	   
	   for(int i = 0; i < numRows; i++) {
	       for(int j = 0; j < numCols; j++) {
	    	   if(mineData[i][j]) {
	    		   mineData[i][j] = false;
	    	   }  		  
	       }
	  }

      int currentMines = 0; //current number of mines in the field
      
      //generate random mines
      Random generator = new Random();
      while(currentMines < numMines) {
    	  int mineRow = generator.nextInt(numRows);  
          int mineCol = generator.nextInt(numCols);
          
          //make sure no mine is placed at (row,col), and no repeat mine locations
          if((mineRow != row || mineCol != col) && !mineData[mineRow][mineCol]) {
        	  mineData[mineRow][mineCol] = true;
        	  currentMines++;
          }       
      }
   }
   
   
   /**
      Reset the minefield to all empty squares.  This does not affect numMines(), numRows() or numCols()
      Thus, after this call, the actual number of mines in the minefield does not match numMines().  
      Note: This is the state the minefield is in at the beginning of a game.
    */
   public void resetEmpty() {
      
      for(int i = 0; i < numRows; i++) {
    	  for(int j = 0; j < numCols; j++) {
    		  if(mineData[i][j]) {
    			  mineData[i][j] = false;
    		  }  		  
    	  }
      }
   }

   
  /**
     Returns the number of mines adjacent to the specified mine location (not counting a possible 
     mine at (row, col) itself).
     Diagonals are also considered adjacent, so the return value will be in the range [0,8]
     @param row  row of the location to check
     @param col  column of the location to check
     @return  the number of mines adjacent to the square at (row, col)
     PRE: inRange(row, col)
   */
   public int numAdjacentMines(int row, int col) {
       int numAdjacent = 0; //the number of adjacent mines
       
       //ignore the edge case, assume we generally start search mines from the the row before to the row after
       //and the column before to the column after
       int startRow = row-1; 
       int startCol = col-1; 
	   int endRow = row+1; 
	   int endCol = col+1; 
       
	   //up edge case and down edge case
	   //make sure the start row and end row for searching mines are in field
       if(startRow < 0) {
    	   startRow = row;
       }	   
       if(endRow >= numRows) {
    	   endRow = row;
       }

       //left edge and right edge case
       //make sure the start column and end column for searching mines are in field
	   if(startCol < 0) {
		   startCol = col;
	   }
	   if(endCol >= numCols) {
		   endCol = col;
	   }
	   
	   for(int i = startRow; i <= endRow; i++) {		   
		   for(int j = startCol; j <= endCol; j++) {
			   if(!(i == row && j == col) && mineData[i][j]) {
				   numAdjacent++;
			   }
		   }
	   }
	   return numAdjacent;      
   }
   
   
   /**
      Returns true iff (row,col) is a valid field location.  Row numbers and column numbers
      start from 0.
      @param row  row of the location to consider
      @param col  column of the location to consider
      @return whether (row, col) is a valid field location
   */
   public boolean inRange(int row, int col) {
      
	   return (row >= 0) && (row < numRows) && (col >= 0) && (col < numCols);       
   }
   
   
   /**
      Returns the number of rows in the field.
      @return number of rows in the field
   */  
   public int numRows() {
      return numRows;   
   }
   
   
   /**
      Returns the number of rows in the field.
      @return number of rows in the field
   */    
   public int numCols() {
      return numCols;    
   }
   
   
   /**
      Returns whether there is a mine in this square
      @param row  row of the location to check
      @param col  column of the location to check
      @return whether there is a mine in this square
      PRE: inRange(row, col)   
   */    
   public boolean hasMine(int row, int col) {
	   return mineData[row][col];      
   }
   
   
   /**
      Returns the number of mines you can have in this minefield.  For mines created with the 3-arg constructor,
      some of the time this value does not match the actual number of mines currently on the field.  See doc for that
      constructor, resetEmpty, and populateMineField for more details.
    * @return
    */
   public int numMines() {
      return numMines;       
   }

}