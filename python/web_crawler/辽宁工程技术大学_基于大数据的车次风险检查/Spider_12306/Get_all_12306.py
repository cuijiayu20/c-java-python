from time import sleep
from Spider_12306.Decrypt import decrypt_1
from prettytable import PrettyTable
from Spider_12306.Get_tickets import get_tickets
from Spider_12306.Get_tickets import header
from Spider_12306.Get_tickets import header_1
from Spider_12306.Get_stations import stations
from Spider_12306.Decrypt import decrypt
from Spider_12306.Get_info import get_info
import re
from Spider_12306.Get_path import get_path

dic_che_ci_number = {}


def over_1():  # 获取车站次位信息

    print("正在获取车站信息")

    sleep(0.2)

    pt = PrettyTable()  # 单程
    tb = PrettyTable()  # 中转站

    sleep(0.2)

    pt.field_names = header
    tb.field_names = header_1

    [fw, tw, st] = get_info()
    sleep(0.2)
    train_list, response_1 = get_tickets(fw, tw, st)

    sleep(0.2)
    new_dict = {v: k for k, v in stations.items()}
    sleep(0.2)
    number = 0

    # number : 单程
    for item in train_list:
        sleep(0.2)
        result = list(decrypt(item))
        result[1] = new_dict[result[1]]
        result[2] = new_dict[result[2]]
        results = [number, result[0], result[1], result[2], result[3], result[4], result[5], result[-1], result[-2],
                   result[-3],
                   result[-12], result[-10], result[-6], result[-5], result[-8], result[-4], result[-7]]
        dic_che_ci_number[f"{number}"] = result[0]
        number += 1
        pt.add_row(results)

    list_all, list_1 = decrypt_1(response_1.text)

    number_path_1 = number
    number_path_2 = number_path_1 + 1
    # number_path_1 : 中转前
    # number_path_2 : 中转后
    list_2 = []
    for i in list_all:
        list_2.append(i)

    list_3 = []
    for i, ii in zip(list_1, list_2):
        list_3.append([i, ii, ])

    for i in list_3:
        i = str(i)
        for ii in re.findall(
                "\[\[\'(.*?)\'\, \'(.*?)\'\, \'(.*?)\'\, \'(.*?)\'\, \'(.*?)\'\]\, \[\'(.*?)\'\, \'(.*?)\'\]\]", i):
            tb.add_row([number_path_1, ii[5], number_path_2, ii[6], ii[1], ii[0], ii[2], ii[3], ii[4]])
            dic_che_ci_number[f"{number_path_1}"] = ii[5]
            dic_che_ci_number[f"{number_path_2}"] = ii[6]
            number_path_1 += 2
            number_path_2 = number_path_1 + 1
    print("车站信息获取完毕")

    stations_list = []
    for i in dic_che_ci_number.values():
        station_name = get_path(i)
        stations_list.append(station_name)

    # print(dic_che_ci_number)
    # print(stations_list)
    return pt, tb, dic_che_ci_number, stations_list

# over_1()
