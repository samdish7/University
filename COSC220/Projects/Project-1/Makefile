all: student

student: main.o student.o studentdb.o course.o
	g++ -o student main.o student.o studentdb.o course.o
main.o: main.cpp
	g++ -c main.cpp
student.o: student.cpp student.h
	g++ -c student.cpp 
studentdb.o: studentdb.cpp studentdb.h
	g++ -c studentdb.cpp
course.o: course.cpp course.o
	g++ -c course.cpp
clean:
	rm *o student

