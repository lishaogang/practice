import Grammar_Tools.paraser as paraser
import Grammar_Tools.constructor as constructor
import re
c = constructor.Constructor('g.gram')
def inputWrapper():
	s = None
	while s is None or s == '':
		s = input('请输入式子:(quit 退出)\n ')
		if s in ['q','quit']:
			exit()
	r = ''
	for i in s:
		r += i
		if i in ['(',')']:
			r += i
	
	try:
		n = int(input('请输入阶数:\n'))
		if n < 0:
			print('阶数只能为非负整数')
			return None,None
	except:
		print('阶数只能为非负整数')
		return None,None
	
	return r+'#',n



if __name__ == '__main__':
    s,n = inputWrapper()
    if s is None or n is None:
        exit()
    e = c.getE(s)
    e.IO_traverse()
    