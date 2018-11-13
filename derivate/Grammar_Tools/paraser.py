from Grammar_Tools.pre_analy_table import *
from Grammar_Tools.lexer import Lexer

class StackWrapper:
	class inner:
		def __init__(self, data = None, next = None):
			self.data = data
			self.next = next
			
	def __init__(self):
		self.stack = None
		self.head = self.stack
		self.tail = self.stack
	
	def pop(self):
		if self.head is None:
			print("pop fialed, stack is None")
			return None
		it = self.head
		self.head = self.head.next
		return it.data
	
	def push(self, data):
		it = self.inner(data,self.head)
		self.head = it
	def isEmpty(self):
		return self.head == None
		
	def print(self,end = '\n'):
		it = self.head
		print("start",end = end)
		while it is not None:
			print(it.data,end = end)
			it = it.next
		print("over")
		

class Paraser():
	def __init__(self,grammarPath):
		table_tools = PreAnalyTable(grammarPath)
		self.table = table_tools.getTable()
		self.terminals, self.non_terminals = table_tools.getSymbols()
		self.productions = table_tools.getProductions()
		self.lexer = Lexer()
	def nextWord(self):
		word = self.lexer.next()
		if word == None:
			exit('None Word')
		
		return word['value']
		
	def isTerminal(self,c):
		return c in self.terminals.keys()
		
	def analyse(self,input):
		#初始化
		self.lexer.setInput(input)
		st = StackWrapper()
		st.push('#')
		st.push('S')
		process = StackWrapper()
		#分析开始
		word = self.nextWord()
		top = st.pop()
		while  True:
			if self.isTerminal(top):
				#接受
				if top == word and top == '#':
					break
				#匹配
				try:
					
					float(top)
					float(word)
					word = self.nextWord()
				except:	
					if top == word:
						word = self.nextWord()
					else:
						print('WRONG MATCHING,Giving:'+word+',Expecting: '+top) #出错，不符合语法
						return
			else:
				#推导
				try:
					pro = self.table[top][word]
					process.push(top+'-->'+','.join(pro[::-1]))
				except:
					try:
						s = float(word)
						pro = self.table[top]['0']
						if pro == ['0']:
							process.push(top+'-->'+str(word))
						else:
							process.push(top+'-->'+','.join(pro[::-1]))
					except:
						print('错误输入, 缺少括号或未命名变量:'+word) #出错，未识别的单词
						return
				#入栈
				if pro is None:
					print("WRONG DEDUCE:"+top+'->'+word)
					return
				while pro != [] and pro is not None:
					t, pro = pro[0],pro[1:]
					if t != 'ε':
						st.push(t)
			top = st.pop()
		return process
		
		

		
if __name__ == '__main__':
	
	#analyse('i*#')
	#analyse('i++i')
	#analyse('i+j')
	A = Paraser('g.gram')
	A.analyse('i+i#')
	A.analyse('i+j#')
	A.analyse('i+i*j#')
	print(A.table)
	process = A.analyse('i+i+i+sin((-i))#')
	process.print()
	process = A.analyse('-i#')
	process.print()
	
	
	
	