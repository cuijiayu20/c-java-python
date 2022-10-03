import requests
import json

url = "https://kyfw.12306.cn/lcquery/query?train_date=2022-09-28&from_station_telecode=PLT&to_station_telecode=HPD&middle_station=&result_index=0&can_query=Y&isShowWZ=N&purpose_codes=00&lc_ciphertext=uiIFnLsJxRaWghojbBAiSBCc/fxWi8CcAMpldznTfWY="
response = requests.get(url)
response.encoding = "utf-8"
response = json.loads(response.text)
response = response["data"]["middleList"]

print("| 车次 | 总历时 | 到达目的地日期 | 到达目的地时间 | 出发地点 | 中间站 | ")
for i in response:
    print(f"总历时：{i['all_lishi']}")
    print(f"到达目的地日期：{i['arrive_date']}")
    print(f"到达目的地时间{i['arrive_time']}")
    print(f"出发地点：{i['from_station_name']}")
    print(f"中间站：{i['middle_station_name']}")
    for ii in i['fullList']:
        print(f"车次：{ii['station_train_code']}")
        a = ii['station_train_code']
        print(f"到达中间站的时间：{ii['arrive_time']}")
        b = ii['arrive_time']
        print('-' * 60)


