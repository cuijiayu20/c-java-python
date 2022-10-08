# 该文件用于分配驱动，进行实例化
import re
from time import sleep

import msedge.selenium_tools
from selenium import webdriver

from selenium.webdriver.chrome.service import Service as chrome_Service

from msedge.selenium_tools import EdgeOptions
from msedge.selenium_tools import Edge

from Browser_operation.Get_browser_instance import Judgment_browser
from Browser_operation.Get_browser_instance import browser_list
from Browser_operation.Get_browser_instance import number

Judgment_browser()
driver_path_edge = r".\Browser_operation\Browser_driven\Edge\msedgedriver.exe"
driver_path_google = r".\Browser_operation\Browser_driven\Chrome\chromedriver.exe"

browser_name = re.findall("浏览器名称：(.*),", browser_list[number[0] - 1])[0]
browser_path = re.findall("浏览器地址：(.*?)]", browser_list[number[0] - 1])[0]


def Browser_Instantiation():
    print("正在配置驱动")
    sleep(0.1)

    driver = ""
    if browser_name == "edge":

        edge_options = EdgeOptions()

        edge_options.use_chromium = True  # 使用谷歌内核

        edge_options.add_experimental_option("excludeSwitches", ['enable-automation', 'enable-logging'])
        driver = Edge(executable_path=driver_path_edge, options=edge_options)  # 这里虽然有问题，但是NM竟然可以用

        driver.set_window_position(y=9999, x=9999)

    elif browser_name == "谷歌":

        chrome_options = webdriver.ChromeOptions()
        chrome_options.add_experimental_option("excludeSwitches", ['enable-automation', 'enable-logging'])
        driver = webdriver.Chrome(executable_path=driver_path_google, options=chrome_options)
        driver.set_window_position(y=9999, x=9999)

    print("驱动配置完毕")
    sleep(0.1)

    print("-" * 60)
    print("正在加载页面")
    return driver
