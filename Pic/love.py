from math import floor,pow
from decimal import Decimal
def f(x,y):
	a = Decimal(floor(y//5))
	b = Decimal(-5*floor(x)-floor(y)%5)
	c = Decimal(2**b)
	r = Decimal(a*c)%2
	r = floor(r)
	return r > 1/2
	
def pic(n):
	for y in range(n+4,n-1,-1):
		for x in range(0,14):
			print('💗',end='') if f(x,y) else print('  ',end='')
		print()
			
if __name__ == '__main__':
	n = 179057560799192115125
	pic(n)