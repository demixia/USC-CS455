
// CSCI 455 PA5
// Fall 2018


#include <iostream>
#include <string>

#include <cassert>

#include "listFuncs.h"

using namespace std;

Node::Node(const string &theKey, int theValue) {
   key = theKey;
   value = theValue;
   next = NULL;
}

Node::Node(const string &theKey, int theValue, Node *n) {
   key = theKey;
   value = theValue;
   next = n;
}


//*************************************************************************
// put the function definitions for your list functions below

void printList(ListType list){
   if(list == NULL){
      return;
   }
   Node *p = list;
   while(p->next != NULL){
      cout << p->key << "  " << p->value << endl;
      p = p->next;
   }
   cout << p->key << "  " << p->value << endl;
}


void insertList(Node * & list, string name, int score){
   //if enter the first node, make its next points to NULL
   if(list == NULL){
      list = new Node(name, score);
   }
   else{
      list = new Node(name, score, list);
   }
   
}

bool removeList(ListType & list, string target){
   if(list != NULL){
      Node *p = list;
      //if the target is in front, make list points to its next
      //and delete current node
      if(p->key == target){
         list = list->next;
         delete p;
         return true;
      }
      Node * pre = p; //a pointer points to current node
      p = p->next; //points to next
      while(p->next != NULL){
         if(p->key == target){
            pre->next = p->next;
            p->next = NULL;
            delete p;
            return true;
         }
         pre = pre->next;
         p = p->next;
         
      }
   }
   return false;
}

int * lookupList(ListType & list, string target){
   if(list != NULL){
      Node *p = list;
      while(p->next != NULL){
         if(p->key == target){
            return &(p->value);
         }
         p = p->next;
      }
      if(p->key == target){
         return &(p->value);
      }
   }
   return NULL;
}

int chainLength(ListType list) {
   int length = 0;
   if(list != NULL){
      Node *p = list;
      while(p->next != NULL){
         ++length;
         p = p->next;
      }
      ++length;
   }
   return length;
}
