#ifndef INVENTORY_H
#define INVENTORY_H

#include<iostream>

template <class T>
class Inventory {
  private:
    size_t inStock; // size_t is usually unsigned long
    double price;
    T item;

  public:
    // Fully qualified name is:
    // Inventory<T>::Inventory()
    Inventory(){}

    Inventory(size_t, double, T);
    void print();
};

template <class T>
Inventory<T>::Inventory(size_t n, double p, T t){
  inStock = n;
  price = p;
  item = t;
}

template <class T>
void Inventory<T>::print(){
  // relies on item being cout-able
  std::cout << "Item name: " << item << std::endl;
  std::cout << "In stock: " << inStock << std::endl;
  std::cout << "Price: " << price << std::endl;
}

#endif












