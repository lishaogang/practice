from pre_analy_table import *
from lexer import Lexer

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
		

class Parser():
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
		return word
		
	def isTerminal(self,c):
		return c in self.terminals.keys()
		
	def analyse(self,input):
		#初始化
		self.lexer.setInput(input)
		st = StackWrapper()
		st.push('#')
		st.push('S')
		print('analysing....', input)
		process = StackWrapper()
		#分析开始
		word = self.nextWord()
		top = st.pop()
		while  True:
			#接收或匹配
			if self.isTerminal(top):
				#接受
				if top == '#' and top == word['value']:
					print('FINISH, this sentence is  grammatical')
					break
				#匹配
				#匹配数字
				if word['type'] == 'CONSTANT':
					word = self.nextWord()
				#匹配变量
				elif word['type'] == 'VARIABLE' and top == 'v':
					word = self.nextWord()
				#匹配操作符
				elif word['type'] == 'OPERATOR' and  top == word['value']:
					word = self.nextWord()
				#匹配函数
				elif word['type'] == 'FUNCTION' and top == word['value']:
					word = self.nextWord()
				else:
					exit('WRONG MATCHING,Giving:'+word['value']+',Expecting: '+top) #出错，不符合语法
			#推导
			else:
				try:
					#推导,选取产生式
					if word['type'] == 'VARIABLE':
						pro = self.table[top]['v']
						right = word['value'] if top == 'Q' else ','.join(pro[::-1])
						process.push(top+'-->'+right)
					elif word['type'] == 'CONSTANT':
						pro = self.table[top]['n']
						right = word['value'] if top == 'Q' else ','.join(pro[::-1])
						process.push(top+'-->'+right)
					else:
						pro = self.table[top][word['value']]
						process.push(top+'-->'+','.join(pro[::-1]))
				except:
					process.print()
					exit("WRONG DEDUCE——:"+top+'->'+str(word))
				#入栈
				if pro is None:
					exit("WRONG DEDUCE:"+top+'->'+str(word))
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
	A = Parser('f.gram')
	while True:
		s = input('请输入(q退出)：\n')
		if s == 'q':
			break
		process = A.analyse(s+'#')
		process.print()
		print(A.terminals)
	
	
	
	