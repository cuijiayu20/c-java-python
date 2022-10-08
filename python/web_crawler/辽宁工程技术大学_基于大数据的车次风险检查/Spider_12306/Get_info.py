import datetime
import sys

from Spider_12306.Get_stations import stations


def get_info():
    print("进入操作页面")

    _or_ = "y"
    while _or_ == "y":
        fw = input("请输入出发地>>>")
        if fw in stations.keys():
            pass
        else:
            print("未找到该地区")
            sys.exit()

        tw = input("请输入目的地>>>")
        if fw in stations.keys():
            pass
        else:
            print("未找到该地区")
            sys.exit()

        st = input("请输入出发时间；格式：(例如：2022-09-05)(回车默认为今日日期)>>>>")
        if st == '':
            st = datetime.date.today()
            return fw, tw, st
        else:
            today = datetime.date.today()
            date = str(today).split('-')
            _list_ = st.split('-')
            if int(_list_[0]) < int(date[0]) or int(_list_[0]) > int(date[0]):
                exit("输入的年份不在我的查询范围之内")
            else:
                if int(_list_[1]) < int(date[1]) or int(_list_[1]) > int(date[1]) + 1:
                    exit("你输入的月份不在我的查询范围之内")
                else:
                    if int(_list_[2]) < int(date[2]):
                        exit("你输入的日期不在我的查询范围之内")
                    else:
                        if int(_list_[1]) < 10 and int(_list_[1][0]) != 0:
                            _list_[1] = '0' + _list_[1]
                        if int(_list_[2]) < 10 and int(_list_[2][0]) != 0:
                            _list_[2] = '0' + _list_[2]
                        return fw, tw, _list_[0] + '-' + _list_[1] + '-' + _list_[2]
        _or_ = input("是否确定输入无误, 否则将返回重新输入(y/n)")

    print("退出输入界面")
