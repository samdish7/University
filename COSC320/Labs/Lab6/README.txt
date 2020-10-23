README.txt
Sam Disharoon
*PSA*
The hiring problem does not work in this lab, nor does rand_quick. See 'g' below as to why rand_quick does not work


a) My approach to the problem was to take the code we already used, implement it in this lab, and modify it based on what was needed.  I tested each "working" function first, recorded the data, and moved to the next one.
b)Each time i inceased the data size by 50000 starting at 10000.  Just because you double the size of an array, this does not mean the time will be doubled.  In the case of Mergesort, and increase of ~200% data (60000->110000) did not cause the algorithm to double in time, it mearly had ~142% increase in time taken.
c)As n increases, the time SHOULD increase as well. Due to the randomness of shuffle however, this allows some arrays that are larger to potentially take less time due to the randomness factor. 
d) The worst case for each would be an array sorted in reverse order. This would cause the n^2 situation. However, since there is a 1/n! chance of that occuring due to shuffle, it will most likely never hit that situation.  The arrays that are smaller, but take a longer time than thier larger counterparts come the closest to hitting the worst case.
f) Shuffle coulde be better if it didn't shuffle the array the same each time. Even using srand, it still shuffles arrays of size n the same way as a second array of size n.
g) Rand_quick does not work. I added the swap in the correct place like what was stated in class, and it does not sort the array, just randomizes it more. 
