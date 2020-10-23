all: main

main: main.o lab8.o
	g++ -o main main.o lab8.o
main.o: main.cpp
	g++ -c main.cpp
lab8.o: lab8.cpp lab8.h
	g++ -c lab8.cpp
clean: 
	rm *o main
