import asyncio
import threading
import time

async def Hello():
    print('Hello,World.(%s)'%(threading.currentThread()))
    await asyncio.sleep(2)
    print('Hello again.(%s)'%(threading.currentThread()))

loop = asyncio.get_event_loop()
tasks = [Hello() for i in range(3)]
loop.run_until_complete(asyncio.wait(tasks))
loop.close()




@asyncio.coroutine
def wget(host):
    print('wget %s......' % host)
    connect = asyncio.open_connection(host, 80)
    reader, writer = yield from connect
    
    header = 'GET / HTTP/1.0\r\nHost:%s\r\n\r\n' % host
    writer.write(header.encode('utf-8'))
    yield from writer.drain()
    while True:
        line = yield from reader.readline()
        print('yield here')
        if line == b'\r\n':
            break
        print('-')#('%s header > %s' % (host, line.decode('utf-8').rstrip()))
    writer.close()

tasks = [wget(host) for host in ['www.baidu.com','www.sina.com']]