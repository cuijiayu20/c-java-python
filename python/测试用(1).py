import requests
import re

url = 'https://www.hacg.mom/wp/71162.html'
proxie={'http': 'http://127.0.0.1:7890'}
header = {'User-Agent':'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/105.0.0.0 Safari/537.36 Edg/105.0.1343.27'}

response = requests.get(url, proxies=proxie,headers=header)

response.encoding = 'utf-8'
# for i in response.text:
#     print(i)

if 'http://v2.uploadbt.com/\r\n' in response.text:
    print(1)
    bt = re.findall('提取码 (.*)', response.text)

# print(bt)
