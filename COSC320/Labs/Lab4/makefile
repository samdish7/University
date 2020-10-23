all: main
main: main.o priorityQ.o
	g++ -o main main.o priorityQ.o
main.o: main.cpp
	g++ -c main.cpp
priorityQ.o: priorityQ.cpp
	g++ -c priorityQ.cpp
clean:
	rm *o main
