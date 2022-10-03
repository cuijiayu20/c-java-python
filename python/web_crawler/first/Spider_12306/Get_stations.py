# 该文件用于获取所有的车站信息

import re
import requests

print("获取车次页面")

url = 'https://kyfw.12306.cn/otn/resources/js/framework/station_name.js?station_version=1.9240'
response = requests.get(url)
stations = re.findall(u'([\u4e00-\u9fa5]+)\|([A-Z]+)', response.text)
stations = dict(stations)

# print(stations)

print("页面车次获取完毕")

print("-" * 60)
