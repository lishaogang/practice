import copy
import paraser
from de import *

class Constructor():
	def __init__(self,path):
		"""
		process: 应用 产生式 的顺序，是一个栈
		"""
		self.paraser = paraser.Paraser(path)
		self.process = None
		
	def getE(self,input):
		self.process = self.paraser.analyse(input)
		es = []
		
		while not self.process.isEmpty():
			pro = self.process.pop()
			symbols = self.getNonTerminals(pro)
			
			first_symbol = self.getFirstSymbol(pro)

			if (first_symbol in self.paraser.terminals or self.isNumber)and len(symbols) == 0:
				es = self.deduceToTerminal(pro,es)
			else:
				es = self.deduceToNonTerminal(pro,es)
		
		
		S = es[0]
		expression = self.EWrapper(left = None,right=S['operand'],op=S['operator'])
		
		return expression
			
	def deduceToTerminal(self,pro,es):
		valueOfPro = {'operator':None,'operand':self.EWrapper(pro[4:])}
		es.append(valueOfPro)
		return es
	def deduceToNonTerminal(self,pro,es):
		
		#pro 是一个字符串, 形如 S-->AB
		#且本应用的文法中，产生式右部有 0-2 个非终结符
		symbols = self.getNonTerminals(pro)
		first_symbol = self.getFirstSymbol(pro)
		
		#若使用的产生式为S-->A 则es中最近A的值即为S的值
		#若使用产生式 S-->aA 则归约aA为S的值
		if len(symbols) == 1:
			if first_symbol in self.paraser.terminals:
				if len(first_symbol) > 1:
					operand = self.EWrapper(left = None,right = es[-1]['operand'], op = first_symbol)
					operator = None
				elif first_symbol in ['+','-'] and symbols[0] == 'S':
					operator = None
					operand = self.EWrapper(left = None,right = es[-1]['operand'], op = first_symbol)
					
					
				else:
					operand = es[-1]['operand']
					operator = first_symbol if first_symbol not in ['(',')'] else None
				valueOfPro = {'operator':operator,'operand':operand}
				es[-1]=valueOfPro
			return es
		
		A = es[-1]
		B = es[-2]
		valueOfPro = {'operator':None,'operand':A['operand']}
		temp = {'operator':None,'operand':None}
		#如果此时使用的产生式为S -->aAB,则es中必然已经依序有了A和B的值
		#(1)A,B无操作符相连,则以a连接AB为S的值
		#(2)A,B有操作符相连,则以AB归约为S的值,a为S连接其它操作数的操作符
		if first_symbol in self.paraser.terminals:
			if B['operand'] is not None:
				op_of_s = first_symbol if B['operator'] is None else B['operator']
				operand = self.EWrapper(A['operand'],B['operand'],op_of_s)
				valueOfPro['operand'] = operand
				valueOfPro['operator'] = A['operator'] if B['operator'] is None else first_symbol
			else:
				valueOfPro['operator'] = first_symbol
				valueOfPro['operand'] = A['operand']
		#如果此时使用的产生式为S -->AB,则es中必然已经依序有了A和B的值
		#且B无操作符与A连接时,B必为空
		else:
			operand = self.EWrapper(A['operand'],B['operand'],B['operator'])
			valueOfPro['operand'] = operand
			valueOfPro['operator'] = A['operator']
				
		
		valueOfPro['operator'] = None if valueOfPro['operator'] in ['(',')'] else valueOfPro['operator']
		es = es[0:-2]
		es.append(valueOfPro)
		
		return es
		
	def getNonTerminals(self,pro):
		symbols = []
		for symbol in pro[4:].split(','):
			if symbol in self.paraser.non_terminals:
				symbols.append(symbol)
		return symbols
	def getFirstSymbol(self,pro):
		return pro[4:].split(',')[0]
	#待修改
	def EWrapper(self,left,right=None,op=None):
		if left == 'ε':
			return None
		if op == None:
			if isinstance(left,BaseClass):
				return left
			if self.isNumber(left):
				return Constant(float(left))
			if isinstance(right,BaseClass):
				return right
			if self.isNumber(right):
				return Constant(float(right))
			return Variable(left if left is not None else right)
		return GExpressionWrapper(left,right,op)
	def isNumber(self,s):
		try:
			float(s)
			return True
		except:
			pass
		try:
			import unicodedata
			unicodedata.numeric(s)
			return True
		except (TypeError, ValueError):
			pass
	 
		return False

if __name__ == '__main__':
	c = Constructor('g.gram')
	i = 'tan(i)'
	e = c.getE(i+'#')
	e = e.derivate()
	print(e.expression(),e.left,e.right)
	exit()
	'''	
	e = c.getE('i#')
	print(e.expression(),type(e))
	
	e = c.getE('i*i#')
	print(e.expression(),type(e))
	
	e = c.getE('sin(i)#')
	
	print(e.expression(),type(e))
	
	'''
	
	e = c.getE('i+i*i#')
	print(e.expression(),type(e))
	
	