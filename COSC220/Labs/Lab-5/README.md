# Lab 5 COSC 220; Sam Disharoon

*README written in 2023* :grinning:

## Overview

This demonstrates the *Stack* data structure with a *Linked List* back-end. A little difficult to determine the structure of the code because there is zero formatting, but it appears to have the same dynamic as the previous labs in which there is no user input. Formatting of the output looks nice, but it is hard to tell what it is trying to portray off of the intial inspection.

Submitted report was:

`Everything works with an exception of the Copy CTOR and the Assignment operator. It does not print in reverse because you never told us to push back what we pop out. If we did re push them, the list would print out in reverse order. `

I apparently had a quarrel with Dr. Anderson about the *reverse order* part of the assignment. :rofl:

### Bugs/Improvements

- The *push* function works as expected.
- The *pop* function is misleading because each time it pops, it outputs the entire employee. Maybe this was the intent. but I would change it to say `Popped <employee>!`. Minor UI change nevertheless.
- Need to add the = operator and copy CTOR implementations.

## Feedback

Very good program. Does not crash, handles the files per usual, stack works as implied and does not leak memory. Just a few minor UI changes and to implement the copy CTOR/assignment operator.

***Overall Rating: 8/10***
