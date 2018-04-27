import logging
logging.basicConfig(level = logging.INFO,
				format = '%(asctime)s %(filename)s [line:%(lineno)d] %(levelname)s %(message)s')	
logging.debug('this is a debug message')
logging.info('this is a info message')
logging.warning('this is a warning message')
