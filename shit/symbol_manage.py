from pa import Parser
class Symbol:
	def __init__(self,name,kind,addr,val=0,size=1):
		self.name = name
		self.kind = kind
		self.addr = addr
		self.val = val
		self.size = size

class SymboList:
	def __init__(self,env):
		self.enviroment = env
		self.symbols = []
	
	def insert(self,x):
		if x in self.symbols:
			exit('duplicate definition of '+x)
		self.symbols.append(x)
	
	def remove(self,x):
		try:
			self.symbols.remove(x):
		except:
			exit(x+'dose not exist')
	def lookup(self,x):
		return x in self.symbols



if __name__ == '__main__':
	SL = SymboList('GLOBAL')
	for i in range(10):
		SL.insert('S'+str(i))
	while True:
		s = input('请输入查询符号')
		s = 