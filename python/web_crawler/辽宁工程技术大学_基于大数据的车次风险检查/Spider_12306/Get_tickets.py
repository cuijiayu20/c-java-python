from Spider_12306.Get_cookie import cookiestr
from Spider_12306.Get_stations import stations
import time
import json
import requests
import re

header = ["编号", "车次", "出发站", "到达站", "出发时间", "到达时间", "历时", "商务座", "一等座", "二等座", "高级软卧",
          "软卧",
          "动卧", "硬卧", "软座", "硬座", "无座"]

header_1 = ["前编号", "前一段车次", "后编号", "后一段车次", "出发站点", "中转站", "发车时间", "到达终点时间", "总历时"]


def get_tickets(froms, tos, date):  # 出发地，目的地，时间

    print("正在获取车站信息")

    start_station = stations[f'{froms}']
    end_station = stations[f'{tos}']

    headers = {
        'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/105.0.0.0 Safari/537.36 Edg/105.0.1343.50',
        'cookie': cookiestr
    }
    # driver.quit()

    # 单程
    url = f'https://kyfw.12306.cn/otn/leftTicket/queryZ?leftTicketDTO.train_date={date}&leftTicketDTO.from_station={start_station}&leftTicketDTO.to_station={end_station}&purpose_codes=ADULT'

    # 中转换乘
    url_1 = f"https://kyfw.12306.cn/lcquery/query?train_date={date}&from_station_telecode={start_station}&to_station_telecode={end_station}&middle_station=&result_index=0&can_query=Y&isShowWZ=N&purpose_codes=00&lc_ciphertext=uiIFnLsJxRaWghojbBAiSBCc/fxWi8CcAMpldznTfWY="

    response = requests.get(url, headers=headers)
    response_1 = requests.get(url_1, headers=headers)

    response.encoding = 'utf-8'

    response_1.encoding = "utf-8"

    response = json.loads(response.text)
    result = response['data']['result']

    new_list = []

    for item in result:
        if '列车停运' not in item:
            new_list.append(item)
        else:
            pass

    print("车站信息获取完毕")

    return new_list, response_1
