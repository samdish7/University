# Lab 3, Iris data set; Sam Disharoon

## Details:

1. You may submit this lab in groups of one or two.
2. Download the “Iris” dataset from the UCI Machine Learning data repository: [Iris Link](https://archive.ics.uci.edu/ml/datasets/Iris). This dataset is record of some flowers, along with their sepal and petallength and width measurements. Each flower is “labeled” as to which type of Iris family it belongs to (Setosa, Versicolour, or Virginica).
3. In each of the following plots/visualization, be sure to:
    - (a) Include titles for each plot.
    - (b) Include legends to differentiate which plot elements correspond to which data.
    - (c) Start your y and x index at logically consistent values (usually 0 for physical measurement-type data).
4. Among the sepal/petal length/width measurements, we can define six different pairwise comparisons (sepal length vs petal width, sepal length vs sepal width, etc.); show these two parameters together in (six) different scatter plots, where each class is shown by a different color and shape marker.
    - (a) What is the correlation coefficient for each pair of measurements when class is disregarded?
    - (b) What is the correlation coefficient for each pair of measurements when taking into account only measurements within the same class. I.e. what is the correlation between sepal length and width overall, and what is the correlation coefficient between sepal length and width among the Setosa class, Versicolour class, and Virginica class?
5. For each of the four numerical categories, compute the mean with a 95% confidence interval and show them in a bar chart.
6. Next, compute the mean of sepal and petal measurements with 95% confidence intervals, but this time separated by each of the three classes (so you will have a total of 12 bars and intervals).
7. From the two mean estimates above, draw at least one relationship conclusion (e.g. the mean of X is larger than the mean of Y ) and find the p-value that shows the strength of that conclusion. Does this mean you can reject your hypothesis or not?
    - (a) Looking at the data (in the form of a histogram), how close is it to a Normal distribution?
    - (b) If it is not close, then what does this say about your p-value?
    
## Answers

### 4; 
A) The 6 relationships are as follows:

    1. Sepal_length vs Sepal_width
    2. Sepal_length vs Petal_length
    3. Sepal_length vs Petal_width
    4. Sepal_width vs Petal_length
    5. Sepal_width vs Petal_width
    6. Petal_length vs Petal_width
    
B) *In lab 3 file*

### 5;

*In lab 3 file*

### 6;

*In lab 3 file*

### 7;

A) *More detailed in lab 3 file*, but the distributions appear to be normal.

B) *In lab 3 file*

## Issues

Not sure if I did the confidence intervals correctly for numbers 5 and 6, but it didn't crash the program and it gave me the correct answers, so I'm gonna assume that I was right in what I did.
