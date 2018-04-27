from multiprocessing import Process
import os
import time
def run_proc(name):
    print("3> sub:proc %s(%d) is running...\
	and now is %d" % (name, os.getpid(), time.time()))

if __name__ == '__main__':
    print('1> main:Parent proc %d is running...and now is %d'\
	% (os.getpid(),time.time()))
    p = Process(target = run_proc, args = ('test',))
    print('2> main:CHILD PROC START and now is %d' % time.time())
    p.start()
    p.join()
    print('4> main:END and now is %d' % time.time())
