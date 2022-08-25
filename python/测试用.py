# -*- coding = utf-8 -*-
# @time : 2022/8/13 18:43
# @Author : 特让他也让
# @File : 测试用.py
# @Software : PyCharm

# 一剑独尊全章
from concurrent.futures import ThreadPoolExecutor

import requests
from lxml import etree

header = {
    'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/103.0.0.0 Safari/537.36'
}


# 第一个函数：拿到链接
def get_url():
    url = 'http://www.biqu5200.net/106_106690/'
    response = requests.get(url, headers=header)
    response.encoding = 'gbk'
    response_xpath = etree.HTML(response.text)
    book_url = response_xpath.xpath('//*[@id="list"]/dl/dd/a/@href')
    for link in book_url:
        return link


# 第二个函数：解析链接得到内容,写入文件
def get_main():
    url=get_url()
    response = requests.get(url, headers=header)
    response.encoding = 'gbk'
    response_xpath = etree.HTML(response.text)
    name = response_xpath.xpath('//*[@id="wrapper"]/div[4]/div/div[1]/a[3]/text()')
    book_name = response_xpath.xpath('//*[@id="wrapper"]/div[4]/div/div[2]/h1/text()')
    book_txt = response_xpath.xpath('//*[@id="content"]/p/text()')
    with open(f'D:\\c++-java-python\\python\\1.txt','a', encoding='utf-8') as fin:
            for i in book_txt:
                fin.write(str(i))
    # file1=open('./%s.txt' %(book_name),'a')
    # try:
    #     for i in book_txt:
    #              file1.write(str(i))
    # finally:
    #  file1.close( )



# 进程池

pool_2 = ThreadPoolExecutor(100)
pool_2.submit(get_main())

print('all over')
