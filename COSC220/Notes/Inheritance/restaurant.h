#ifndef RESTAURANT_H
#define RESTAURANT_H

#include<iostream>
#include<stdio.h>

#include "arrays.h"

class Restaurant {
  public:
    // not bound to any specific instance
    // of Restaurant!
    // instead is bound to the class itself
    // reference with Restaurant::nextId
    static int nextId;

  private:
    int tax_id;

  private:
    std::string name;
  protected:
    bool vegan;
    Array<std::string> menu;
    int employees;
    // array of days, each with 2 hours
    // start and end
    Array<Array<int>> hours;
  public:
    Restaurant(){
      //printf("Constructing a restaurant!\n");
      
      // puts nextid on the right
      // then increments it
      tax_id = nextId++;

      menu.push_back("Water");
    }

    Restaurant(std::string s) : name(s){
      tax_id = nextId++;
    }



    ~Restaurant(){
      //printf("Destructing restaurant %s!\n", name.c_str());
    }

    void setName(std::string s){
      name = s;
    }

    std::string getName(){
      return name;
    }

    void printMenu(){
      std::cout << "====== " << name << " Menu ======\n";
      for(int i=0; i<menu.length(); i++){
        std::cout << menu[i] << std::endl;
      }
      std::cout << "===================\n";
    }
};

// initialize the static member
int Restaurant::nextId = 1;

// the cafe class will inherit from restaurant
// "Restaurant" is the "Base Class"
// "Cafe" is the "Derived Class"
class Cafe : public Restaurant {
  public:
    Cafe(){
      //printf("In Cafe ctor!\n");
      menu.push_back("Latte");
    }

    // choose which parent ctor to use!
    Cafe(std::string s) : Restaurant(s) {
      
    }
    ~Cafe(){
      //printf("Destructing cafe %s!\n", name.c_str());
    }
    std::string getName(){
      std::cout << "LKSJDFLKD\n";
    }
    void welcome(){
      std::cout << "Welcome to Cafe " << getName() << std::endl;
    }
};



#endif
