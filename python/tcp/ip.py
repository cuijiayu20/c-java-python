import numpy as np 
from matplotlib import pyplot as plt 


#程序输入-------

# '''测试数据
# 1
# 16
# 12
# -1
# 22

# 1
# 16
# 12
# 21
# 26
# '''

cwin = eval(input('请输入初始拥塞窗口大小：')) #拥塞窗口
ssthresh = eval(input('请输入初始阈值大小：')) #初始阈值

#是否超时 >0-True  -1-False
ifTimeoutRoundIndex = eval(input('请输入超时发生时的传输轮次（没有则输-1）：')) #超时发生时的传输轮次

#是否重传 >0-True  -1-False
ifReTransmissionRoundIndex = eval(input('请输入重传发生时的传输轮次（没有则输-1）：')) #重传发生时的传输轮次

roundCount = eval(input("请输入传输轮次：")) #传输轮次


#算法----------

listXResult = [] #后期绘图用的x坐标集（此处直接用roundCount算出也可以）
listYResult = [] #后期绘图用的y坐标集
1


for roundIndex in range(roundCount):
    print("第", roundIndex, "轮：", "，拥塞窗口大小：", cwin)


    #图：收集x, y坐标
    listXResult.append(roundIndex)
    listYResult.append(cwin)

    #先判断，是否超时或重传------------

    #---若超时--->转-慢开始
    if (roundIndex == ifTimeoutRoundIndex):
        ssthresh = cwin // 2
        cwin = 1
        continue
    #---若重传--->转-快重传
    if (roundIndex == ifReTransmissionRoundIndex):
        ssthresh = cwin // 2
        cwin = ssthresh
    #--------------------------------

    #+++++++慢开始
    #【拥塞窗口 < 初始阈值】
    if (cwin < ssthresh):
        cwin *= 2
        #避免因x2而越界
        if (cwin > ssthresh):
            cwin = ssthresh

    #+++++++拥塞避免
    #【拥塞窗口 > 初始阈值 且 当前轮次 < 超时发生时的传输轮次】
    elif (cwin >= ssthresh): 
        cwin += 1

    

#程序输出-------
# print("----------------------------------")
# print(listXResult)
# print(listYResult)
# 中文乱码解决方法
plt.rcParams['font.family'] = ['Arial Unicode MS','Microsoft YaHei','SimHei','sans-serif']
plt.rcParams['axes.unicode_minus'] = False
plt.xlabel("传输次数 roundIndex")
plt.ylabel("拥塞窗口 cwin")

plt.plot(listXResult, listYResult, 'r')
plt.grid()#添加网格
plt.show()
