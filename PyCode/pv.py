def consumer():
    r = ''
    print('start')
    while True:
        print('while start')
        n = yield r
        print('--',n,'--',r,'--')
        if not n:
            print('not n')
            return
        print('[CONSUMER] Consuming %s...' % n)
        r = '200 OK'

def produce(c):
    c.send(None)
    next(c)
    n = 1
    print('[PRODUCER] Producing %s...' % n)
    r = c.send(n)
    exit()
    print('[PRODUCER] Consumer return: %s' % r)
    c.close()

c = consumer()
produce(c)