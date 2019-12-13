
// CSCI 455 PA5
// Fall 2018


//*************************************************************************
// Node class definition 
// and declarations for functions on ListType

// Note: we don't need Node in Table.h
// because it's used by the Table class; not by any Table client code.

// Note2: it's good practice to not put "using" statement in header files.  Thus
// here, things from std libary appear as, for example, std::string

#ifndef LIST_FUNCS_H
#define LIST_FUNCS_H
  

struct Node {
   std::string key; //the string key in Node
   int value; //the value in the Node

   Node *next; //pointer to next Node
   
   //create a Node with next points to NULL
   Node(const std::string &theKey, int theValue);
   //create a Node with next ponits to n
   Node(const std::string &theKey, int theValue, Node *n);
};

//create a ListType pointer points to Node
typedef Node * ListType;

//*************************************************************************
//add function headers (aka, function prototypes) for your functions
//that operate on a list here (i.e., each includes a parameter of type
//ListType or ListType&).  No function definitions go in this file.

//print information of all the Nodes in the list
void printList(ListType list);

//insert new name and score infront of the list
//PRE: just insert name that doesn't in the list
void insertList(ListType & list, std::string name, int score);

//remove the node from list according to the name and return true
//return false if the node needed to delete doesn't exist in list
bool removeList(ListType & list, std::string name);

//look up the score according to the name entered
//return a pointer to the score
//return NULL if the name entered doesn't exist in list
int * lookupList(ListType & list, std::string name);

//find the length of the linked list
//return the length
int chainLength(ListType list);


// keep the following line at the end of the file
#endif
