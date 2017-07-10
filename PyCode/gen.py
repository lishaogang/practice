def squares(cursor = 1):
    res = None
    while True:
        if res:
            res = yield res ** 2
            continue
        res = yield cursor ** 2
        cursor += 1

sq = squares()
print(next(sq))
print('first')
print(next(sq))
print('second')
print(next(sq))
print('third')
print(sq.send(7))
print(next(sq))
print(next(sq))
