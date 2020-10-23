all: main

main: driver.o SUList.o SUStack.o SUQueue.o
	g++ -o main driver.o SUList.o SUStack.o SUQueue.o
driver.o: driver.cpp SUList.h SUStack.h SUQueue.h
	g++ -c -std=c++11 -g driver.cpp
clean:
	rm -f main driver.o SUList.o SUQueue.o SUStack.o
rebuild:
	make
	make clean
