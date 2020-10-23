README.txt ~> Sam Disharoon
PSA~> So, funny thing, when I create the tree, if I insert a node that is larger than the root, and then directly after, try and insert a node that is in between the root and the first inserted node, it Segfaults as shown in exhibit A. however, if the second node i insert is larger than the first node, then i can insert what ever I want after that and it works fine for a while, but then goes into an infinite loop as shown in exhibit B.  Please note, the root of both trees will start at 1 for this case

EXHIBIT A~>
====================================================
Insertion finished at Wed Nov 13 23:58:12 2019
elapsed time: 7.772e-06s
1 5 50 54 500 745 
5 found!
65 Not found!
500 found!
5 found!
1 50 54 500 745 
5 Not found!
Enter a number (-1 to quit,-2 to print in order)
~> 78
Enter a number (-1 to quit,-2 to print in order)
~> 54
Segmentation fault (core dumped)
===================================================

EXHIBIT B~>
===================================================
Insertion finished at Wed Nov 13 23:59:25 2019
elapsed time: 7.637e-06s
1 5 50 54 500 745 
5 found!
65 Not found!
500 found!
5 found!
1 50 54 500 745 
5 Not found!
Enter a number (-1 to quit,-2 to print in order)
~> 54
Enter a number (-1 to quit,-2 to print in order)
~> 55
Enter a number (-1 to quit,-2 to print in order)
~> 2
Enter a number (-1 to quit,-2 to print in order)
~> 4
Enter a number (-1 to quit,-2 to print in order)
~> 6
Enter a number (-1 to quit,-2 to print in order)
~> 2
Enter a number (-1 to quit,-2 to print in order)
~> 1
Enter a number (-1 to quit,-2 to print in order)
~> 4
Enter a number (-1 to quit,-2 to print in order)
~> 66
Enter a number (-1 to quit,-2 to print in order)
~> 4
Enter a number (-1 to quit,-2 to print in order)
~> 3
Enter a number (-1 to quit,-2 to print in order)
~> 5
6	<~This is were the program stops
534
3
53
45
g
dg
===================================================
a) My approach to the problem was to take the code from our binary search tree, modify it, and implement the new functions that were introduced with the Red-black tree puesdo code
b) 			Best		Worst
	Insert	 	O(logn)		O(logn)
	Search		O(logn)		O(logn)
	Delete		O(logn)		O(logn)
	Fixups		O(1)		O(logn)
c) couldnt test properly because of segfaults
d) it could be improved to be able to function properly which i will bring up to you during lab/office hours
