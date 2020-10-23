a) QUICKSORT
	Best Case -> nlog(n)
	Worst Case -> n^2
   MERGESORT
	Best Case -> nlog(n)
	Worst Case -> nlog(n)
b) The absolute timing depends on the size of the array and how it is arranged in the beginning. (i.e. if the array is randomly generated, it will take less time than an array in descending order). You can use this to rectify with theoretical time complexity if you knew how fast the processors in the CPU were, you could estimate how long it would take to sort a specific array.
c) I couldn't get mergesort to work. It would segfault, I ran valgrind on it and it told me that it kept trying to stack higher than it should have been. 
d) Quicksort takes the same amount of time with best and worst case. Not sure why
e) They are much more effective than bubble sort
f) Getting mergesort to work would be a start.
