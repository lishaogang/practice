def producer():
	i = 0
	while i < 5:
		yield i
		i += 1

def wrapper():
	p = producer()
	try:
		yield from p
	finally:
		print("Done!")

if __name__ == "__main__":
	print("for loop")
	for item in wrapper():
		print(item)
	print("while loop:")
	w = wrapper()
	while True:
		try:
			print(w.send(None))
		except StopIteration:
			break
	