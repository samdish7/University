all: main

main: main.o hanoi.o
	g++ -o main main.o hanoi.o
main.o: main.cpp
	g++ -c main.cpp
hanoi.o: hanoi.cpp hanoi.h
	g++ -c hanoi.cpp
clean:
	rm *o main
