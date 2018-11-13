class Base():
	pass
class A(Base):
	def __repr__(self):
		return 'A'
		
class B(Base):
	
	def __repr__(self):
		return 'B'
a = A()
b = B()
print(isinstance(a,Base))