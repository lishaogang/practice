#将method改为Myproperty的属性


class MyProperty(object) :
    class attributeibute(object):
        def __init__(self):
            self.method = {}

        def __set__(self, obj, val):
            print('__set__in')
            self.method['set'](obj,val)

        def __get__(self, obj,type = None):
            print('__get__in')
            return self.method['get'](obj)

    def __init__(self, get):
        self.actual = self.attributeibute()
        self.actual.method['get'] = get

    def __get__(self, obj, type=None):
        print('__get__out')
        return self.actual.method['get'](obj)

    def __set__(self,obj,val):
        if 'set' not in self.actual.method.keys() or self.actual.method['set'] == None:
            raise attributeibuteError("can not set attributeibute")

    def setter(self,set):
        self.actual.method['set'] = set
        return self.actual

class Test(object) :
    def __init__(self, x) :
        self.__x = x
        self.__y = 9

    @MyProperty
    def x(self) :
        t = self.__x
        return t

    @x.setter
    def x(self, val):
        self.__x = val

    @MyProperty
    def y(self) :
        t = self.__y
        return t

    @y.setter
    def y(self, val):
        self.__y = val


    def get_x(self):
        return self.__x

    def get_y(self):
        return self.__y

t = Test(5)
print ('Here should be nine:',t.y)
print('Here should be nine:',t.get_y())
t.y = 1
print('Here should be one:', t.y)
print('Here should be one:',t.get_y())

print ('Here should be five:',t.x)
print('Here should be five:',t.get_x())
t.x = 1
print('Here should be one:', t.x)
print('Here should be one:',t.get_x())
