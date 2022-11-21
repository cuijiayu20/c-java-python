from Spider_12306.Get_all_12306 import over_1
from Spider_Gaud_map.get_station_address import get_address
from concurrent.futures import ThreadPoolExecutor
from prettytable import PrettyTable
from Spider_danger_place.get_list import risk_list

pt, tb, dic_che_ci_number, stations_list = over_1()














if __name__ == '__main__':
    def main():
        _or_ = "y"
        while _or_ == "y":
            print("正在输出获取结果")
            print("-" * 60)
            print("\n")
            print("                     单程信息")
            print(pt)
            print("\n")
            print("                     中转信息")
            print(tb)
            print("\n")
            print("获取结果输出完毕")
            print("-" * 60)
            table = PrettyTable()
            table.field_names = ["该车次经过的车站", "经过车站的地址"]
            again = "y"
            while again == "y":
                num = input("请输入想要查询的车次编号:")
                # print(dic_che_ci_number)
                if dic_che_ci_number.values() is not None:
                    print("正在获取车次路程信息")
                    
                    # i = dic_che_ci_number[num]
                    # for ii in stations_list:
                    #     # print(dic_che_ci_number)
                    table.title = dic_che_ci_number[num]
                    for ii in stations_list[int(num)]:
                        a = get_address(ii)
                        if a != []:
                            
                            b = a[0].find("号")
                            # print[b]
                            if b != -1:
                                _str_ = a[0].replace("号", "")
                                # print(f"经过{iii}站: 地址为, {_str_}")
                                table.add_row([f"{ii}", f"{_str_}"])
                            elif b == -1:
                                # print(f"经过{iii}站: 地址为, {a[0]}")
                                table.add_row([f"{ii}", f"{a[0]}"])
                        elif a == []:
                            print(f"{ii}: 抱歉未找到该地址信息")
                    print(table)
                    print("\n")
                    # break
                    
                again = input("是否再次查询?(y/n):")
                table.clear_rows()
                # print(table)
            _or_ = "n"
        if dic_che_ci_number is None:
            _or_ = input("可能由于网络原因或者已经没有车次安排，未取到数据是否重新运行？(y/n)：")
            _or_ = "n"
    pool = ThreadPoolExecutor(999)
    pool.submit(main())
