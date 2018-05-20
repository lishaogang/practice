from pre_analy_table import *

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
		
	def print(self):
		it = self.head
		print("start")
		while it is not None:
			print(it.data)
			it = it.next
		print("over")
		
class TableWrapper:
	def __init__(self,path):
		self.table = getTable(path)
	def add(self,key,value):
		self.table[key] = value
	def setTable(self,table):
		self.table = table
	def print(self):
		print(self.table)

class Paraser():
	def __init__(self,grammarPath):
		self.table = TableWrapper(grammarPath)
		self.terminals, self.non_terminals= getSymbols()
		self.productions = getProductions()
	def nextWord(self,input):
		if input != '' and input is not None:
			return input[0],input[1:]
		
	#待完善
	def isTerminal(self,c):
		return c in self.terminals.keys()
		
	def analyse(self,input):
		#初始化
		st = StackWrapper()
		st.push('#')
		st.push('S')
		print('analysing....', input)
		word, input = self.nextWord(input)
		top = st.pop()
		while  True:
			if self.isTerminal(top):
				#接受
				if top == word and top == '#':
					print('FINISH, this sentence is  grammatical')
					break
				#匹配
				if top == word:
					word, input = self.nextWord(input)
				else:
					exit('WRONG MATCHING,Giving:'+word+',Expecting: '+top) #出错，不符合语法
			else:
				#推导
				try:
					pro = self.table.table[top][word]
				except KeyError:
					exit('UNIDENTIFIED WORD:'+word) #出错，未识别的单词
				#入栈，可修改产生式的数据结构，使得产生式数据与此处入栈逻辑分离
				if pro is None:
					exit("WRONG DEDUCE:"+top+'->'+word)
				while pro != [] and pro is not None:
					t, pro = pro[0],pro[1:]
					if t != 'ε':
						st.push(t)
				
			top = st.pop()
		
		

		
if __name__ == '__main__':
	global table
	s = StackWrapper()
	#print(t.table['S']['+'])
	#analyse('i*#')
	#analyse('i++i')
	#analyse('i+j')
	A = Paraser('g.gram')
	A.analyse('i+i#')
	A.analyse('i+j#')
	A.analyse('i+i*j#')
	print(A.terminals)
	print(A.non_terminals)
	print(A.productions)
	
	
	
	