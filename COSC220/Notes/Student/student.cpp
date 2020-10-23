#include<iostream> // cout, cin, endl
#include<stdio.h> // printf

class Student{
  public:
    // need this to compile, because a
    // single override "deletes" all of
    // the implicit ones
    Student(){ }

    //copy constructor
    // no return type, param is Student&
    // intended behavior: do a "deep" copy
    //
    // This is called when:
    // 1. Assigning an existing to a new
    //    declaration of the same type
    //    e.g. Student s2 = s1;
    //    e.g. Student s2(s1);
    // 2. Passing by value/copy
    // 3. Return by value/copy
    //
    // Note: almost always a good idea to
    // make the parameter "const"
    // meaning: read-only
    Student(const Student& other){
      std::cout << "IN COPY CTOR!\n";
      name = other.name;
      age = other.age;
      numScores = other.numScores;

      // bad! does member-wise copy
      //scores = other.scores;
      
      // good: make a clone
      scores = new int[numScores];
      for(int i=0; i<numScores; i++){
        scores[i] = other.scores[i];
      }
    }

    // TODO: destructor

    std::string name;
    int         age;

    // record of exam scores
    int         numScores;
    int*        scores;
};

// argument "s" is by value!!
void printStudent(Student s){
  //std::cout << &s << std::endl;
  std::cout << s.name << std::endl;
  std::cout << s.age << std::endl;
  std::cout << s.scores << std::endl;
  std::cout << "Scores:";
  for(int i=0; i<s.numScores; i++){
    std::cout << s.scores[i] << " ";
  }
  std::cout << "\n=====\n";
}

Student makeStudent(){
  Student s1;
  s1.name = "Clare";
  s1.age = 22;
  s1.numScores = 0;

  return s1;
}

int main(){
  Student s1;
  //Student s2; // calls default ctor

  s1.name = "Bob";
  s1.age = 22;
  s1.numScores = 3;
  s1.scores = new int[s1.numScores];
  s1.scores[0] = 99;
  s1.scores[1] = 89;
  s1.scores[2] = 90;

  printStudent(s1);

  std::cout << "********\n";
  // equiv to Student s2(s1);
  // TODO: wtf?
  //Student s2;
  //s2 = s1; // DOES NOT CALL COPY CTOR
             // calls assignment
             // operator= 
  Student s2 = s1; // now s2 has shared access
           // to the internals of s1!
  std::cout << "********\n";

  s2.scores[1] = 100;
  // *(s2.scores + 1)
  s2.name = "Alice";
  printStudent(s1);
  printStudent(s2);

  Student s3;
  s3 = makeStudent();
  std::cout << "s3 data:\n";
  printStudent(s3);

  return 0;
}
