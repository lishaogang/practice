def testFun():
	lam = (lambda x: i*x for i in range(4))
	print(lam)
	return lam

for f in testFun():
	print(f)