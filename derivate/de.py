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
	
	def derivate(self,partial):
		return Constant(0)
		
	def expression(self):
		return str(self.value)
		
class Variable(BaseClass):
	def __init__(self, name, value = None):
		self.name = name
		self.value = value
		self.type = 'VARIABLE'
		
	def derivate(self,partial):
		if partial.name == self.name:
			return Constant(1)
		return Constant(0)
	
	def expression(self):
		return str(self.name)
		
		
def GExpressionWrapper(left, right, opType):
	#do some preparation here
	return GExpression(left,right,opType)
		
class GExpression(BaseClass):
	def __init__(self, a, b, opType):
		self.left = a
		self.right = b
		self.opType = opType
	
	def derivate(self, partial):
		if partial.type != 'VARIABLE':
			print('Error')
			return None
		if self.opType == 'plus' or self.opType == 'minus':
			return GExpressionWrapper(self.left.derivate(partial),
					self.right.derivate(partial), self.opType)
					
	def expression(self):
		if self.opType == 'plus':
			return self.left.expression() + '+' + self.right.expression()
		if self.opType == 'minus':
			return self.left.expression() + '-' + self.right.expression()
	def type(self):
		return 'OPERATION'
		
if __name__ == '__main__':
	a = Constant(2)
	x = Variable('X')
	apx = GExpressionWrapper(a,x,'plus')
	amx = GExpressionWrapper(a,x,'minus')
	print(apx.derivate(x).expression())
	print(amx.derivate(x).expression())
	
		
		
		
		
		
		