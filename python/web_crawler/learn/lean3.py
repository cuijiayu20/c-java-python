import requests 

url="https://fanyi.baidu.com/sug"

s=input("请输入你要翻译的内容：")

dat={
    "kw": s
}

#发送POST请求，发送的数据必须放在字典中，通过data参数传递

res=requests.post(url,data=dat)

print(res.json())#服务器返回内容处理为json（）=>字典