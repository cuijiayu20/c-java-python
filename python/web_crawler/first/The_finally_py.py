from Spider_12306.Get_all_12306 import over_1
from Spider_12306.Get_all_12306 import che_ci

pt = over_1()

_or_ = "y"
while _or_ == "y":

    print("正在输出获取结果")
    print("单程信息")
    print(pt)

    print("获取结果输出完毕")

    print("-" * 60)
    if che_ci is not None:
        print("正在获取车次路程信息")
        print(che_ci)
        break
    if che_ci is None:
        _or_ = input("可能由于网络原因或者已经没有车次安排，未取到数据是否重新运行？(y/n)：")



