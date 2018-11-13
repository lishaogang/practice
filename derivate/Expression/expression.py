import math
class BaseClass():
	def __init__(self, name, value, type):
		self.name = name
		self.value = value
		self.type = type
	
	def derivate(self,partial):
		pass
	
	def expression(self):
		pass

G_STATIC_COUNT = 0		

class Constant(BaseClass):
	def __init__(self,value):
		global G_STATIC_COUNT
		G_STATIC_COUNT += 1
		self.value = value
		self.name = 'CONST_' + str(G_STATIC_COUNT)
		self.type = 'CONSTANT'
	
	def derivate(self,partial=None):
		return Constant(0)
		
	def expression(self):
		if self.value == math.e:
			return 'e'
		if self.value == math.pi:
			return 'pi'
		return str(self.value)
		
class Variable(BaseClass):
	def __init__(self, name, value = None):
		self.name = name
		self.value = value
		self.type = 'VARIABLE'
		
	def derivate(self,partial=None):
		if partial is None or partial.name == self.name:
			return Constant(1)
		return Constant(0)
	
	def expression(self):
		return str(self.name)
		
		
def GExpressionWrapper(left, right, opType):
	#do some preparation here
	
	if opType in ['+','-'] and left is None:
		left = Constant(0)
	if opType == '/':
		if right.type == 'CONSTANT' and right.value < 10e-8:
			exit('除数不能为0')
	if opType == 'ln' or opType == 'log':
		if right.type == 'CONSTANT' and right.value <= 0:
			exit(opType+'操作数不能为非正数')
		if left is not None and left.type == 'CONSTANT':
			if left.value <= 0 or left.value == 1:
				exit(opType+'底数不能为非正数或1')
	try:
		if right.type == 'CONSTANT':
			if right.value == math.e:
				if opType == 'ln':
					return Constant(1)
			if right.value == 1:
				if opType == '*':
					return left
				if opType == 'ln' or opType == 'log':
					return Constant(0)
			if right.value < 10e-8:
				if opType == '+':
					return left
				if opType == '*':
					return Constant(0)
				if opType == '^':
					return Constant(1)
		if left.type == 'CONSTANT':
			if left.value == 1:
				if opType == '*':
					return right
			if left.value < 10e-8:
				if opType == '+':
					return right
				if opType == '*' or opType == '/' or opType == '^':
					return Constant(0)
				if opType == 'ln' or opType == 'log':
					if right.type == 'CONSTANT' and right.value == 1:
						return Constant(1)
		if left.value == right.value and left.value < 10e-8:
			return Constant(0)
	except:
		pass
				
	return GExpression(left,right,opType)
		
