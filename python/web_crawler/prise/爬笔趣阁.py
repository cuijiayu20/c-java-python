# -*- coding = utf-8 -*-
# @time : 2022/8/13 18:43
# @Author : 特让他也让
# @File : 测试用.py
# @Software : PyCharm

# 一剑独尊全章
from concurrent.futures import ThreadPoolExecutor
import threading
import requests
from lxml import etree
import re
import os

lock_object = threading.RLock()

header = {
    'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/103.0.0.0 '
                  'Safari/537.36 ',
    'Cookie': 'ckAC=1; width=85%25'
}

# 第一个函数：拿到链接
url_list = []
name_list = []


def get_url():  # 获取每章的链接

    lock_object.acquire()  # 加锁

    url = 'http://www.biqu5200.net/106_106690/'
    response = requests.get(url, headers=header)
    response.encoding = 'gbk'
    response_xpath = etree.HTML(response.text)
    book_url = response_xpath.xpath('//*[@id="list"]/dl/dd/a/@href')  # 所有的章节链接
    name = response_xpath.xpath('//*[@id="info"]/h1/text()')  # 书名
    # 创建文件夹
    for i in name:
        name_list.append(str(i))
        path = r'D:\{}'.format(str(i))
        a = os.path.exists(path)
        if not a:
            os.mkdir(r'D:\{}'.format(str(i)))
        else:
            break
    for i in book_url:
        url_list.append(i)  # 存入列表中，以后只用
    # print(url_list)  # 验证是否写入列表
    # print(url_list[0])
    # print(len(url_list))  # 取数，找下标  一共3053个元素，一个元素是一个链接
    lock_object.release()  # 解锁
    return url_list


get_url()  # 先运行函数 才能返回url_list


# print(url_list[0])  # 验证是否返回
# print(name_list)
# 这时链接就在列表里，可通过下标方式取出，列表共有3053个元素（链接）

# 第二个函数：解析数据
def get_main(urls):
    lock_object.acquire()

    response_1 = requests.get(urls, headers=header)
    response_1.encoding = 'gbk'
    # print(response_1.text)

    obj1 = re.compile(r"<title>(?P<book_name>.*?)</title>", re.S)
    obj2 = re.compile(r"<p>(?P<book_text>.*?)</p>", re.S)
    result1 = obj1.finditer(response_1.text)
    result2 = obj2.finditer(response_1.text)
    for a in result1:
        book_name = a.group('book_name')  # 章节名
        # print(book_name)
        for b in result2:
            book_text = b.group('book_text')  # 章节内容
            book_text = str(book_text)
            # print(book_text, type(book_text))
            with open(f'D:\\{name_list[0]}\\{book_name}.txt', 'a', encoding='utf-8') as file:
                file.write(book_text)  # 保存内容
    lock_object.release()


# 多线程
pool = ThreadPoolExecutor(100)
d = 0
while d < 3053:
    pool.submit(get_main, url_list[d])
    d += 1