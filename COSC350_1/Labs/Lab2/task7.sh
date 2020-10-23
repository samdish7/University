#!/bin/sh
#Task 7
#Sam Disharoon
#This program outputs the factorial of an argument that is passed

i=$1
fact=1
while [ $i -gt 0 ]; do
	fact=$((fact*i))
	let i=i-1

done
echo "Factorial of $1~> $fact"
exit 0

