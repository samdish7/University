#!/bin/sh
#Task 8
#Sam Disharoon
#This program takes a number from command line, adds the digits, and prints out the sum

if [[ $# -gt 1 ]]; then
	echo "You can only have 1 aargument!"
	exit 0
elif [[ $# -eq 0 ]]; then
	echo "You need to pass an argument!"
	exit 0
else
dig=0
sum=0
num=$1
while [ $num -gt 0 ]; do
	dig=$(( $num % 10 ))
	num=$(( num / 10 ))
	sum=$(( sum + dig ))

done
echo "The sum of $1's digits is ~> $sum"
fi
exit 0
