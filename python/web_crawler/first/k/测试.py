import requests
import json
import re
from prettytable import PrettyTable

url = "https://kyfw.12306.cn/lcquery/query?train_date=2022-09-29&from_station_telecode=PLT&to_station_telecode=HPD&middle_station=&result_index=0&can_query=Y&isShowWZ=N&purpose_codes=00&lc_ciphertext=uiIFnLsJxRaWghojbBAiSBCc/fxWi8CcAMpldznTfWY="
response = requests.get(url)
response.encoding = "utf-8"
# response = json.loads(response.text)
# response = response["data"]["middleList"]
# print(response)

# print(response.text)
table = PrettyTable(["前一段车次","后一段车次" "出发站点", "中转站", "到达终点时间", "总历时"])
zhong_zhuan_zhan = re.findall('"middle_station_name":"(.*?)"', response.text)
from_station_name = re.findall('"from_station_name":"(.*?)"', response.text)
arrive_time = re.findall('"arrive_time":"(.*?)"', response.text)
all_lishi = re.findall('"all_lishi":"(.*?)"', response.text)
station_train_code = re.findall('"station_train_code":"(.*?)"', response.text)

list_all=[]
listx=[]
listy=[]


for i in range(0,len(station_train_code),2):
        listx.append(station_train_code[i])
for j in range(1,len(station_train_code),2):
        listy.append(station_train_code[j])
if (len(station_train_code) // 2)==0:
    list_all=[[x,y] for x,y in zip(listx, listy)]
elif len(listx)>len(listy):
    listy.append("null")
    list_all=[[x,y] for x,y in zip(listx, listy)]
elif len(listx)<len(listy):
    listx.append("null")
    list_all=[[x,y] for x,y in zip(listx, listy)]
else: list_all=[[x,y] for x,y in zip(listx, listy)]



print(list_all)
        





# count = 0
# while count <= 2:
#     count= 0
#     for i, ii, i_1, i_2 in zip(zhong_zhuan_zhan, from_station_name, arrive_time, all_lishi):
#         for i_3, i_4 in station_train_code:
#             # print("  ", ii, "  ", i)
#             table.add_row([i_3, i_4, ii, i, i_1, i_2]) #添加行
#             count += 1
# print(table)

# station_train_code = re.findall('"station_train_code":"(.*?)"', response.text)
# list = []
# count = 0
# while count <= 2:
#     list.append([station_train_code[count], station_train_code[count+1]])
# print("-" * 60)

# count = 0
# count_list = []
# for i in range(1, len(station_train_code), 2):
#     count_list.append(i)
# # 1 3 5
# # 1 3 5 6
# for i in count_list:
#     print([station_train_code[i-1], station_train_code[i]]) 

"""
G9513 金谱  盘锦 
z194 盘锦 
[G9153, Z194], 
"""