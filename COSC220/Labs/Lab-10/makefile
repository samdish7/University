all: main
main: main.o payroll.o 
	g++ -o main main.o payroll.o
main.o: main.cpp
	g++ -c -g main.cpp
payroll.o: payroll.cpp
	g++ -c -g payroll.cpp

clean:
	rm *o main
