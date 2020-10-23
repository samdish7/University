#include "restaurant.h"

int main(){
  printf("Next tax id: %d\n", Restaurant::nextId);

  Restaurant r;
  r.setName("COSC 220 Diner");
  r.printMenu();

  printf("r.nextId: %d\n", r.nextId);


  Cafe c("Blahblah");
  printf("c.nextId: %d\n", c.nextId);
  printf("r.nextId: %d\n", r.nextId);

  //c.setName("Nervosa");
  c.welcome();
  c.printMenu();
  std::cout << c.getName();
  return 0;
}
