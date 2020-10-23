#!/bin/bash
echo "Please enter a directory"
read dir
if [ -d "$dir" ]; then
	echo "Name a file in $dir, you have 3 tries:"
	read file
	if [[ (-e "$file") && (-r "$file") ]]; then
		echo "Give me a word to look for in $file:"
		read word
		if [ grep "$word" ]; then
			echo "$word FOUND!"
			exit 0
		else
			echo "$word NOT FOUND!"
			exit 4
		fi
	elif [[ (-e "$file") && ( ! -r "$file") ]]; then
		echo "File is not readable"
		exit 3
	else 
		echo "File does not exist"
		exit 2
		
	
	fi
else
	echo "Directory does not exixt"
	exit 1
fi

	
