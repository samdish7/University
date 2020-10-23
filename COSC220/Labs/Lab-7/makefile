all: main

main: main.o payroll.o payrolllist.o
	g++ -o main main.o payroll.o payrolllist.o
main.o: main.cpp
	g++ -c main.cpp
payroll.o: payroll.cpp payroll.h
	g++ -c payroll.cpp
payrolllist.o: payrolllist.cpp payrolllist.h
	g++ -c payrolllist.cpp
clean:
	rm *o main
