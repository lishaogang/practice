err = 0.1
step = 0.1
def f():
	x = -5.0
	y = -3.0
	while y < 3.0:
		while x < 5.0:
			if(abs(x*x - x*y + y*y - 3) < err):
				print('x')
				print(x)
			else:
				pass
				#print(abs(x*x - x*y + y*y - 3))
				#print('')
			x = x + step
		#print('\n')
		x = 0.0
		y = y + step

if __name__ == '__main__':
	f()