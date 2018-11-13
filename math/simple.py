l = [1,2,3]
t = [4,5,6]
def neg(l):
    for i in range(len(l)):
        l[i] = -l[i]

def add(l,t):
    r = []
    for i in range(min(len(l),len(t))):
        r.append(l[i]+t[i])
    return r

def minus(l,t):
    neg(t)
    return add(l,t)

minus(t,l)
print('l:',l,"t:",t)
