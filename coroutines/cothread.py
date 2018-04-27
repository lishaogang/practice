from queue import Queue
from threading import Thread
import threading
from follow import coroutine,printer
import time
from parsers import *

global_lock = threading.Lock()

@coroutine
def threaded(target):
	messages = Queue()
	global global_lock
	def run_target():
		while True:
			item = messages.get()
			if item is GeneratorExit:
				with global_lock:
					target.close()
				return
			else:
				with global_lock:
					target.send(item)
	Thread(target = run_target).start()
	try:
		while True:
			item = (yield)
			messages.put(item)
	except GeneratorExit:
		messages.put(GeneratorExit)

if __name__ == "__main__":
	p = printer()
	f = filter_on_field("direction","North Bound",p)
	t1 = threaded(f)
	t2 = threaded(f)
	c1 = buses_to_dicts(t1)
	c2 = buses_to_dicts(t2)
	h1 = MyHandler(c1)
	h2 = MyHandler(c2)
	xml.sax.parse("./buses.xml", h1)
	xml.sax.parse("./buses.xml", h2)



	