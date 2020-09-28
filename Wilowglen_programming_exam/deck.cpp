#include <string>
#include <iostream>
#include <algorithm>
#include <cstdlib>
#include <vector>
#include <random>
#include "deck.h"

Deck::Deck()
{
  for(int i=0;i<4;i++){
    for(int j=1;j<=13;j++){
      Card card;
      card.set_value(j,i);
      deck.push_back(card);
    } 
  }
}
  
Deck::~Deck()
{
}
  
void Deck::deal_hand(int sets, int cards)
{
  int total = sets*cards;
  if(total>52){
    cout << "Not Enough Cards"<< endl;
  }
  else{
    for(int i=0;i<total;i++){
      // New line for new set.
      if(i ==0){
        cout << "Set " << 1 << ":";
      }
      if(i%cards == 0 && i != 0){
        cout << endl;
        int setNum = (i/cards)+1;
        cout << "Set " << setNum << ":";
      }
      deck[i].print();
    }
  }
}
  
void Deck::print_deck()
{
  int counter = 0;
  for(int i=0;i<52;i++){
    if(counter%13 == 0){
      cout << endl;
    }
    deck[i].print();
    counter++;
  }
}

void Deck::shuffle(int seed)
{
    ::shuffle(deck.begin(), deck.end(), default_random_engine(seed));
}

void Deck::sort()
{ 
  bubbleSort(deck, 52);
  bubbleSort_suit(deck, 52);
}

void Deck::swap(Card *xp, Card *yp)  
{  
    Card temp = *xp;  
    *xp = *yp;  
    *yp = temp;  
}  

void Deck::bubbleSort(vector<Card> &vec, int n) 
{  
    int i, j;  
    for (i=0;i<n-1;i++){      
      for (j=0;j<n-i-1;j++){  
        if (vec[j]>vec[j+1]){  
          swap(&vec[j], &vec[j+1]);
        }  
      }
    }
} 
  
void Deck::bubbleSort_suit(vector<Card> &vec, int n)  
{  
    int i, j;  
    for (i=0;i<n-1;i++){      
      for (j=0;j<n-i-1;j++){  
        if((vec[j].get_suit()) > (vec[j+1].get_suit())){
          swap(&vec[j], &vec[j+1]);  
        }
      }
    }
} 