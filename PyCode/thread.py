import threading
import time
import os
from multiprocessing import Queue
from multiprocessing.managers import BaseManager

local_school = threading.local()

def process_student():
    std = local_school.std
    print('THIS IS', std,'in (',threading.current_thread().name,')')

def process_thread(name):
    local_school.std = name
    process_student()

if __name__ == '__main__':
    t1 = threading.Thread(target = process_thread, args = ('Andy',),
        name = 'THREAD-A')
    t2 = threading.Thread(target = process_thread, args = ('Mary',),
        name = 'THREAD-B')
    t1.start()
    t2.start()
