all: main

main: main.o payroll.o payrolllist.o
	g++ -o main main.o payroll.o payrolllist.o
main.o: main.cpp
	g++ -c -g main.cpp
payroll.o: payroll.cpp payroll.h
	g++ -c -g payroll.cpp
payrolllist.o: payrolllist.cpp payrolllist.h
	g++ -c -g payrolllist.cpp

clean:
	rm *o main
