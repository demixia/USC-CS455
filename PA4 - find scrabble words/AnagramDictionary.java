
// CS 455 PA4
// Fall 2018

import java.io.FileNotFoundException;
import java.io.File;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Collections;


/**
   A dictionary of all anagram sets. 
   Note: the processing is case-sensitive; so if the dictionary has all lower
   case words, you will likely want any string you test to have all lower case
   letters too, and likewise if the dictionary words are all upper case.
 */

public class AnagramDictionary {
   
   
   /**
      representation invariant:
      dictionary is a hash map to dictionary by the letters each word contains, 
      it will save the string contains same letters in the same arraylist
      the key of the map is the hashCode of the sorted string
      the value of the map is the ArrayList of strings that have the save letters but in different orders 
    */
   private Map<Integer, ArrayList<String>> dictionary; 


   /**
      Create an anagram dictionary from the list of words given in the file
      indicated by fileName.  
      PRE: The strings in the file are unique.
      @param fileName  the name of the file to read from
      @throws FileNotFoundException  if the file is not found
    */
   public AnagramDictionary(String fileName) throws FileNotFoundException {
      
      File inFile = new File(fileName);
      dictionary = new HashMap<Integer, ArrayList<String>>();
      Scanner in = new Scanner(inFile);
      readWords(in);
   }
   

   /**
      Get all anagrams of the given string. This method is case-sensitive.
      E.g. "CARE" and "race" would not be recognized as anagrams.
      @param s string to process
      @return a list of the anagrams of s
    */
   public ArrayList<String> getAnagramsOf(String s) {
      
      //convert the string into character arrays and sort it
      //then re-convert it into a new string with letters in order 
      char[] letters = s.toCharArray();
      Arrays.sort(letters);
      String newWord = new String(letters);
      
      int code = newWord.hashCode(); //the hashCode of the newWord
      ArrayList<String> anagrams = new ArrayList<String>();
      
      if(dictionary.containsKey(code)){
         anagrams = dictionary.get(code);
      }
      return anagrams;     
   }
   
   
   /**
      Reads all the words in the dictionary file, and save it in the map according to the letters it contains.
      Resort the every words according to letters it contains into a new string, eg. "hello" will be "ehllo",
      then according to the hashCode of the new sorted string, save the original word in the map.
      @param in the scanner that scans the words in the file
      PRE: the file should contain legal words
    */
   private void readWords(Scanner in){
       
      while(in.hasNext()){
      
         String word = in.next();
         char[] letters = word.toCharArray();
         Arrays.sort(letters);
         String newWord = new String(letters);
         
         //put words which contain same letters together
         //save the score of sorted word in scoreMap
         int code = newWord.hashCode();
         ArrayList<String> words = new ArrayList<String>();  
         if(dictionary.containsKey(code)){        
            words = dictionary.get(code);
         }
         words.add(word);
         dictionary.put(code,words);
      }
   }
   

}
