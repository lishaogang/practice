import asyncio
import aiomysql

@asyncio.coroutine
def test3(loop):
    pool = yield from aiomysql.create_pool(user = 'root', password = '948023',
                                port = 3306, db = 'awesome',loop = loop)
    with (yield from pool) as cnn:
        cur = yield from cnn.cursor()
        print('enter')
        yield from cur.execute('select * from users')
        r = cur.fetchall()
        print(r)
        yield from cur.close()
    print('exit')
    print(pool._closed)
    pool.close()
    yield from pool.wait_closed()
    

async def test1(loop):
    async with aiomysql.create_pool(user = 'root', password = '948023',
                                port = 3306, db = 'awesome',loop = loop) as pool:
        async with pool.acquire() as cnn:
            print('enter')
            async with cnn.cursor() as cur:
                await cur.execute('select * from users')
                r = cur.fetchall()
                print(r)
            print('exit')
    print('-' * 10)
    print(pool._closed)
    await pool.wait_closed()
    
loop = asyncio.get_event_loop()
tasks = [asyncio.async(test3(loop))]
loop.run_until_complete(asyncio.wait(tasks))
