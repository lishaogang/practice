import socket

class Conn(object):
	def __init__(self):
		self.s = None
		
	def conn(self, url, req):
		self.s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

		self.s.connect((url,80))

		self.s.send(req)

	def close(self):
		self.s.close()
	
	def recv(self):
		return self.s.recv(1024)

baidu_url = 'www.baidu.com'
baidu_req = b'GET / HTTP/1.1\r\nHost:www.baidu.com\r\nConnection: keep-alive\r\n\r\n'

sina_url = 'www.sina.com.cn'
sina_req = b'GET / HTTP/1.1\r\nHost: www.sina.com.cn\r\nConnection: close\r\n\r\n'

baidu = Conn()
baidu.conn(baidu_url, baidu_req)
sina = Conn()
sina.conn(sina_url, sina_req)
def fun(s):
	buffer = []
	while True:
		d = s.recv()
		if d:
			
			buffer.append(d)
		else:
			break
	s.close()
	return buffer
	
header, html = b''.join(fun(baidu)).split(b'\r\n\r\n',1)
print(header.decode('utf-8'))
# print('sina\n------------\n--------------\n-----------------\n')
# header, html = b''.join(fun(sina)).split(b'\r\n\r\n',1)
# print(header.decode('utf-8'))
# with open('sina.html','wb') as f:
	# f.write(html)