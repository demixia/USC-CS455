
// CS 455 PA4
// Fall 2018

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

/**
   A Rack of Scrabble tiles
 */

public class Rack {
   
   /**
      representation invariant:
      letters is a hash map to save the legal letters and their frequencies, '
      the key of the map is characters of a string, and the values of the map is the frequency of the character.
      the key set of the map letters contains only sorted alphabet letters
    */
   private Map<Character, Integer> letters;

   
   /**
      Create a legal rack according to the user input, and only save alpahbet letters into the map 
      @param input the string entered by user 
    */
   public Rack(String input){
   
      letters = new HashMap<Character, Integer>();
    
      //save the alphabet letters and frequency in the hash map
      for(int i = 0; i < input.length(); i++){
         
         char cur = input.charAt(i); //the current character of the string
         
         if(Character.isLetter(cur)){
            if(letters.containsKey(cur)){
               letters.put(cur,letters.get(cur)+1);
            }
            else{
               letters.put(cur,1);
            }
         }
      }
   }

   
   /**
      Save the unique key set of the map into a string, and the corresponding values into an array
      to find all the subsets of the legal input string
      @return the ArrayList of all subset strings of the given 
    */
   public ArrayList<String> findSubsets(){
   
      String unique = ""; 
      int[] mult = new int[letters.size()]; 
      
      int loc = 0;
      for(Map.Entry<Character, Integer> entry: letters.entrySet()){
         unique += entry.getKey();
         mult[loc] = entry.getValue();
         loc++;
      }
      return allSubsets(unique,mult,0);
   }
   
   /**
      Finds all subsets of the multiset starting at position k in unique and mult.
      unique and mult describe a multiset such that mult[i] is the multiplicity of the char
           unique.charAt(i).
      PRE: mult.length must be at least as big as unique.length()
           0 <= k <= unique.length()
      @param unique a string of unique letters
      @param mult the multiplicity of each letter from unique.  
      @param k the smallest index of unique and mult to consider.
      @return all subsets of the indicated multiset
      @author Claire Bono
    */
   private static ArrayList<String> allSubsets(String unique, int[] mult, int k) {
      ArrayList<String> allCombos = new ArrayList<>();
      
      if (k == unique.length()) {  // multiset is empty
         allCombos.add("");
         return allCombos;
      }
      
      // get all subsets of the multiset without the first unique char
      ArrayList<String> restCombos = allSubsets(unique, mult, k+1);
      
      // prepend all possible numbers of the first char (i.e., the one at position k) 
      // to the front of each string in restCombos.  Suppose that char is 'a'...
      
      String firstPart = "";          // in outer loop firstPart takes on the values: "", "a", "aa", ...
      for (int n = 0; n <= mult[k]; n++) {   
         for (int i = 0; i < restCombos.size(); i++) {  // for each of the subsets 
                                                        // we found in the recursive call
            // create and add a new string with n 'a's in front of that subset
            allCombos.add(firstPart + restCombos.get(i));  
         }
         firstPart += unique.charAt(k);  // append another instance of 'a' to the first part
      }
      
      return allCombos;
   }

   
}
