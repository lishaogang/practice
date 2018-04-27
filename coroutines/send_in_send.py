from follow import coroutine
import time
@coroutine
def drive(target):
	while True:
		event = (yield)
		target.send(event)

@coroutine
def broadcast(targets):
	while True:
		event = (yield)
		for target in targets:
			time.sleep(2)
			target.send(event)
@coroutine
def fun():
	for i in range(1,3):
		m = (yield)
		print("Hello")

def main():
	drive(broadcast([fun(), fun(),fun()])).send(None)
	drive(broadcast([fun(), fun(),fun()])).send(None)

if __name__ == "__main__":
	main()
