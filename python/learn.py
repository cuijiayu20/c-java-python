import os
import sys

a = input('请输入')
dic = {}
for i in a:
    print(a.count(i))
    dic[i] = a.count(i)
b = max(dic.values())
for key in dic.items():
    print(key)