import requests
import re
from urllib import parse


def get_address(self):
    url = f"https://map.baidu.com/?newmap=1&reqflag=pcmap&biz=1&from=webmap&da_par=baidu&pcevaname=pc4.1&qt=s&da_src=shareurl&wd={self}站&c=176&src=0&pn=0&sug=0&l=17&b=(12534995.180650333,4523612.393958214;12537748.663361853,4525062.277591786)&from=webmap&biz_forward=%7B%22scaler%22:2,%22styles%22:%22pl%22%7D&device_ratio=2&auth=6S9cMHSHGeMbHzOVKdD20JwZWVE6%3DWYMuxLLENTNVExtAMU7ICOFFvyuwyS8v7uvkGcuVtvvhguVtvyheuVtvCMGuVtvCQMuVtvIPcuVtvYvjuVtvZgMuVtv%40vcuVtvc3CuVtvcPPuVtveGvuVtveh3uVtvh3CuVtvhgMuxVVtcvY1SGpuxRtKHZz3MJ2dd9dv7uegvcguxLLENTNVEx&seckey=-1%2C-1&tn=B_NORMAL_MAP&nn=0&u_loc=13446611,4929928&ie=utf-8&t=1664797053039&newfrom=zhuzhan_webmap"
    response = requests.get(url)
    s = response.text.encode('utf8')
    k = s.decode('unicode_escape')

    # print(_str_(k))

    obj = re.compile('"address_norm":"(.*?)"')
    result = obj.finditer(k)
    address = []
    for i in result:
        # print(i.group(1))
        result_1 = re.findall("[\u4e00-\u9fa5]", i.group(1))
        result_1 = "".join(result_1)
        address.append(result_1)
        break
    return address

