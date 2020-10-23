#Task 6c
#Sam Disharoon
#!/bin/sh
#Simple program to display a pattern on the screen
var=true
echo "Please enter a number between 5 and 9"
read i
while $var;
do
	if [ $i -lt 5 ]; then
		echo "TOO LOW!"
		echo
		read i
	elif [ $i -gt 9 ] ; then
		echo "TOO HIGH!"
		echo 
		read i
	else
		echo "Value accepted"
		var=false
		echo
	fi
done
	for ((k=1;k<=i;k++))
	do
		for((j=1;j<=i-k;j++))
		do
			echo -n "  "
		done
		for((j=1;j<=k;j++))
		do
			echo -n " $k "
		done
	echo
	done
