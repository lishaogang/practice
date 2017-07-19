from multiprocessing import Process,Queue
import os
import time

def run_proc(name,q):
    print("sub:proc %s(%d) is running..." % (name, os.getpid()))
    time.sleep(5)
    q.put(9,block = False)

if __name__ == '__main__':
    q = Queue()
    print('main:Parent proc %d is running... and semaphore is' % (os.getpid()))
    p = Process(target = run_proc, args = ('test',q))
    print('main:CHILD PROC START')
    p.start()
    print('main :semaphore is', q.get())
    p.join()
    print('main:END')
