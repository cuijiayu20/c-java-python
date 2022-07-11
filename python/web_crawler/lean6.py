import requests
import re

url="https://movie.douban.com/top250"
headers={
    "User-Agent":"Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/100.0.4896.60 Safari/537.36"
}

resp = requests.get(url,headers=headers)


page_content=resp.text
obj = re.compile(r'<li>.*? <div class="item">.*?<span class="title">(?P<name>.*?)'
                r'</span>.*?<p class="">.*?<br>(?P<year>.*?)&nbsp',re.S)
result=obj.finditer(page_content)
for it in result:
    print(it.group("name"))
    print(it.group("year").strip())
resp.close()
