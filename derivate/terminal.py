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
def outputWrapper(s):
	r = ''
	i = 0
	while i < len(s)-4:
		t = s[i:i+5]
		if t.startswith('-(-') and t.endswith(')'):
			r += t[3]
			i+=4
		else:
			r+= t[0]
			i += 1
	r += s[-4:]
	r = r.replace('()','')
	r = r.replace('--','')
	r = r.replace('+-','-')
	r = r.replace('-+','-')
	return r
	
def caculate():
	s,n = inputWrapper()
	if s is None or n is None:
		return
	e = c.getE(s)
	for i in range(n):
		if e is not None:
			e = e.derivate()
	if e is not None:
			print('\n------------------------------\n')
			print('该式的',n,'阶导',outputWrapper(e.expression()))
			print('\n------------------------------\n')
	
if __name__ == '__main__':
	while True:
		caculate()