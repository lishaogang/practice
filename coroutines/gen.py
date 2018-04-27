def gen():
	n = 0
	sig = yield n
	while True:
		n+=1
		sig = yield n
		if sig is not None:
			print(sig,n)


if __name__ == "__main__":
	g = gen()
	for i in range(0,9):
		if i == 7:
			print("next is send")
			t = g.send(99)
			print(t,"t")
			print("send is over")
		print(next(g))

		"""
		每次next(g)调用会暂停在yield
		g.send()进入gen()时, n的从暂停处开始，再次运行到yield暂停，
		调用g.send()时可以接收其返回值
		"""