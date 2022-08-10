import numpy as np
import numpy.linalg as la
from collections import Counter as c


def norm(x):
    return np.sqrt(np.sum(x**2))

class KNN:
    k = 0
    data = None
    labels = None
    
    def __init__(self, k):
        self.k = k
        
    def train(self, vals, labs):
        self.data = vals
        self.labels = labs
    
    def predict(self, X):
    
        dist = [
            (self.labels[i], la.norm(X-self.data[i])) for i in range(len(self.data))
        ]
        
        k_nearest = sorted(dist, key = lambda X: X[1])
        k_nearest = k_nearest[:self.k]
        

        f = c([p[0] for p in k_nearest])
        return f