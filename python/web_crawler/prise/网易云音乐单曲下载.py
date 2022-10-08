import json

import requests
import re

id_ = input('请输入音乐id：')

url_1 = f'https://music.163.com/song/media/outer/url?id={id_}.mp3'
url_2 = f'https://music.163.com/song?id={id_}'

headers = {
    'user-agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/105.0.0.0 Safari/537.36',
    'origin': 'https://music.163.com/',
}


response_1 = requests.post(url_1, headers=headers)
response_2 = requests.get(url_2, headers=headers)
title_list = re.findall('<title>(.*?)</title>', response_2.text)

if '开通VIP畅听' not in response_2.text:

    if '-' in title_list[0]:
        title_list_1 = title_list[0].split('-')
        title = title_list_1[0]
    else:
        title = title_list[0]
        print(f'{title}正在下载')

    with open(fr'D:\c++-java-python\python\music\{id_}.mp3', 'wb') as fin:
        fin.write(response_1.content)
        print('下载完成')
else:
    print('该程序无法执行')



