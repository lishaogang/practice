from multiprocessing.managers import BaseManager
import time, sys
import queue

class QueueManeger(BaseManager):
    pass

QueueManeger.register('get_task')
QueueManeger.register('get_result')

server_addr = '127.0.1'
manager = QueueManeger(address = (server_addr,5000), authkey=b'abc')
manager.connect()
task = manager.get_task()
result = manager.get_result()

for i in range(10):
	try:
		n = task.get(timeout = 1)
		print('run task %d * %d' %(n,n))
		r = '%d * %d = %d' % (n,n,n*n)
		#time.sleep(1)
		result.put(r)
	except queue.Empty:
		print('task queue is empty')
	
print('worker exit...')