class GExpression(BaseClass):
	def __init__(self, a, b, opType):
		self.left = a
		self.right = b
		self.opType = opType
		self.type = 'EXPRESSION'
		if self.opType == 'ln':
			self.left = Constant(math.e)
		
	def derivate(self, partial=None):
		if partial is not None and partial.type != 'VARIABLE':
			print('Error')
			return None
		if self.opType == 'sin':
			cos = GExpressionWrapper(None,self.right,'cos')
			return GExpressionWrapper(cos,self.right.derivate(partial),'*')
			
		if self.opType == 'cos':
			sin = GExpressionWrapper(None,self.right,'sin')
			n_sin = GExpressionWrapper(Constant(0),sin,'-')
			return GExpressionWrapper(n_sin,self.right.derivate(partial),'*')
		if self.opType == 'tan':
			cos = GExpressionWrapper(None,self.right,'cos')
			ct = GExpressionWrapper(cos,Constant(2),'^')
			r = GExpressionWrapper(self.right.derivate(),
								ct,
								'/')
			return r
		if self.opType == '+' or self.opType == '-':
			return GExpressionWrapper(self.left.derivate(partial),
					self.right.derivate(partial), self.opType)
		
		if self.opType == '*':
			return GExpressionWrapper(GExpressionWrapper(self.left,self.right.derivate(partial),'*'),
									GExpressionWrapper(self.right,self.left.derivate(partial),'*'),
									'+')
		if self.opType == '/':
			vdu = GExpressionWrapper(self.right, self.left.derivate(partial),'*')
			udv = GExpressionWrapper(self.left, self.right.derivate(partial),'*')
			vv = GExpressionWrapper(self.right,Constant(2),'^')
			return GExpressionWrapper(GExpressionWrapper(vdu,udv,'-'),vv,'/')
		
		#ln(E) left operator is e, right operator is E, opType is ln
		if self.opType == 'ln':
			return GExpressionWrapper(self.right.derivate(partial),self.right,'/')
		#loga(E) left operator is a, right operator is E, opType is log	
		if self.opType == 'log':
			return GExpressionWrapper(self.right.derivate(partial),
									GExpressionWrapper(self.right,
														GExpressionWrapper(Constant(math.e),self.left,'ln'),
														'*'),
									'/')
			
		
		if self.opType == '^':
			#y = u^a, assume a is  constant
			if self.right.type == 'CONSTANT':
				du = self.left.derivate(partial)
				udu = GExpressionWrapper(du,self.left,'*')
				base = GExpressionWrapper(Constant(self.right.value),udu,'*')
				return GExpressionWrapper(base,Constant(self.right.value-1),'^')
			
			#y = a^u, assume a is constant
			if self.left.type == 'CONSTANT':
				du = self.right.derivate(partial)
				ydu = GExpressionWrapper(du,self,'*')
				return GExpressionWrapper(GExpressionWrapper(Constant(math.e),self.left,'ln'),ydu,'*')
			
			print('错误输入')
			return
					
	def expression(self):
		#date: 18-4-27 21:11
		#memo: expression with too much redundant brackets which needs to be fixed
		#		this maybe should be done in a independent moduel 
		if self.opType in ['sin','cos','tan']:
			return self.opType+'('+self.right.expression()+')'
		l = self.left.expression()
		r = self.right.expression()
		
		try:
			if self.right.type == 'EXPRESSION':
				r = '('+r+')'
			if self.left.type == 'EXPRESSION':
				l = '('+l+')'
		except:
			pass
		if self.opType == '+' or self.opType == '-':
			l = self.left.expression()
			r = self.right.expression()
			if self.left.type == 'CONSTANT' and self.left.value in [1,0]:
				l = ''
			if self.right.type == 'CONSTANT' and self.right.value in [1,0] and self.opType != '-':
				r = ''
			op = '' if l == '' and self.opType == '+' else self.opType
			return l+ op + r
		
		if self.opType == '*':
			return l + '*' + r
		if self.opType == '/':
			return l+ '/'+ r
		if self.opType == '^':
			return l + '^' + r
		if self.opType == 'ln':
			return 'ln' + r
		if self.opType == 'log':
			return 'log' + l + r
			
	def type(self):
		return self.type
	
	def IO_traverse(self):
		self.left.IO_traverse()
		print(str(self.name))
		self.right.IO_traverse()
		
if __name__ == '__main__':
	
	a = Constant(2)
	x = Variable('X')
	x1 = Variable('X',3)
	apx = GExpressionWrapper(a,x,'+')
	amx = GExpressionWrapper(a,x,'-')
	x1dx = GExpressionWrapper(x1,x,'/')
	xp2 = GExpressionWrapper(x,a,'^')
	apx = GExpressionWrapper(a,x,'^')
	
	dominator = GExpressionWrapper(Constant(1),GExpressionWrapper(Constant(math.e),Variable('X',-1),'^'),'-')
	
	#print(apx.derivate(x).expression())
	
	#print(apx.derivate(x).expression())
	#print(amx.derivate(x).expression())
	#print(x1dx.derivate(x).expression())
	#print(xp2.right.value)
	#print(xp2.derivate(x).expression())
	
		

	dominator = GExpressionWrapper(Constant(1),
                                        GExpressionWrapper(Constant(math.e),
                                                            Variable('X',-1),
                                                            '^'),
                                        '+')
	
	numerator = Constant(1)
        
	sigmoid = GExpressionWrapper(numerator, dominator, '/')
        
    #sigmoid(x) = 1/(1+e^(-x))
	#sigmoid(x)dx = (e^(-x))/(1+e^(-x))^2
	lnx = GExpressionWrapper(Constant(2),xp2,'ln')
	print(lnx.derivate(x).expression())
	print(sigmoid.derivate(x).expression())






