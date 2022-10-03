from time import sleep

from prettytable import PrettyTable
from Spider_12306.Get_tickets import get_tickets
from Spider_12306.Get_tickets import header
from Spider_12306.Get_stations import stations
from Spider_12306.Decrypt import decrypt
from Spider_12306.Get_info import get_info

che_ci = []


def over_1():  # 获取车站次位信息

    print("正在获取车站信息")

    sleep(0.2)
    pt = PrettyTable()
    sleep(0.2)
    pt.field_names = header
    [fw, tw, st] = get_info()
    sleep(0.2)
    train_list = get_tickets(fw, tw, st)

    sleep(0.2)
    new_dict = {v: k for k, v in stations.items()}
    sleep(0.2)
    for item in train_list:
        sleep(0.2)
        result = list(decrypt(item))
        che_ci.append(result[0])
        result[1] = new_dict[result[1]]
        result[2] = new_dict[result[2]]
        results = [result[0], result[1], result[2], result[3], result[4], result[5], result[-1], result[-2], result[-3],
                   result[-12], result[-10], result[-6], result[-5], result[-8], result[-4], result[-7]]
        pt.add_row(results)

    print("车站信息获取完毕")

    return pt

