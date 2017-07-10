class A(type):
    def __init__(self):
        print ("init")
    def __new__(cls,name='B',bases=(),attr={'age':18}):
        print( "new %s"%cls)
        return type.__new__(cls,name,bases,attr)

a = A()
print(a.__name__)
print(type(a))
