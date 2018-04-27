import time
def coroutine(func):
	def start(*args, **kwargs):
		cr = func(*args, **kwargs)
		cr.send(None)
		return cr
	start.__name__ = func.__name__
	return start

@coroutine
def grep(pattern,target):
	print("Looking for %s" % pattern)
	try:
		while True:
			line = (yield)
			if pattern in line:
				#print("found",pattern)
				target.send(line)
	except GeneratorExit:
		print("Going away. Bye")

@coroutine
def printer():
	while True:
		line = (yield)
		print(line)

@coroutine
def braodcast(targets):
	while True:
		item = (yield)
		for target in targets:
			target.send(item)

def follow(thefile, target):
	thefile.seek(0,2)
	while True:
		line = thefile.readline()
		if not line:
			time.sleep(0.1)
			continue
		target.send(line)

if __name__ == "__main__":
	f = open("log")
	p = printer()
	follow(f,
			braodcast([grep("python",p),
						grep("java",p),
						grep("c++",p)]))