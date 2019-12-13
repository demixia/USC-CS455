
// CSCI 455 PA5
// Fall 2018

// Table.cpp  Table class implementation


#include "Table.h"

#include <iostream>
#include <string>
#include <cassert>

using namespace std;


// listFuncs.h has the definition of Node and its methods.  -- when
// you complete it it will also have the function prototypes for your
// list functions.  With this #include, you can use Node type (and
// Node*, and ListType), and call those list functions from inside
// your Table methods, below.

#include "listFuncs.h"


//*************************************************************************


Table::Table() {
   hashSize = HASH_SIZE;
   
   //allocate an array of HASH_SIZE pointers to Node
   //and initialize them all to 0
   table = new ListType[hashSize](); 
}


Table::Table(unsigned int hSize) {
   hashSize = hSize;
   
   //allocate an array of hashSize pointers to Node
   //and initialize them all to 0
   table = new ListType[hashSize]();
}


int * Table::lookup(const string &key) {
   int index = hashCode(key);
   return lookupList(table[index], key);
}

bool Table::remove(const string &key) {
   int index = hashCode(key);
   //if the key is in the table, remove it 
   if(removeList(table[index], key)){
      if(table[index] == NULL){
         --bucketUsed;
      }
      --entries;
      return true;
   } 
   return false;
}

bool Table::insert(const string &key, int value) {
   int index = hashCode(key);
   if(table[index] == NULL){
      ++bucketUsed;
   }
   if(lookupList(table[index], key) == NULL){
      insertList(table[index], key, value);
      ++entries;
      return true;
   }
   return false;
}

int Table::numEntries() const {
   return entries; 
}


void Table::printAll() const {
   for(int i= 0; i < hashSize; i++){
      printList(table[i]);
   }
}


void Table::hashStats(ostream &out) const {
   out << "number of buckets: " << hashSize << endl;
   out << "number of entries: " << entries << endl;
   out << "number of non-empty buckets: " << bucketUsed << endl;   
   
   //find the longest chain
   int longest = 0;
   for(int i= 0; i < hashSize; i++){
      int length = chainLength(table[i]);
      if(length > longest){
         longest = length;
      }
   }
   out << "longest chain: " << longest << endl;
}


// add definitions for your private methods here
