import os
import threading
from time import sleep

import requests
import re
from lxml import etree
from concurrent.futures import ThreadPoolExecutor

print(r'保存路径为：D:\others\Python\python爬虫项目\Animex动漫社\图片\'')
sleep(3)

lock_project = threading.RLock()

headers = {
    "User-Agent": "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.26 Safari/537.36 Core/1.63.5383.400 QQBrowser/10.0.1313.400"
}


def get_url():
    lock_project.acquire()
    print('正在获取页面链接')
    global url_list
    url_list = []
    for page in range(1, 56):
        url = f'http://www.animetox.com/category/pic/pixiv/page/{page}'
        response = requests.get(url, headers=headers)

        href = re.finditer("""<a class="post-list-link" rel="bookmark" href="(.*?)" itemprop='mainEntityOfPage'>""",
                           response.text)
        for i in href:
            url = i.group(1)
            url_list.append(url)
    print('页面链接获取完毕')
    print('-' * 60)
    lock_project.release()


def get_photos_url():
    lock_project.acquire()
    print('正在获取图片链接')
    a = 1
    for url in url_list:
        response = requests.get(url, headers=headers)
        # print(response.text)
        response_tree = etree.HTML(response.text)
        title = response_tree.xpath('//*[@id="main-contents"]/section/article/div/div/p[@style="text-align: center;"][1]/text()')
        src = response_tree.xpath('//*[@id="main-contents"]//p/img[@data-lazy-src]/@data-lazy-src')
        for i in title:
            print(f'正在下载{i}中的图片')
            for i_1 in src:
                response = requests.get(i_1, headers=headers)
                with open(rf'D:\others\Python\python爬虫项目\Animex动漫社\图片\{a}.jpg', 'wb') as fin:
                    fin.write(response.content)
                    print(f'第{a}张下载完毕')
                    a += 1
            print(f'{title}全部下载完毕')
            print('-' * 60)

    lock_project.release()


pool = ThreadPoolExecutor(600)
pool.submit(get_url)
pool.submit(get_photos_url)
