from subprocess import list2cmdline
from typing_extensions import final
import requests
import re
#lst =re.findall(r"玩儿.*?游戏","玩儿吃鸡游戏，晚上一起上游戏，干嘛呢？打游戏")
#print(lst)
list2=re.finditer(r"玩儿.*?游戏","玩儿吃鸡游戏，晚上一起上游戏，干嘛呢？打游戏")
for i in list2:
    print(i.group())
s= re.search(r"d+","我的电话号码是：10086，我女朋友电话号码是：10010")
print(s.group())

# s= re.match(r"d+","我的电话号码是：10086，我女朋友电话号码是：10010")
# print(s.group())
obj=re.compile(r"d+")