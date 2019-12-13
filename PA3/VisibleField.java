
// CS 455 PA3
// Fall 2018


/**
  VisibleField class
  This is the data that's being displayed at any one point in the game (i.e., visible field, because it's what the
  user can see about the minefield), Client can call getStatus(row, col) for any square.
  It actually has data about the whole current state of the game, including  
  the underlying mine field (getMineField()).  Other accessors related to game status: numMinesLeft(), isGameOver().
  It also has mutators related to moves the player could do (resetGameDisplay(), cycleGuess(), uncover()),
  and changes the game state accordingly.
  
  It, along with the MineField (accessible in mineField instance variable), forms
  the Model for the game application, whereas GameBoardPanel is the View and Controller, in the MVC design pattern.
  It contains the MineField that it's partially displaying.  That MineField can be accessed (or modified) from 
  outside this class via the getMineField accessor.  
 */
public class VisibleField {
   // ----------------------------------------------------------   
   // The following public constants (plus numbers mentioned in comments below) are the possible states of one
   // location (a "square") in the visible field (all are values that can be returned by public method 
   // getStatus(row, col)).
   
   // Covered states (all negative values):
   public static final int COVERED = -1;   // initial value of all squares
   public static final int MINE_GUESS = -2;
   public static final int QUESTION = -3;

   // Uncovered states (all non-negative values):
   
   // values in the range [0,8] corresponds to number of mines adjacent to this square
   
   public static final int MINE = 9;      // this loc is a mine that hasn't been guessed already (end of losing game)
   public static final int INCORRECT_GUESS = 10;  // is displayed a specific way at the end of losing game
   public static final int EXPLODED_MINE = 11;   // the one you uncovered by mistake (that caused you to lose)
   // ----------------------------------------------------------   
  
   /**
      Representation invariant:
      mineField is the underlying mine field, which is a reference to MineField class
      field is 2D array, which represents the visible field and have the same dimension of mine field
      totalRows is the total number of rows of both the mine field and the field
      totalColumns is the total number of columns of both the mine field and the field
      mineGuess is the number of mines guessed by user
      foundEmpties is number of non-mine locations found by user
      isExploded is true only if user explodes a mine
      totalRows > 0; totalColumns > 0; 
      mineGuess >= 0; 0 <= foundEmpties <= (totalRows*totalColumn - mineField.numMines())
    */
   private MineField mineField;
   private int[][] field;
   private int totalRows;
   private int totalColumns;
   private int mineGuess;
   private int foundEmpties;
   private boolean isExploded;

