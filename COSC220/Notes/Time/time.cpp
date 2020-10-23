#include<iostream>

// represent a time in hours and minutes
class Time{
  private:
    // 0 <= hours < 24
    // and
    // 0 <= minutes < 60
    int hours, minutes;
    
  public:
    Time(){};

    // using an initializer list
    Time(int h, int m) : hours(h), minutes(m) {
      // we may need to check things
      // e.g. the constraints above...
    }

    // class member function: t1.operator<(t2)
    // t1 < t2 if t1 is earlier in the day
    bool operator<(Time t1) {
      return
        (hours*60 + minutes) < 
        (t1.hours*60 + t1.minutes);
    }

    // TODO: overload + and -
    // the left operand will be "this"
    // because we're defining inside a class
    // const will prevent us from chancing
    // "this" inadvertently
    // returns noting, takes a Time by value
    // on the right
    // consts make sure operands have no
    // side-effects
    Time operator+(const Time& t) const {
      Time rtn;
      std::cout << "in operator+ v1...\n";
      int m1 = minutes + 60*hours;
      int m2 = t.minutes + 60*t.hours;
      int m = m1 + m2;
      int h = m / 60;
      m %= 60; // m = m % 60
      h %= 24;
      rtn.hours = h;
      rtn.minutes = m;
      return rtn;
    }

    // motivated by the desire to type
    // t1 = t2 + 10;
    // t1 = t2.operator+(10)
    // interpret the int as minutes
    // doesn't account for negative??
    Time operator+(const int i) const {
      // dereference and copy "this" data
      Time rtn = *this;
      rtn.minutes += (i % 60);
      rtn.hours += (i / 60);
      rtn.hours += rtn.minutes / 60;
      rtn.minutes %= 60;
      return rtn;
    }

    Time operator+=(int i){
      *this = *this + i;
      return *this;
    }

    // overloaded type conversion!
    // interpret as minutes
    operator int(){
      return 60*hours + minutes;
    }

    // to allow the operator<< to access private
    // members, we make it a "friend function"
    // i.e. has full internal access
    
    friend void func(Time);

    // any function matching this prototype
    // later in the program will have full
    // access to the private section
    friend std::ostream& 
      operator<<(std::ostream&, const Time&);
};

// prototype for operator<<
// call with "cout << obj"
std::ostream&
  operator<<(std::ostream& o, const Time& t){
    o << t.hours << ":";
    if( t.minutes < 10)
      o << "0";
    o << t.minutes;
    // << std::endl; // don't assume the user
    // wants a newline!
    return o; // return the stream ref
}

int main(){
  Time t1 = {2, 45};
  Time t2 = {3, 20};

  // want operator<< for Time
  //std::cout << t1;
  //operator<<(std::cout, t1);
  std::cout << "Time is: " << t1;
  /* above is shorthand for this:
  operator<<(
      operator<<(std::cout, "Time is: "), t1);
  */

  std::cout << "Earlier time is:\n";
  if( t1 < t2 ){
    std::cout << t1 << std::endl;
  } else {
    std::cout << t2 << std::endl;
  }

  // calls t1.operator+(t2);
  // and the default copy ctor
  Time t3 = t1 + t2;
  std::cout <<
    t1 << " + " << t2 << " = " << t3
    << std::endl;

  Time t4 = t1;
  int n = 235;
  std::cout << 
    t4 << " + " << n << " = " << (t4 + n)
    << std::endl;

  std::cout << "t4 = " << t4 << std::endl;
  t4 += 61;
  std::cout << "t4 += 61 // " << t4 << std::endl;

  std::cout << "int(t4) = " << int(t4) << std::endl;


  return 0;
}
























