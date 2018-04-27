def producer():
    r = 1
    while r < 10:
        yield r
        r += 1

def processor():
    try:
        yield producer()
    finally:
        print("processed")

def foo():
    yield 9


import requests
from bs4 import BeautifulSoup
import re
def fun():
    total = 0
    r = requests.get('https://book.douban.com/subject/2160556')
    soup = BeautifulSoup(r.text, 'lxml')
    pattern = soup.find_all('p', 'conmment-content')
    for item in pattern:
        print(item.string)

    pattern_s = re.compile('<span class="user-stars allstar(.*?) ratin')
    p = re.find_all(pattern_s,r.text)
    for star in p:
        total += int(star)

    print(total)


if __name__ == "__main__":
    fun()
