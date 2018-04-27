import time


def ping():
    count = 1
    try:
        pi,po = yield s
        print('ping ' + str(count))
        count += 1     
        time.sleep(1)
        po.send([po, pi])
    except StopIteration:
        pass


def pong():
    count = 1
    try:
        po, pi = yield s
        print('pong ' + str(count))
        count += 1
        time.sleep(1)
        pi.send([pi,po])
    except StopIteration:
        pass


s = ping()
r = pong()
s.send(None)
r.send(None)
s.send([s,r])