import logging;logging.basicConfig(level = logging.INFO)
def producter():
	r = 0
	while r < 10:
		sig = yield r
		r += 1
		#print(sig,"in producter.")
	print("Producter Done!")

def processor():
	prod = producter()
	i = 0
	"""
	while True:
		try:
			sig = yield from prod
			next(prod)
		except StopIteration:
			break
	"""
	try:
		sig = yield from prod
	finally:
		print("Processor Done!")
	

def wrapper():
	proc = processor()
	yield from proc

if __name__ == "__main__":
	for item in processor():
		print("__main__",item)
	print("__main__ Done!")

	
	package = wrapper()
	print(package,next(package))