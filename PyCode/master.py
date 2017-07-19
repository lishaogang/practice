import time
import queue
from multiprocessing import freeze_support
from multiprocessing.managers import BaseManager


task_queue = queue.Queue()
result_queue = queue.Queue()

def get_t():
	global task_queue
	return task_queue
	
def get_r():
	global result_queue
	return result_queue
	
class QueueManeger(BaseManager):
    pass
	
def master():
	#windows下callable不能用lambda表达式
	QueueManeger.register('get_task', callable=get_t)
	QueueManeger.register('get_result', callable=get_r)
	#此处应该先验证address是否被占用
	m = QueueManeger(address = ('127.0.0.1', 5000), authkey = b'abc')
	
	m.start()
	#这时,task和task_queue不是同一个对象
	task = m.get_task()
	result = m.get_result()
	
	print('task == task_queue? ',task == task_queue)
	
	#不会先输出这个循环
	#应该是进程的实现有关
	#循环已经先执行了,但输出是写到缓存,并不是到终端
	for i in range(10):
		n = time.time()
		print('Put task ---', n)
		task.put(n)


	for i in range(10):
		try:
			r = result.get(timeout = 10)
			print('Result is ---',r)
		except queue.Empty:
			print('result queue is empty')

	m.shutdown()
	print('Master exit...')
	

if __name__ == '__main__':
	#Windows下, master()中的m.start()之前要执行 freeze_support()
	#这和进程的实现有关
	freeze_support()
	master()
	
