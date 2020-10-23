#include "inventory.h"

int main(){
  Inventory<std::string> inv1(20, 4.5, "Alice");
  Inventory<std::string> inv2(20, 4.5, "Bob");
  Inventory<int> inv3(10, 5.5, 56);
  inv1.print();
  std::cout << "<><><><>\n";
  inv2.print();
  std::cout << "<><><><>\n";
  inv3.print();
  return 0;
}
