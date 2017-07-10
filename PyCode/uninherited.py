class Meta(type):
    def __new__(cls, name, bases, attr):
        if attr.pop('abstract',False):
            return super(Meta, cls).__new__(cls, name, bases, attr)
        new_class = super(Meta, cls).__new__(cls, name, bases, attr)
        if hasattr(new_class, '_run'):
            return new_class
        raise TypeError('Subclass must define _run method')

class Task(metaclass = Meta):
    abstract = True
    def __init__(self):
        pass
    def run(self):
        print('_run start')
        self._run()
        print('_run end')

class someTask(Task):
    def _run():
        pass
    pass

t = Task()
print(t.abstract)
t = someTask();
print(t.abstract)
