all: main
main: main.o shuffle.o
	g++ -o main main.o shuffle.o
main.o: main.cpp
	g++ -c main.cpp
shuffle.o: shuffle.cpp
	g++ -c shuffle.cpp
clean: 
	rm* -o main
