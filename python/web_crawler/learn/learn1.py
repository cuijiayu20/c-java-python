from asyncore import read
from urllib.request import urlopen

url="http://www.200580.com/2021/2021%E7%9F%A5%E5%88%B0%E7%AD%94%E6%A1%88-%E5%85%AC%E5%85%B1%E7%AE%A1%E7%90%86%E7%A4%BC%E4%BB%AA-%E6%9C%80%E6%96%B0%E7%9F%A5%E5%88%B0%E6%99%BA%E6%85%A7%E6%A0%91%E6%BB%A1%E5%88%86%E7%AB%A0%E8%8A%82/#toc-2"
url2="http://www.baidu.com"
resp=urlopen(url2)#打开网址

#print(resp.read())#.read读取

#print(resp.read().decode("utf-8"))#解码，将字节转化为字符串

with open("zhs.html",encoding="utf-8",mode="w") as f:
    f.write(resp.text)#将网页写入文件
print("over")#你好
