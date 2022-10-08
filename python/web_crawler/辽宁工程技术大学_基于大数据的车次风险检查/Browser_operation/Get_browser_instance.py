# 该文件用来获取浏览器

import sys
import re
from time import sleep

import requests
from selenium import webdriver
from selenium.webdriver.edge.options import Options
from Browser_operation.Get_browser import get_browser_path

browser_list = []
browser_path_list = []
number = []


def Judgment_browser():  # 判断用户用的是什么浏览器

    # 1. 找浏览器位置
    print('正在检测浏览器位置……')
    global dic
    dic = {
        "谷歌": get_browser_path('chrome'),
        "edge": get_browser_path('edge'),
    }

    # 2.输出浏览器位置
    count = 1
    for i in dic.items():
        if i[1] is not None:
            print(f"检测到浏览器：{i[0]}")
            browser_list.append(f"[编号：{count}, 浏览器名称：{i[0]}, 浏览器地址：{i[1]}]")
            count += 1
        else:
            pass
    if not browser_list:
        print("未找到浏览器")
        print("本程序仅支持谷歌, edge")
        print("程序即将退出")
        sleep(1)
        sys.exit()

    # 3. 选择浏览器
    else:
        for ii in browser_list:
            print(ii)
        _or_ = input("是否选择浏览器？ y/n : ")


        while _or_ == ("y" or "n"):
            if _or_ == "n":
                print("未选择浏览器，程序即将退出")
                sleep(1)
                sys.exit()
            elif _or_ == "y":
                num = input("请选择浏览器编号：")
                if num in str(list(range(0, len(browser_list) + 1))) and num != "" and num != " ":
                    if num == "0":
                        print("请输入正确的编号")
                        sleep(1)
                        sys.exit()
                    else:
                        num = int(num)
                        browser_path = re.search("浏览器地址：(.*?)]", browser_list[num-1]).group(1)
                        browser_path_list.append(browser_path)
                        number.append(num)
                        break
                else:
                    print("请输入正确的编号")
                    sleep(1)
                    sys.exit()

        else:
            print("您输入的不是系统认定的字符，程序即将退出")
            sleep(1)
            sys.exit()

        print("-" * 60)

        return browser_list

