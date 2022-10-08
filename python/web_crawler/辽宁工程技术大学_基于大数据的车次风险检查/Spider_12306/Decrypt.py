import re

print("正在解码")


def decrypt(string):
    string = ''.join(string)
    reg = re.compile(
        '.*?\|预订\|.*?\|(.*?)\|.*?\|.*?\|(.*?)\|(.*?)\|(.*?)\|(.*?)\|(.*?)\|.*?\|.*?\|.*?\|.*?\|.*?\|.*?\|.*?\|.*?\|.*?\|.*?\|(.*?)\|(.*?)\|(.*?)\|(.*?)\|(.*?)\|(.*?)\|(.*?)\|(.*?)\|(.*?)\|(.*?)\|(.*?)\|(.*?)\|.*?\|.*?\|.*?\|.*')
    result = re.findall(reg, string)[0]

    return result


def decrypt_1(string):
    zhong_zhuan_zhan = re.findall('"middle_station_name":"(.*?)"', string)
    from_station_name = re.findall('"from_station_name":"(.*?)"', string)
    arrive_time = re.findall('"arrive_time":"(.*?)"', string)
    all_lishi = re.findall('"all_lishi":"(.*?)"', string)
    station_train_code = re.findall('"station_train_code":"(.*?)"', string)
    start_time = re.findall('"start_time":"(.*?)"', string)

    list_all = []
    listx = []
    listy = []

    for i in range(0, len(station_train_code), 2):
        listx.append(station_train_code[i])
    for j in range(1, len(station_train_code), 2):
        listy.append(station_train_code[j])
    if (len(station_train_code) // 2) == 0:
        list_all = [[x, y] for x, y in zip(listx, listy)]
    elif len(listx) > len(listy):
        listy.append("null")
        list_all = [[x, y] for x, y in zip(listx, listy)]
    elif len(listx) < len(listy):
        listx.append("null")
        list_all = [[x, y] for x, y in zip(listx, listy)]
    else:
        list_all = [[x, y] for x, y in zip(listx, listy)]

    list_1 = []
    for i_1, i_2, i_3, i_4, i_5 in zip(zhong_zhuan_zhan, from_station_name, arrive_time, all_lishi, start_time):
        list_1.append([i_1, i_2, i_3, i_4, i_5])

    print('解码完毕')
    return list_all, list_1
