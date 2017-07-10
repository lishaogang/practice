class out(object):
    class inside(object):
        def __init__(self):
            self.data = []

        def show(self):
            print(self.data)

    def __init__(self):
        pass

    def add(self,n):
        if not hasattr(self.inside,'data'):
            self.inside.data = []
        self.inside.data = self.inside.data + [n]


o = out()
o.add(1)
o.add(2)
i1 = o.inside()
i1.show()

i2 = out.inside()
i2.show()
