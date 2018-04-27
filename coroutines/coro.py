import logging;logging.basicConfig(level = logging.INFO)
import asyncio
def broadcast(targets):
    sig = True
    while sig:
        item = (yield)
        for target in targets:
            try:
                target.send(item)
            except StopIteration:
                pass
            finally:
                pass

def drive(broadcast):
    while True:
        try:
            broadcast.send(None)
        except StopIteration:
            logging.info("Unexcepeted Wrong")
        finally:
            pass



def coroutine(func):
    def start(*args, **kws):
        cr = func(*args, **kws)
        logging.info("init"+str(cr))
        
        cr.send(None)
        return cr
    return start

@asyncio.coroutine
def hello(name):
    for i in range(1,3):
        #msg = (yield i)
        print("hello",name,i)

if __name__ == "__main__":
    drive(broadcast([hello("tom"),hello("sam")]))
    
