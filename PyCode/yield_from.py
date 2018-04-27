def gen():
    r = 0
    while True:
        next = yield
        if next is None:
            print('Over')
            return r
        r += next
        
def wrapper(ganther):
    while True:
        r = yield from gen()
        print(r,'over')
        ganther.append(r)
        
r = []
w = wrapper(r)
w.send(None)
w.send(None)
for i in range(4):
    w.send(i)

w.send(None)
print(r)

def reader():
    for i in range(4):
        yield '<< %s' % i

def read_wrapper(g):
    yield from g
    print('----over')

wrap = read_wrapper(reader)
for i in wrap:
    pass
