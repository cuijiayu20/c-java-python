import requests 

url="https://ebt143.doc88.com/getebt-0rUXzq3U1LnQ0rUV0rUS0LM!0LHX1KUS0LM!0LHX1LPX1LHS0N8VtWB2rghVEX==.ebt"



#发送POST请求，发送的数据必须放在字典中，通过data参数传递


header={ 
    "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/101.0.4951.64 Safari/537.36 Edg/101.0.1210.53"
}

res=requests.get(url,headers=header)
with open("a.text",mode="w",encoding="gbk") as f:
    f.write(res.text)
print(res.text)#服务器返回内容处理为json（）=>字典