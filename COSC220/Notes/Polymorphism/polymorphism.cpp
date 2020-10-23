#include<iostream>
#include<stdio.h>

class A{
  // allow access to subclasses
  protected:
    int num;

  public:
    // (by default) is statically bound
    // to objects of this class
    // - with "virtual" the compiler
    // will wait until runtime, and search
    // any inherited objects for an
    // override
    // any override will automatically
    // be virtual as well!
    virtual void setNum(int i){
      num = i;
    }

    // =0 means this function is "pure
    // virtual" -- it will *NOT* be
    // implemented in this class, but
    // must be overridden by derived
    // classes
    virtual void doStuff() = 0;
    // this thing makes A an "Abstract
    // class" and abstract classes
    // cannot be instantiated!

    int getNum(){
      return num;
    }
};

// can only hold even numbers
class B : public A {
  public:
    void doStuff() override{
      std::cout << "Hello from B!\n";
    }

    virtual void doMoreStuff(){
      printf("Blahhhhhh\n");
    }

    // override the setNum to do something
    // a bit more specialized
    // the "override" keyword tells the
    // compiler to check the parent
    // classe(s) to make sure it is a valid
    // override
    void setNum(int i) override{
      num = 2*i;
    }
};

class C : public A {
  public:
    void doStuff() override{
      std::cout << "Hello from C!\n";
    }

    int getNum(){
      printf("here!\n");
      return num;
    }
};

// can only hold even numbers
class D : public B {
  public:
    void doStuff() override{
      std::cout << "Hello from D!\n";
    }
    // override the setNum to do something
    // a bit more specialized
    void setNum(int i){
      num = 3*i;
    }
};

int main(){
  A* p;

  B* b = new B();
  p = b;
  b->setNum(20);
  std::cout << "b->num is " << b->getNum() << std::endl;
  b->doStuff();
  std::cout << "p->num is " << p->getNum() << std::endl;
  p->doStuff();

  C* c = new C();
  p = c;
  c->setNum(20);
  std::cout << "c->num is " << c->getNum() << std::endl;
  std::cout << "p->num is " << p->getNum() << std::endl;
  p->doStuff();

  D* d = new D();
  p = d;
  d->setNum(20);
  std::cout << "d->num is " << d->getNum() << std::endl;
  std::cout << "p->num is " << p->getNum() << std::endl;
  p->doStuff();



  return 0;

}











