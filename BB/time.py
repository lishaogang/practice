x = 0
for a in range(1,10):
	for b in range(1,10):
		for c in range(1,10):
			for d in range(1,10):
				for e in range(1,10):
					for f in range(1,10):
						for g in range(1,10):
							for h in range(1,10):
	 							for i in range(1,10):
									if (((1 << a) | (1 << b) |(1 << c) | (1 << d) |(1 << e) | (1 << f) | (1 << g) | (1 << h) |(1 << i)) == 0x3FE):
							        	x = a*100 + b*10 + c
							        	y = d*100 + e*10 + f
							        	z = g*100 + h*10 + i
										if (y == 2*x && z == 3*x):
												print(x, y, z)