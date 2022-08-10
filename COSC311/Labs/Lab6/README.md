# Lab 6, SHAP usage on Iris Data Set

## Tasks
   1. Begin by installing the shap library for Python. [SHAP Link](https://github.com/slundberg/shap).
   2. Do the following with 1) the Iris dataset from last lab, 2) the Adult dataset from last lab, and 3) the dataset you are using for Project 2. Be sure to provide thorough evaluations of the resulting visualizations; use examples from the plots and clear written descriptions to accompany each one. Remember that the ideas here are to help humans understand why a model makes the predictions that it does. So first come up with an explanation for yourself and then write it in a concise way to also convince your reader.
        
     (a) Train a model from the scikit-learn library on the data (e.g. support vector machine, tree classifier, multilayer perceptron). That is, your goal for the iris dataset is a multi-class decision of the iris species, for the adults, the decision is whether or not a person makes over $\$50k, and for your project data will depend on your own focus.
     (b) Using the shap package (see the linked documentation), plot the waterfall graph of SHAP values of the features in your data; do this for a couple different predictions, how do they differ for different class outputs? Try for both a correct and incorrect prediction.
     (c) Try the force plot on some individual SHAP values and also the entire set of predictions. What do these indicate about the influence of various features in your data?
     (d) Try the above with a second model and compare and contrast the results. If you were to use one of these in a practical setting (i.e. your job/income/business depends on it), which would you choose and why?
        
## Answers

All answers are in notebook.

*SIDE NOTE*

All predictions give a % chance of one or the other, therefore this would determine how accurately the algorithm can predict a random value based off of those %. 

## Issues

I'm not really sure how to explain what the shap values mean. I know they determine how influencial each attribute is, but I don't know if that is all I need to say or not.

The force plots for adults is too large, therefore it take too long and literally tells me that. 
