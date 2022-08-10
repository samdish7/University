import numpy as np
import math
from collections import Counter

def mean(x):
    """calculate and return the mean of a numpy array"""
    sum = 0
    for i in range(len(x)):
        sum += x[i]
    return sum/len(x)

def median(x):
    """return the 'middle value' of the array x after sorting"""
    tmp = sorted(x)
    # use // for integer division (i.e. round down)
    return tmp[len(x) // 2] if len(x) % 2 == 1 else (tmp[len(x) // 2] + tmp[(len(x) // 2)-1]) / 2

def mode(xs):
    counts = Counter(xs)
    return np.array([x[0] for x in counts.items() if x[1] == max(counts.values())])

def quantile(xs, q):
    """generalize the median to the q-percent quantile -- q is a float in range (0,1)"""
    tmp = sorted(xs)
    return tmp[ int(len(tmp) * q) ]

def interquartile_range(xs):
    """return difference of the 25% quantile and 75% quantile"""
    return quantile(xs, 0.75) - quantile(xs, 0.25)

def spread(x):
    """one way to measure the spread of data"""
    return max(x) - min(x)

def center(xs):
    return np.array([x - mean(xs) for x in xs])

def var(xs):
    """return variance of x -- the average squared distance from the mean"""
    return mean([x**2 for x in center(xs)])

def std(xs):
    return math.sqrt(var(xs))

def cov(xs, ys):
    """Take two lists of observations and compute their covariance"""
    assert len(xs) == len(ys)
    cx = center(xs)
    cy = center(ys)
    return mean([cx[i]*cy[i] for i in range(len(cx))])

def corr(xs, ys):
    """Calculate the (Pearson) correlation coefficient"""
    return cov(xs,ys)/(std(xs)*std(ys))

def uniform_pdf(x,a,b):
    return 1/(b-a) if a <= x and x <= b else 0

def uniform_cdf(t,a,b):
    if t < a:
        return 0
    if t > b:
        return 1
    return (t-a)/(b-a)

def normal_pdf(x, mu, sigma):
    return (1/(sigma*np.sqrt(2 * np.pi))) * np.exp(-1*(x - mu)**2 / (2 * sigma**2))

def normal_cdf(x, mu, sigma):
    return (1 + math.erf((x-mu)/(sigma*np.sqrt(2))))/2

def binom_draw(n,p):
    """Generate one draw from a Binomial(n,p) distribution"""
    return np.sum(np.random.choice([0,1],size=n,p=[1-p,p]))

def inverse_normal_cdf(p, mu, sigma):
    """Approximate smallest x such that normal_cdf(x,mu,sigma) >= p"""
    low = -1 # TODO: make this dynamic based on mu and sigma
    while( normal_cdf(low, mu, sigma) > 1e-10 ):
        low *= 2
    high = 1
    while( normal_cdf(high, mu, sigma) < 1-1e-10 ):
        high *= 2
    
    # Structure of binary search: 
    #  - check middle: (high+low)/2
    #  - if cdf(middle) < p, low = middle
    #    else high = middle
    #  - stop when |high-low| < tolerance
    
    tol = 1e-5

    # as a backup to avoid weirdness when convergence takes too long,
    # could build in a max iteration count too...
    
    while abs(high-low) > tol:
        mid_x = (low+high)/2
        mid_p = normal_cdf(mid_x, mu, sigma)
        
        if mid_p < p:
            low = mid_x
        else:
            high = mid_x
            
    return mid_x

def normal_probability_above(t, mu, sigma):
    """Calculate P(X > t)"""
    # int from t to inf is 1 - int from -inf to t
    return 1 - normal_cdf(t,mu,sigma)

def normal_probability_between(a,b,mu,sigma):
    return normal_cdf(b,mu,sigma) - normal_cdf(a,mu,sigma)

def normal_probability_outside(a,b,mu,sigma):
    return 1 - normal_probability_between(a,b,mu,sigma)

def normal_upper_bound(p, mu, sigma):
    """Tell us what value z is such that P(X <= z) = p"""
    return inverse_normal_cdf(p, mu, sigma)

def normal_lower_bound(p, mu, sigma):
    """get z so that P(X >= z) = p"""
    return inverse_normal_cdf(1-p, mu, sigma)

def normal_two_side_bound(p, mu, sigma):
    """Get the range around mu so that P(a < X < b) = p"""
    tail = (1-p)/2
    
    # bottom because we want upper bound the left tail
    bottom = normal_upper_bound(tail, mu, sigma)
    
    # top because want to lower bound the right tail
    top = normal_lower_bound(tail, mu, sigma)
    
    return bottom, top

def two_sided_p(x, mu, sigma):
    if x >= mu:
        return 2 * normal_probability_above(x,mu,sigma)
    else:
        return 2 * normal_cdf(x,mu,sigma)

def ab_parameters(n,N):
    mu = n/N
    sigma = math.sqrt(mu * (1-mu) / N)
    return mu, sigma

def ab_statistic(nA, NA, nB, NB):
    mu_A, sigma_A = ab_parameters(nA, NA)
    mu_B, sigma_B = ab_parameters(nB, NB)
    return (mu_A - mu_B/math.sqrt(sigma_A**2 + sigma_B**2))
    
# something you will see...
# we can make this do the print, but *only* when this
# file is run as a standalone program, not through an import.
# when a python program runs, it sets the dunder __name__ 
# variable to be its context in the greater program.
# So the program that got ran through python is __main__
# typically used if you want have some tests here but 
# don't run the tests when you import into other programs
if __name__ == '__main__':
    print('testing stats.py...')
    
    print(f'the mean of [1,2,3,4,5] is {mean(np.array([1,2,3,4,5]))}')
    # assert(mean(np.array([1,2,3,4,5])) == 3.5)
    print(f'the median of [2,5,4,3,1] is {median([2,5,4,3,1])}')
    print(f'the median of [2,5,4,3] is {median([2,5,4,3])}')
    print(f'the variance of [2,5,4,3] is {var([2,5,4,3])}')
    print(f'the standard deviation of [2,5,4,3] is {std([2,5,4,3])}')
    print(f'the mode of {[1,2,3,4,3,2,3,1,2,1,1,2,3,2]} is {mode([1,2,3,4,3,2,3,1,2,1,1,2,3,2])}')
    print(f'the 20th percentile of {[1,2,3,4,3,2,3,1,2,1,1,2,3,2]} is {quantile([1,2,3,4,3,2,3,1,2,1,1,2,3,2], 0.2)}')
    print(f'the IQR of {[1,2,3,4,3,2,3,1,2,1,1,2,3,2]} is {interquartile_range([1,2,3,4,3,2,3,1,2,1,1,2,3,2])}')