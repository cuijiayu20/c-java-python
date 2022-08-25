# -*- coding = utf-8 -*-
# @time : 2022/8/13 18:43
# @Author : 特让他也让
# @File : 测试用.py
# @Software : PyCharm
import requests
from selenium import webdriver
from selenium.webdriver.edge.options import Options
from lxml import etree
from time import sleep
from concurrent.futures import ThreadPoolExecutor
import re
import os

# 一.程序建立
# 提示
print('正在建立自动化程序')
print('\n')

# 1.建立自动化程序
# 进行页面隐藏
options = Options()
options.add_argument("--headless")  # 隐藏界面的参数
# 进行自动化
diver = webdriver.Edge(options=options)
url = 'https://mp.weixin.qq.com/mp/appmsgalbum?__biz=MzIzNjY1ODg1NA==&action=getalbum&album_id=1837296374816522241&scene=173&from_msgid=2247556526&from_itemidx=1&count=3&nolastread=1#wechat_redirect'
diver.get(url)
print('自动化程序建立完毕')
sleep(1)
print('即将对页面进行操作，期间请勿关闭界面')
sleep(1)

# 二.执行页面程序
# 执行js程序
print('开始进行页面操作，请等待')
sleep(1)
diver.execute_script("""
var a = 0;
setInterval(function(){
    window.scrollTo(0, document.body.scrollHeight/3*a);   //18为自定义滑动距离，当前代码为每秒向下滑动1/18
    a++;
    console.log(a);
 }, 100);  // 500为间隔时间，单位毫秒
""")
# 10秒后完成
print('进行倒数')
for i in range(10, 0, -1):
    print(i)
    sleep(1)
print('页面操作完成，即将进行源码分析')
print('\n')
sleep(1)

# 三.解析页面源码
# 获取页面源码
print('进行源码分析')
page = diver.page_source
# 解析源码，获得子页面链接
print('正在获得子页面链接,请稍等')
# 建立函数
child_page_list = []
page_etree = etree.HTML(page)
page_xpath = page_etree.xpath('//*[@id="js_content_overlay"]/div[1]/div/div[5]/ul/li/@data-link')
for ii in page_xpath:
    child_page_list.append(ii)
print('子页面获取成功')

# 四.从子链接中获取下载地址,并存到文件中
print('正在获取中')
sleep(1)


def download(urls):
    response = requests.get(urls)
    response_tree = etree.HTML(response.text)
    title = response_tree.xpath('//*[@id="activity-name"]/text()')
    title_1 = re.findall('[^\x00-\xff][a-zA-Z ]*', str(title))  # 文章标题
    path = r'D:\公众号'
    aa = os.path.exists(path)
    if not aa:
        os.mkdir(r'D:\公众号')
    else:
        pass
    with open(f'D:\\公众号\\{str(title_1)}.txt', 'a', encoding='utf-8') as fin:
        for count in range(80, 88):
            download_url = response_tree.xpath(f'//*[@id="js_content"]/p[{count}]/span/text()')
            fin.write(str(download_url[0]))


pool = ThreadPoolExecutor(500)
b = 0
while b < 340:
    pool.submit(download, child_page_list[b])
    b += 1

# 自动化关闭
diver.quit()
