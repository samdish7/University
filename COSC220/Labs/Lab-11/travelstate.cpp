#include "travelstate.h"

void TravelState::printOptions() {
  std::cout << "You are walking " << direction << std::endl;
  if (hasCrossroad){
    std::cout << "You see a fork in the road, headed " << newDirection << std::endl;
  }

  /* Classic way
  for(int i = 1; i<choices.size() + 1; i++){
    std::cout << i << ". " << choices[i] << std::endl;
  }
  */

  /* More modern way to iterate a std::map */
  for(auto i = choices.begin(); i != choices.end(); i++){
    /*
     * i holds an iterator, which is a pointer to a std::pair<int, string>
     * i->first is the int
     * i->second is the string
     */
    std::cout << i->first << ". " << i->second << std::endl;
  }
};

GameState* TravelState::handleInput(std::string in) {
  // cast the string to an int to be interpreted as a choice
  int choice = std::stoi(in);

  // Temp variable to hold the new game state
  GameState* rtn;

  switch (choice){
    case CONTINUE_OPTION:
      std::cout << "\nYou continue walking...\n";
      rtn = new TravelState(direction);
      break;
    case SIT_OPTION:
      rtn = nullptr;
      // TODO: construct and return a new idlestate
      // currently this will cause a segfault if the user
      // tries to sit down!
      //rtn = new IdleState(direction);
      break;
    case FORK_OPTION:
      std::cout << "\nYou take the fork...\n";
      rtn = new TravelState(newDirection);
      break;
    default:
      std::cout << "Invalid option.\n";
      rtn = this;
      break;
  };

  return rtn;
};
