from follow import coroutine, printer
import xml.sax


def foo():
	print("foo")

class MyHandler(xml.sax.ContentHandler):
	def __init__(self, target):
		self.target = target
	def startElement(self, name, attrs):
		self.target.send(("start", (name, attrs._attrs)))
	def endElement(self, name):
		self.target.send(("end", name))
	def characters(self, text):
		if(text == '\t' or text == '\n'):
			return
		self.target.send(("text", text))

@coroutine
def pprinter():
	while True:
		event, value = (yield)
		print(event,value)

@coroutine
def buses_to_dicts(target):
	while True:
		event, value = (yield)
		if event is None:
			print("__init__")
		if event == "start" and value[0] == "bus":
			busdics = {}
			fragments = []
		while True:
			if event == "start":
				fragments = []
			elif event == "text":
				fragments.append(value)
			elif event == "end":
				if value != "bus":
					busdics[value] = "".join(fragments)
				else:
					target.send(busdics)
			break

@coroutine
def filter_on_field(fieldname, value, target):
	while True:
		d = (yield)
		if d.get(fieldname) == value:
			target.send(d)



if __name__ == "__main__":
	p = printer()
	f = filter_on_field("direction","North Bound",p)
	c = buses_to_dicts(f)
	xml.sax.parse("./buses.xml", MyHandler(c))
