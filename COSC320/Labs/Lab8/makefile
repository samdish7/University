all: main
main: main.o binarytree.o
	g++ -o main main.o binarytree.o
main.o: main.cpp
	g++ -c main.cpp
binarytree.o: binarytree.cpp
	g++ -c binarytree.cpp
clean:
	rm *o main

