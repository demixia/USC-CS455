
// CSCI 455 PA5
// Fall 2018

/*
 * grades.cpp
 * A program to test the Table class.
 * How to run it:
 *      grades [hashSize]
 * 
 * the optional argument hashSize is the size of hash table to use.
 * if it's not given, the program uses default size (Table::HASH_SIZE)
 *
 */

#include "Table.h"

// cstdlib needed for call to atoi
#include <cstdlib>
#include <iostream>
#include <string>

using namespace std;
string name; //the name entered by user
int score; //the score entered

//summary of all commands
void printCmdSummary(){
   cout << "\n********************Valid Commands********************" << endl;
   cout << "insert name score , change name newscore , lookup name" << endl;
   cout << "remove name       , print                , size" << endl; 
   cout << "stats             , help                 , quit" << endl;
}

//excute different methods according to command entered by user
void command(Table * grades);

//insert name and grades to the grades table
//if the name already exist, show the message about it and don't do the insert.
void insertGrades(Table * grades);

//if change is true, it's a change score method
//it will change the score according to the user entered name and score
//if change is false, it's a lookup method
//it will show the score associated with the name
//both will show the message if the name doesn't exist
void changeOrLookup(Table * grades, bool change);

//remove the name and score from the grades table
//show message if the name entered doesn't exist
void removeGrades(Table * grades);

int main(int argc, char * argv[]) {

   // gets the hash table size from the command line

   int hashSize = Table::HASH_SIZE;

   Table * grades;  // Table is dynamically allocated below, so we can call
   // different constructors depending on input from the user.

   if (argc > 1) {
      hashSize = atoi(argv[1]);  // atoi converts c-string to int

      if (hashSize < 1) {
         cout << "Command line argument (hashSize) must be a positive number" 
              << endl;
         return 1;
      }

      grades = new Table(hashSize);

   }
   else {   // no command line args given -- use default table size
      grades = new Table();
   }

   //show the statistic of the grades table
   grades->hashStats(cout);

   // Reminder: use -> when calling Table methods, since grades is type Table*
   
   //show the summary of commands
   printCmdSummary();
   
   //excute according to command entered by user
   command(grades);

   return 0;
}

void command(Table * grades){
   string c; //command entered by user
   bool keepgoing = true; 
   do {
      cout << "cmd>";
      cin >> c;
      if(c == "insert"){
         insertGrades(grades);   
      }
      else if(c == "change"){
         changeOrLookup(grades, true);
      }
      else if(c == "lookup"){
         changeOrLookup(grades, false);
      }
      else if(c == "remove"){
         removeGrades(grades);
      }
      else if(c == "print"){
         grades->printAll();
      }
      else if(c == "size"){
         cout << grades->numEntries() << endl;  
      }
      else if(c == "stats"){
         grades->hashStats(cout);
      }
      else if(c == "help"){
         printCmdSummary();  
      }
      else if(c == "quit"){
         delete grades;
         keepgoing = false;
      }
      else{
         cout << "Error: invalid command" << endl;
         printCmdSummary();  
      }     
   } while (keepgoing);
}

void insertGrades(Table * grades){
   cin >> name;
   cin >> score;
   if(grades->insert(name, score) == false){
      cout << "Student already exist, please do the change command for changing the score" << endl;
   }
}

void changeOrLookup(Table * grades, bool change){
   cin >> name;  
   int * val = grades->lookup(name);
   if (val == NULL) {
      cout << "Student doesn't exist!" << endl;
   }
   else {
      if(change){
         cin >> score;
         *val = score; //change the score into the new one
      }
      else{
         cout << *val << endl;
      }
      
   }
}

void removeGrades(Table * grades){
   cin >> name;
   if(grades->remove(name) == false){
      cout << "Student doesn't exist!" << endl;
   }
}
