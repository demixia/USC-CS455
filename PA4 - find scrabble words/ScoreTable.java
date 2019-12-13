
// CS 455 PA4
// Fall 2018


/**
   A score table to find the score of a legal string, and upper case letter and lower case letter have the same score
 */
public class ScoreTable{
   
   private static final int ALPHABETS = 26;  //the size of the score table
   private static final char REFERENCE = 'a'; //the reference character used to index the score array with alphabet letter
   
   /**
      representation invariant:
      score is a array of int, it stores the scores of letters a-z in order      
      score.length = ALPHABETS;
    */
   private int[] score;
   
   
   /**
      Create an array of score table of size ALPHABETS and set the corresponding score of each lower case letter
    */
   public ScoreTable(){
   
      score = new int[ALPHABETS];
      setScore();
   }
   
   /**
      Find the score of a legal word. 
      Convert all the characters in the string into lower case, 
      then we can index the array using the char in the string minus the reference letter 'a' 
      @param s the legal string word that needs to know the score
      @return the score of the word
    */
   public int findScore(String s){
   
      String lowerCase = s.toLowerCase();
      int sum = 0;
      for(int i = 0; i< lowerCase.length(); i++){
         sum += score[lowerCase.charAt(i)-REFERENCE];
      }
      return sum;
   }
   
   
   /**
      Set the corresponding score of each lower letter,
      upper case letter and lower case letter have the same score.
    */
   private void setScore(){
   
      score[0] = 1; //score of a
      score[1] = 3; //score of b
      score[2] = 3; //score of c
      score[3] = 2; //score of d
      score[4] = 1; //score of e
      score[5] = 4; //score of f
      score[6] = 2; //score of g
      score[7] = 4; //score of h
      score[8] = 1; //score of i
      score[9] = 8; //score of j
      score[10] = 5; //score of k
      score[11] = 1; //score of l
      score[12] = 3; //score of m
      score[13] = 1; //score of n
      score[14] = 1; //score of o
      score[15] = 3; //score of p
      score[16] = 10; //score of q
      score[17] = 1; //score of r
      score[18] = 1; //score of s
      score[19] = 1; //score of t
      score[20] = 1; //score of u
      score[21] = 4; //score of v
      score[22] = 4; //score of w
      score[23] = 8; //score of x
      score[24] = 4; //score of y
      score[25] = 10; //score of z
   }
   
}