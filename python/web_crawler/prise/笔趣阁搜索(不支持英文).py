import threading
from time import sleep

import requests
from lxml import etree
import re
import os

headers = {
    'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/105.0.0.0 Safari/537.36'
}

search = input('请输入搜索内容：')
print('-' * 60)

url = f'http://www.biqu5200.net/modules/article/search.php?searchkey={search}'
response = requests.get(url, headers=headers)
response.encoding = 'gbk'
# print(response.text)

href_and_name = re.findall('<td class="odd"><a href="(.*?)">(.*?)</a></td>', response.text)
author_list = re.findall('<td class="odd">([\u4e00-\u9fa5].*?)</td>', response.text)
page_new_list = re.findall('target="_blank">(.*?)</a></td>', response.text)
if_over_list = re.findall('<td class="even" align="center">(.*?)</td>', response.text)

label = 0

href_list = []

for (href, name), author, page_new, if_over in zip(href_and_name, author_list, page_new_list, if_over_list):
    print(f'标签编号：{label}')
    print(
        f'书名：{name}' + '\n' + f'作者：{author}' + '\n' + f'链接：{href}' + '\n' + f'最新章：{page_new}' + '\n' + f'连载状态：{if_over}')
    href_list.append(href)
    label += 1
    print('-' * 60)


# ----------------------------------函数部分---------------------------------------------------------------

def get_all_url():
    response_1 = requests.get(href_list[int(number)], headers=headers)
    response_1.encoding = 'gbk'

    path_dir_ = input(
        '设置保存路径' + '\n' + '会在路径下创建以书名为名称的文件夹进行保存' + '\n' + '如果发现程序无法保存请修改保存路径' + '\n' + '注意，路径最后要带斜杠，例如 D:\新建文件夹\\' + '\n' + '请输入地址吧(不设置的话请回车，默认为D盘)：')

    page_href_and_title = re.findall('<dd><a href="(.*?)">(.*?)</a></dd>', response_1.text)

    count = 1

    for href_, title in page_href_and_title:

        if path_dir_ == '':
            path_dir = 'D:\\'
        elif path_dir_ != 'D:\\':
            path_dir = path_dir_
        elif path_dir_ == 'D:\\':
            path_dir = 'D:\\'

        a = os.path.exists(path_dir + f'{name}' + '\\')
        if not a:
            os.mkdir(path_dir + f'{name}' + '\\')
        else:
            pass

        response_2 = requests.get(href_, headers=headers)
        response_2.encoding = 'gbk'

        text_list = re.findall('<p>(.*?)</p>', response_2.text)

        print(f'{title}正在下载')
        b = os.path.exists(f'{count}' + path_dir + f'{name}' + '\\' + f'{title}.txt')
        if not b:
            for text in text_list:
                with open(f'{count}' + path_dir + f'{name}' + '\\' + f'{title}.txt', 'a') as fin:
                    fin.write(text)
                    fin.write('\n')
                    count += 1
        else:
            pass

        print(f'{title}下载完成')
        print('-' * 60)
        sleep(1)


# ---------------------------------函数部分------------------------------------------------------------------------------------------

yes_or_no = input('是否有想下载的文章?(yes或者no):')

c = 'no'
while c != 'yes':
    if yes_or_no == 'yes':
        number = input('输入想要下载的文章的标签编号：')
        print('-' * 60)
        get_all_url()
    elif yes_or_no != 'yes':
        c = input('是否退出程序？(yes或者no)：')
        yes_or_no = input('是否有想下载的文章?(yes或者no):')
