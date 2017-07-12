# -*- coding: utf-8 -*-

def fact(n):
    '''


    >>> fact(1) == 1
    True
    >>> fact(3) == 6
    True
    >>> fact(4) == 24
    True
    '''
    if n < 1:
        raise ValueError()
    if n == 1:
        return 1
    return n * fact(n - 1)

if __name__ == '__main__':
    import doctest
    doctest.testmod()
