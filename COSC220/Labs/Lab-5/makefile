all: main

main: main.o payroll.o payrollstack.o
	g++ -o main main.o payroll.o payrollstack.o
main.o: main.cpp
	g++ -c main.cpp
payroll.o: payroll.cpp payroll.h
	g++ -c payroll.cpp
payrollstack.o: payrollstack.cpp payrollstack.h
	g++ -c payrollstack.cpp
clean:
	rm *o main
