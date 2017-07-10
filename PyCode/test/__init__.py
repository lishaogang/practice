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
