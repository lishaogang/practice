import tkinter as tk
import tkinter.messagebox as messagebox
import Grammar_Tools.paraser as paraser
import Grammar_Tools.constructor as constructor
c = constructor.Constructor('g.gram')
window=tk.Tk()  
window.title('DIFF')  
window.geometry('1000x200')  
expressionInput=tk.Entry(window)  
label1 = tk.Label(window, text='表达式')
label1.pack() 
expressionInput.pack()
label2 = tk.Label(window,text='阶数')
label2.pack()
orderInput = tk.Entry(window)
orderInput.pack()
def getInput():  
	e=expressionInput.get()		
	n=orderInput.get()
	return inputWrapper(e,n)

def inputWrapper(s,n):
	r = ''
	for i in s:
		r += i
		if i in ['(',')']:
			r += i
	
	try:
		n = int(n)
		if n < 0:
			return None,None
	except:
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
def diff():
		r,n = getInput()
		if r is None or n is None:
			messagebox.showinfo('Message', '%s,%s\n请输入表达式，且求导阶数应为非负'%(r,n))
			return
		e = c.getE(r)
		for i in range(n):
			if e is not None:
				e = e.derivate()
		if e is not None:
			t.delete(0.0, tk.END)
			result = '该式子的%s阶导为： %s' % (n,outputWrapper(e.expression()))
			t.insert('insert',result)
			

b1=tk.Button(window,text='diff',width=15,height=2,command=diff)  
b1.pack()  

t=tk.Text(window,height=1)  
t.pack()  
window.mainloop() 