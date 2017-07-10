#coding:utf-8
#2017/6/15
def fun_1():
    print("hello")
    print("计算机")
    print("%d+%d=%d" % (1,2,1+2))

def fun_2():
    record = {"Micheal": 89,"Bob": 93, "Tracy": 89}

def triangles():
    L2 = [1]
    yield L2
    while(True):
        L1 = L2
        L2 = [1]
        i = 0;
        for v in L1[:-1]:
            L2.append(L1[i] + L1[i+1])
            i += 1
        L2.append(1)
        yield L2

def numbers():
    i = 0
    while(True):
        yield i
        i += 1
def add_end(L = []):
    L.append('END')
    return L
def normalize(name):
    return tuple(map(str.lower,name))

def odd(n):
    if n % 2 == 1:
        return None


def not_divisible_by(n):
    return lambda x : x % n > 0
def primes():
    it = numbers()
    for x in it:
        if x == 2:
            break
    while True:
        n = next(it)
        print('yied',n)
        yield n
        it = filter(not_divisible_by(n),it)

import functools
def log(out_param):
    def decorator(*inner_param,**inner_kw):
        def wrapper(*args,**kw):
            if hasattr(out_param,'__call__'):
                print('begin call %s()' % (out_param.__name__))
                decorator.__name__ = out_param.__name__
                ret = out_param(*args,**kw)
            else:
                print('Before %s %s()' % (out_param,inner_param[0].__name__))
                wrapper.__name__ = inner_param[0].__name__
                ret = inner_param[0](*args,**kw)
            print('End call')
            return ret
        if hasattr(out_param,'__call__'):
            return wrapper(*inner_param,**inner_kw)
        else:
            return wrapper
    return decorator




@log('execute')
def f(*args,**kw):
    for i in args:
        print(i)
@log
def g(*args,**kw):
    print('Function g')

import functools



class Student(object):
    def __init__(self):
        self.__name = 'Robbin'
        self._score = 1
        self.age = 1
    @property
    def score(self):
        print("__get__")
        return self._score

    @score.setter
    def score(self, value):
        if not isinstance(value, int):
            raise ValueError('score must be an integer!')
        if value < 0 or value > 100:
            raise ValueError('score must between 0 ~ 100!')
        self._score = value


if __name__ =="__main__":
    s = Student();
    print(s.score)
    s.score = 100
    print(s.score)



























#