   /**
      Create a visible field that has the given underlying mineField.
      The initial state will have all the mines covered up, no mines guessed, and the game
      not over.
      @param mineField  the minefield to use for for this VisibleField
    */
   public VisibleField(MineField mineField) {
      
	  this.mineField = mineField;
	  mineGuess = 0;
	  foundEmpties = 0;
	  isExploded = false;
	  
      totalRows = mineField.numRows();
      totalColumns = mineField.numCols();
      field = new int[totalRows][totalColumns];
      for(int i = 0; i < totalRows; i++) {
    	  for(int j = 0; j < totalColumns; j++) {
    		  field[i][j] = COVERED;
    	  }
      }
      
   }
   
   
   /**
      Reset the object to its initial state (see constructor comments), using the same underlying MineField. 
   */     
   public void resetGameDisplay() {
	   mineGuess = 0;
	   foundEmpties = 0;
	   isExploded = false;
	   for(int i = 0; i < totalRows; i++) {
	    	  for(int j = 0; j < totalColumns; j++) {
	    		  field[i][j] = COVERED;
	    	  }
	      }    
   }
  
   
   /**
      Returns a reference to the mineField that this VisibleField "covers"
      @return the minefield
    */
   public MineField getMineField() {
      return mineField;       
   }
   
   
   /**
      get the visible status of the square indicated.
      @param row  row of the square
      @param col  col of the square
      @return the status of the square at location (row, col).  See the public constants at the beginning of the class
      for the possible values that may be returned, and their meanings.
      PRE: getMineField().inRange(row, col)
    */
   public int getStatus(int row, int col) {
    
	   return field[row][col];
   }

   
   /**
      Return the the number of mines left to guess.  This has nothing to do with whether the mines guessed are correct
      or not.  Just gives the user an indication of how many more mines the user might want to guess.  So the value can
      be negative, if they have guessed more than the number of mines in the minefield.     
      @return the number of mines left to guess.
    */
   public int numMinesLeft() {
      
	  return mineField.numMines() - mineGuess;       

   }
 
   
   /**
      Cycles through covered states for a square, updating number of guesses as necessary.  Call on a COVERED square
      changes its status to MINE_GUESS; call on a MINE_GUESS square changes it to QUESTION;  call on a QUESTION square
      changes it to COVERED again; call on an uncovered square has no effect.  
      @param row  row of the square
      @param col  col of the square
      PRE: getMineField().inRange(row, col)
    */
   public void cycleGuess(int row, int col) {
	  
	  if(field[row][col] == COVERED) {
		  field[row][col] = MINE_GUESS;
		  mineGuess++;
	  }
	  else if(field[row][col] == MINE_GUESS) {
		  field[row][col] = QUESTION;
		  mineGuess--;
	  }
	  else if(field[row][col] == QUESTION) {
		  field[row][col] = COVERED;
	  }
	       
   }

   
   /**
      Uncovers this square and returns false iff you uncover a mine here.
      If the square wasn't a mine or adjacent to a mine it also uncovers all the squares in 
      the neighboring area that are also not next to any mines, possibly uncovering a large region.
      Any mine-adjacent squares you reach will also be uncovered, and form 
      (possibly along with parts of the edge of the whole field) the boundary of this region.
      Does not uncover, or keep searching through, squares that have the status MINE_GUESS. 
      @param row  of the square
      @param col  of the square
      @return false   iff you uncover a mine at (row, col)
      PRE: getMineField().inRange(row, col)
    */
   public boolean uncover(int row, int col) {
  
	   if(mineField.hasMine(row, col)) {
		   field[row][col] = EXPLODED_MINE;  //hit a mine, game lose, explode
		   isExploded = true;
		   return false;
	   }
	  
	   revealEmpties(row,col);
	   return true;
	   
   }
 
    
   /**
      Returns whether the game is over.
      @return whether game over
    */
   public boolean isGameOver() {
	 
	   if(isExploded || foundEmpties == totalRows*totalColumns - mineField.numMines()) {
		   for(int i = 0; i < totalRows; i++) {
			   for(int j = 0; j < totalColumns; j++) {
				   if(mineField.hasMine(i, j) && field[i][j] != MINE_GUESS && field[i][j] != EXPLODED_MINE) {
					   field[i][j] = MINE;
				   }
				   else if(!mineField.hasMine(i, j) && field[i][j] == MINE_GUESS) {
					   field[i][j] = INCORRECT_GUESS;
				   }

			   }
		   }
		   return true;
	   }
	   return false;
	   
   }
 
   
   /**
      Return whether this square has been uncovered.  (i.e., is in any one of the uncovered states, 
      vs. any one of the covered states).
      @param row of the square
      @param col of the square
      @return whether the square is uncovered
      PRE: getMineField().inRange(row, col)
    */
   public boolean isUncovered(int row, int col) {
      return field[row][col] >= 0;
   }
   
 
   /**
      It is a helper method of the uncover() method
      Recursively opens all the covered squares in the region that aren't adjacent to mines 
      until it gets to the boundary of the field, or squares that are adjacent to other mines
      @param row of the square 
      @param col of the square 
      PRE: mineField.hasMine(row,col) = false
    */
   private void revealEmpties(int row, int col) {
	   
	   //return if the location is out the boundary 
	   if(!mineField.inRange(row, col)) {
		   return;
	   }
	   
	   //check if the square is uncovered or marked as MINE_GUESS
	   if(field[row][col] == MINE_GUESS || isUncovered(row,col)) {
		   return;
	   }
	   field[row][col] = mineField.numAdjacentMines(row, col);
	   foundEmpties++;
	   
	   //check if the square has adjacent mines
	   if(field[row][col] != 0) {
		   return;
	   }	   
       revealEmpties(row-1, col-1);
	   revealEmpties(row-1, col);
	   revealEmpties(row-1, col+1);
	   revealEmpties(row, col+1);
	   revealEmpties(row+1, col+1);
	   revealEmpties(row+1, col);
	   revealEmpties(row+1, col-1);
	   revealEmpties(row, col-1);
		   	  
   }
   

}


