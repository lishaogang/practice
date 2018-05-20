import logging
logging.basicConfig(level = logging.INFO,format = '%(asctime)s - %(name)s - %(levelname)s - %(message)s')
logger = logging.getLogger(__name__)
import copy
from file import *
import file
class B():
	def __init__(self,name):
		self.name = name
	def __repr__(self):
		return self.name
		
if __name__ == '__main__':
	A = 2
	print(file.A)
	