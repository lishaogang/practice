#the lexer turn  CHARACTER STREAM into WORD STREAM
#the valid input of characters are divided into such 3 types:
#	1. alphabet
#	2. number
#	3. symbol

class lexer():
	def __init__(self, input=None, reservedWord = ()):
		self.input = input
		self.length = len(input) if input is not None else 0
		self.p = -1
		self.reservedWord = reservedWord
		pass
	def nextChar(self):
		self.p += 1
		return self.input[self.p] if self.p < self.length else None
	
	def back(self):
		self.p -= 1
		
	def next(self):
		c = self.nextChar();
		while c == ' ':
			c = self.nextChar()

		if c is None:
			return None
			
		if c.isdigit():
			return self.digit(c)
		
		if c.isalpha() or c == '_':
			return self.identifier(c)
		
		return self.operator(c)
		
	def identifier(self,s):
		c = self.nextChar()
		while c is not None and (c.isalnum() or c == '_'):
			s += c
			c = self.nextChar()
		self.back()
		
		return {'type':'RESERVEDWORD','value':s} if s in self.reservedWord else {'type':'IDENTIFIER','value':s}
		
	def digit(self,s):
		c = self.nextChar()
		while c is not None and c.isdigit():
			s += c
			c = self.nextChar()
		self.back()
		return {'type':'CONSTANT','value':s}
		
	def operator(self,s):
		return {'type':'OPERATOR','value':s}
		
if __name__ == '__main__':
		s = 'in t, i1 , j;i+j;'
		l = lexer(input=s,reservedWord=('int','float'))
		c = l.next()
		while  c is not None:
			print(c)
			c = l.next()
	