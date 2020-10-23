#Task 6b
#Sam Disharoon
#!/bin/sh
#Simple program to output a pattern on screen
for ((i=1;i<=5;i++))
do
	for((j=1;j<=i;j++))
	do
		echo -n "*"
	done
	echo
done
for ((k=1;k<=4;k++))
do
	for((l=4;l>=k;l--))
	do
		echo -n "*"
	done
	echo
done
