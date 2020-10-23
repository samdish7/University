all: main
main: main.o qmsort.o
	g++ -g -o main main.o qmsort.o
main.o: main.cpp
	g++ -g -c main.cpp
qmsort.o: qmsort.cpp
	g++ -g -c qmsort.cpp
clean:
	rm *o main
