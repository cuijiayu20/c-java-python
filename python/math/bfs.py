from searching_algorithm import * #从searching_algorithm.py 导入功能

# 算法输入数据
f = Field(10,10) # 整体场地

# 设置起点
f.get_cell(0,0).set_state(START)
# 设置初始cost
f.get_cell(0,0).set_cost(0)
# 设置终点
f.get_cell(5,9).set_state(END)

# 设置墙体
walls = [(1,2),(1,3),(1,4),(1,6),(2,6),(3,6),(4,5),(4,6),(4,9),(5,8),(6,7)]
for i in walls:
    f.set_cell_state(i[0],i[1],WALL)

# f.get_cell(2,2).set_state(1)
start=f.get_start()   # 起点位置

# 求解最短路径，使用算法1（BFS）
f.find_shortest(algorithm=1)

# f.show() # 显示地图