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
	return GExpression(left,right,opType)
		
class GExpression(BaseClass):
	def __init__(self, a, b, opType):
		self.left = a
		self.right = b
		self.opType = opType
		self.type = 'EXPRESSION'
		if self.opType == 'ln':
			self.left = Constant('e')
		

	
	def derivate(self, partial=None):
		if partial is not None and partial.type != 'VARIABLE':
			print('Error')
			return None
		if self.opType == 'sin':
			self.opType = 'cos'
			return self
		if self.opType == 'cos':
			self.opType = 'sin'
			return self
		if self.opType == 'tan':
			return GExpressionWrapper(Constant(1),
								GExpressionWrapper(None,self.right,'cos'),
								'/')
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
														GExpressionWrapper(Constant('e'),self.left,'ln'),
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
				return GExpressionWrapper(GExpressionWrapper(Constant('e'),self.left,'ln'),ydu,'*')
			
			exit('错误输入')
					
	def expression(self):
		#date: 18-4-27 21:11
		#memo: expression with too much redundant brackets which needs to be fixed
		#		this maybe should be done in a independent moduel 
		if self.opType in ['sin','cos','tan']:
			return self.opType+'('+self.right.expression()+')'
		l = '('+self.left.expression()+')'
		r = '('+self.right.expression()+')'
		
		if self.opType == '+':
			return self.left.expression() + '+' + self.right.expression()
		if self.opType == '-':
			return self.left.expression() + '-' + self.right.expression()
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
		
if __name__ == '__main__':
	
	a = Constant(2)
	x = Variable('X')
	x1 = Variable('X',3)
	apx = GExpressionWrapper(a,x,'+')
	amx = GExpressionWrapper(a,x,'-')
	x1dx = GExpressionWrapper(x1,x,'/')
	xp2 = GExpressionWrapper(x,a,'^')
	apx = GExpressionWrapper(a,x,'^')
	
	dominator = GExpressionWrapper(Constant(1),GExpressionWrapper(Constant('e'),Variable('X',-1),'^'),'-')
	
	#print(apx.derivate(x).expression())
	
	#print(apx.derivate(x).expression())
	#print(amx.derivate(x).expression())
	#print(x1dx.derivate(x).expression())
	#print(xp2.right.value)
	#print(xp2.derivate(x).expression())
	
		

	dominator = GExpressionWrapper(Constant(1),
                                        GExpressionWrapper(Constant('e'),
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






