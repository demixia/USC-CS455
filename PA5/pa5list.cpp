
// CS 455 PA5
// Fall 2018

// pa5list.cpp
// a program to test the linked list code necessary for a hash table chain

// You are not required to submit this program for pa5.

// We gave you this starter file for it so you don't have to figure
// out the #include stuff.  The code that's being tested will be in
// listFuncs.cpp, which uses the header file listFuncs.h

// The pa5 Makefile includes a rule that compiles these two modules
// into one executable.

#include <iostream>
#include <string>
#include <cassert>

using namespace std;

#include "listFuncs.h"
//#include "Table.h"

typedef Node * ListType;

int main() {
   
   ListType list;
   cout << "********insert:" << endl;
   insertList(list, "Mary", 100);
   insertList(list, "Jack", 50);
   insertList(list, "Joe", 60);
   insertList(list, "Mark", 30);
   insertList(list, "Anna", 40);
   insertList(list, "Jim", 70);
   printList(list);
   cout << "length: " << chainLength(list)<<endl;
   cout << "********after removing: " << endl;
   removeList(list, "Jack");
   printList(list);
   cout << "length: " << chainLength(list)<<endl;
   cout << "********search: " << endl;
   int *score = lookupList(list, "Mary");
   cout << "Mary: "<< *score << endl;
   cout << "********after removing: " << endl;
   removeList(list, "Joe");
   printList(list);
   cout << "length: " << chainLength(list)<<endl;





   return 0;
}
