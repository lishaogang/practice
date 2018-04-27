import socket
import threading

def _main():
	s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
	s.bind(('127.0.0.1',9999))
	s.listen(5)
	print('waiting for connection')
	while True:
		sock, addr = s.accept()
		t = threading.Thread(target = tcplink, args = (sock, addr))
		t.start()

def tcplink(sock, addr):
	print('Accept new a connection from %s:%s' % addr)
	sock.send(b'Welcome!')
	while True:
		data = sock.recv(1024)
		if not data or data.decode('utf-8') == 'q':
			sock.send(b'quit!')
			break
		sock.send(('Hello, %s' % data.decode('utf-8')).encode('utf-8'))
	sock.close()
	print('Connection from %s:%s closed' % addr)
		
if __name__ == '__main__':
	_main()