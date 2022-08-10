# Lab 4, Adults Data Set pt. 2 

## Details
1. Download the “Adult” data set from the UCI Machine Learning data repository: [Adults Link](https://archive.ics.uci.edu/ml/datasets/Adult). This dataset is record of adults, along with various occupational and lifestyle attributes. Each adult is “labeled” as to whether or not they make more or less than $50k per year. Using this as a driving label, one would typically want to design a process to determine what combinations of factors enable a person to make more than $50k per year.
    (a) Read the data into a pandas DataFrame object.
    (b) Use the data and the numpy library to compute the following:
    
        i. What are the 25th, 50th, and 75th pecentiles of the “education-num” field?
        ii. What is the probability that an adult makes more than 50k given that their education-num is within the ranges defined by the above quantiles (from 0 to the 25th percentile, from the 25th to the 50th etc)?
        iii. Plot the change in probability that a person makes more and less than 50k given their years of education.
        iv. What is the covariance between the number of hours worked per week and education-num?
        v. Use the pandas.DataFrame.boxplot functionality to create a box-and-whisker plot which illustrates the spread of hours worked among adults who make both more and less than $\$50k.
        vi. Use the pandas.DataFrame.boxplot functionality to create a box-and-whisker plot which illustrates the spread of hours worked among adults from each native country and who make more and less than $\$50k.
        vii. Create a table where entry (x, y) contains the conditional probability P (A random adult has level of education x|their education-num is y).
        viii. Create a table where entry (x, y) contains the conditional probability of having marital status x given that they have occupation y.
        ix. What is the conditional probability of making more or less than $\$50k given that a person works in each different occupation?
        x. Plot the change in probability that a person makes more and less than $\$50k given the amount that they work per week.
2. Answer the following questions using the fundamentals of probability.
    (a) If A and B are independent, show that  ̄A and B,  ̄A and B,  ̄A and  ̄B are independent.
    (b) Suppose we send 30% of our products to company A and 70% of our products to company B. Company A reports that 5% of our products are defective and company B reports that 4% of our products are defective. For each probability below, compute the precise value by hand, and also write a short Python script to simulate the above scenario and estimate each probability be empirically examining the rates of each event.
    
        i. Find the probability that a product is sent to company A and it is defective.
        ii. Find the probability that a product is sent to company A and it is not defective.
        iii. Find the probability that a product is sent to company B and it is defective.
        iv. Find the probability that a product is sent to company B and it is not defective.
    (c) Show that for events A and B that P (A|B) > P (A) implies P (B|A) > P (B)
    
## Answers

All answers are given in lab

## Issues

### 2 B) ii)

I couldn't get the bit-wise & to work, so I had to add the total of that percentile the long way.

_ex._ Tried total_mid = **len(adults[['education-num','income']][((adults['education-num'] > per_25) & (adults['education-num'] <= per_50)])** for Q2, but it gave me the entire length of the data frame for some reason.

### 2 B) iii) 

Couldn't get the legend to work properly, so I ommited it :3

### 2 B) iv)

This takes a VERY long time to compute  with stats.cov(), so I took the first 4000 elements of the data and found the cov of that so I could at least have something, and then I let the program run as long as possible to compute the cov of the entire thing. I'll store the number somewhere so you don't have to run it (if you even planned on running it anyway :))

The cov is ~> 4.705193436213586; It took 4226.1184 seconds to calculate. 

### 2 B) vi - x)

I wasn't sure how to get the tables to display like the examples given in class, so I just got a large table for all of them... sorry :)

### 3 C)

I couldn't solve this proof, so I started it for a little bit of credit.