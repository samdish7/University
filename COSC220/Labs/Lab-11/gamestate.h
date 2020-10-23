#ifndef GAMESTATE_H
#define GAMESTATE_H

#include<map>
#include <iostream>

class GameState {
  protected:
    // Each subclass will be responsible for setting up a list
    // of things the user can do, along with the text prompt
    std::map<int, std::string> choices;

  public:
    // TODO: each state should have a chance of
    // the user getting attacked
    double combatProbability;

    // prints all options available to the user in this state
    virtual void printOptions() = 0;

    // will take the input and handle it appropriately
    virtual GameState* handleInput(std::string) = 0;
};

#endif
