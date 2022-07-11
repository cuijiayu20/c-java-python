from dataclasses import dataclass
import requests

query = input("输入查询内容")
# url=f"https://www.baidu.com/s?wd={query}"


url = 'http://www.200580.com/wp-content/plugins/wp-recentcomments/js/wp-recentcomments-jquery.dev.js?ver=2.2.8'

dic = {
    "User-Agent":"Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/100.0.4896.60 Safari/537.36"
}

resp = requests.get(url,headers=dic)
print(resp)
print(resp.text)  #拿到页面源代码

# http://www.200580.com/2021/2021知到答案-公共管理礼仪-最新知到智慧树满分章节/#toc-2