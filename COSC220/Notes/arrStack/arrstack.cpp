#include<iostream>
#include<stdio.h>

class Stack{
  private:
    int* arr;    // array of data
    int  length; // size of arr
    int  top;    // index of the top element

  public:
    Stack(){
      length = 10;
      arr = new int[length];
      top = -1; // -1 means the stack is empty
    }

    // TODO: copy ctor
    Stack(const Stack& other){
      this->length = other.length;
      this->arr = new int[length];
      this->top = other.top;
      
      for(int i=0; i<=top; i++){
        this->arr[i] = other.arr[i];
      }
    }

    // TODO: operator=
    

    ~Stack(){
      delete [] arr;
    }

    void push(int n){
      // we have a new int, n
      // we have the top index: top
      if( top < length - 1 ){
        top++;
        arr[top] = n;
      } else {
        std::cout << "Stack overflow!\n";
        // optionally, we can resize the array
      }
    }

    int pop(){
      if( top > -1 ){
        int tmp = arr[top];
        top--; // not actually removing arr[top]
        return tmp;
      } else {
        // could cout an error message
        std::cout << "Stack underflow!\n";
        return -1; // some garbage value
      }
      // later: throw an exception!
    }

    void print(){
      for(int i=0; i<length; i++){
        std::cout << arr[i];
        if( top == i ){
          std::cout << " <- top\n";
        } else {
          std::cout << std::endl;
        }
      }
    }
};

int main(){
  Stack myStack;
  myStack.push(10);
  myStack.push(1);
  myStack.push(5);
  myStack.print();
  std::cout << "============\n";
  std::cout << myStack.pop() << std::endl;
  std::cout << myStack.pop() << std::endl;
  std::cout << "============\n";
  myStack.print();
  std::cout << "============\n";
  myStack.push(15);
  myStack.print();
  std::cout << myStack.pop() << std::endl;
  return 0;
}















