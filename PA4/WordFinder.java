
// CS 455 PA4
// Fall 2018


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Collections;
import java.util.Comparator;

/**
   WordFinder--the mian class
   A Scrabble game helper to find all the words from dictionary and show their corresponding scores.
   When given letters that could comprise a Scrabble rack, 
   it creates a list of all legal words that can be formed from the letters on that rack.
   To run it from command line using specified dictionary file(eg. dictionary.txt): java WordFinder dictionary.txt
   If there is no file name entered in commandline, this program will use default file sowpods.txt
   Type . to end the program 
 */
public class WordFinder{
 
   
   public static void main(String[] args) throws IOException{
   
      try{

         String fileName = "sowpods.txt"; //the default dictionary file name
         System.out.println("Type . to quit.");
         Scanner in = new Scanner(System.in);
         
         while(true){
            
            //check if user want to use a specified dictionary file
            if(args.length > 0){
               fileName = args[0];
            }
            
            //create an AnagramDictionary object, which contains the dictionary data organized by anagrams
            AnagramDictionary dictionary = new AnagramDictionary(fileName);
            
            //get the rack entered by user, stop if user enters .
            System.out.print("Rack? ");
            String input = in.next();
            if(input.equals(".")){
               break;
            }
            in.nextLine();

            //print the scores and all legal words according to the entered rack 
            legalWordsFinder(dictionary,input);
         }
     
      }
      catch(FileNotFoundException e){
         System.out.println("File Not Found");
      }
      
   }
   
   
   /**
      A helper method to find all the legal words and corresponding scores, and show the results
      @param dictionary the dictionary used to find all the legal words of the input
      @param input the string input by user 
    */
   private static void legalWordsFinder(AnagramDictionary dictionary, String input){
    
      //create a hash map to store strings with the same score together
      Map<Integer, ArrayList<String>> results = new HashMap<Integer, ArrayList<String>>();
      
      //create a ScoreTable object
      ScoreTable table = new ScoreTable();
      
      //create a Rack object which contains the legal letters entered by user
      //and find all the subsets of the legal string
      Rack rack = new Rack(input);
      ArrayList<String> words = rack.findSubsets();
         
      int numWords = 0; //the total number of words we can get from dictionary according to the user input     
      
      //find all legal words and scores and save it in the results hash map
      for(int i = 0; i < words.size(); i++){
         
         ArrayList<String> helper = dictionary.getAnagramsOf(words.get(i));
         
         if(helper.size()>0){
            numWords += helper.size();
            int score = table.findScore(helper.get(0));
            if(results.containsKey(score)){
               helper.addAll(results.get(score));
            }
            results.put(score, helper);
                  
         }
      }
      System.out.println("We can make "+ numWords +" words from " + '"' + input +'"');
      
      //if there are results, show all
      if(numWords > 0){
         printResults(results);
      }
      
   }
   
   /**
      A helper method to show all the resluts in the decreasing order of scores
      if the score of the words are the same, show in the alpahbet order
      @param results the map that stores all the words and corresponding  
    */
   private static void printResults(Map<Integer, ArrayList<String>> results){
      
      //restore the results in the map in an arraylist
      ArrayList<Map.Entry<Integer, ArrayList<String>>> byScores = new ArrayList<Map.Entry<Integer, ArrayList<String>>>(results.entrySet());
      //sort the arraylsit in the order of decreasing scores
      Collections.sort(byScores, new SortByScore());
      
      System.out.println("All of the words with their scores (sorted by score):");
      
      //print all the words and corresponding score
      for(Map.Entry<Integer, ArrayList<String>> curr : byScores){
         int score = curr.getKey();
         ArrayList<String> list = curr.getValue();
         Collections.sort(list);
         for(String s: curr.getValue()){
            System.out.println(score + ": " + s);
         }
      }
   }
   
}


/**
   SortByScore class that implements Comparator<Map.Entry<Integer, ArrayList<String>>>
   make Map.Entry<Integer, ArrayList<String>> object sorts in the decreasing order of the key of the Map entry
 */
class SortByScore implements Comparator<Map.Entry<Integer, ArrayList<String>>>{
   
   public int compare(Map.Entry<Integer, ArrayList<String>> a, Map.Entry<Integer, ArrayList<String>> b){
      return b.getKey()-a.getKey();
   }
}