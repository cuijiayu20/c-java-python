# -*- coding: utf-8 -*-

import matplotlib.pyplot as plt
import numpy as np
from matplotlib.colors import ListedColormap, LinearSegmentedColormap

# state含义
WALL = 1 
START = -1
END = 2

class Cell():
    '''
    格点类
    属性包括位置和取值
    '''
    def __init__(self,x:int,y:int,state=0):
        self.coordinate_x = x
        self.coordinate_y = y
        self.state = state
        self.cost = None
        self.path_direction = None
        
    def set_state(self,state):
        # 设置cell的状态
        self.state = state

    def info(self):
        # 打印cell信息
        print(f"(x,y)=({self.coordinate_x},{self.coordinate_y}),state={self.state}")
    
    def set_cost(self,cost):
        # 设置cell的cost
        self.cost=cost
        # print(f"set{self.coordinate_x},{self.coordinate_y} cost={cost}") # 打印cost修改信息，调试用

    def set_path_direction(self,d):
        self.path_direction=d

class Field():
    '''
    场地类
    由格点构成的长方形场地
    属性
    场地的长和宽
    '''
    def __init__(self,size_x:int,size_y:int):
        self.size_x = size_x
        self.size_y = size_y
        self.cell_list=[]
        for y in range(size_y):
            for x in range(size_x):
                self.cell_list.append(Cell(x,y,0))
    
    def show(self,result=None,title="Searching algorithm"):
        '''
        绘制场地示意图函数
        '''
        fig, ax = plt.subplots()
        # 绘制field
        img_data=np.zeros((self.size_x,self.size_y))
        #根据值更新数据
        for icell in self.cell_list:
            img_data[icell.coordinate_x][icell.coordinate_y]=icell.state
            ax.text(icell.coordinate_y,icell.coordinate_x,icell.cost,ha="center", va="top", color="green")
            ax.text(icell.coordinate_y,icell.coordinate_x,icell.path_direction,ha="center", va="bottom", color="green")
        im = ax.imshow(img_data, cmap= ListedColormap(["red", "white", "black","yellow"]))
        ax.text(1,-2,"result="+str(result),ha="center", va="center", color="black")
        ax.set_title(title)
        plt.show()
        return ax

    def set_cell_state(self,x,y,state):
        self.cell_list[x+y*self.size_x].set_state(state)
        # self.cell_list[x+y*self.size_x].info()

    def get_cell(self,x,y): # 给定field上的坐标，返回相应的cell对象
        return self.cell_list[x+y*self.size_x]

    def get_start(self): # 返回状态为START的节点
        for icell in self.cell_list:
            if icell.state==START:
                return icell
        return None

    def get_end(self): # 返回状态为START的节点
        for icell in self.cell_list:
            if icell.state==END:
                return icell
        return None

    def a_star_cost(self,node):
        cost = node.cost + manhattan_distance(node,self.get_end())
        return cost

    def bfs_cost(self,node):
        cost = node.cost
        return cost

    def get_neighbour(self,node):
    # 按照次序判断node的相邻结点是否被加入frontier_list
        neighbour_list=[]
        if node.coordinate_x-1>=0:
            neighbour_list.append(self.get_cell(node.coordinate_x-1,node.coordinate_y))
        if node.coordinate_x+1<self.size_x:
            neighbour_list.append(self.get_cell(node.coordinate_x+1,node.coordinate_y))
        if node.coordinate_y-1>=0: # 判断相邻点是否在field范围内
            neighbour_list.append(self.get_cell(node.coordinate_x,node.coordinate_y-1))
        if node.coordinate_y+1<self.size_y: # 判断相邻点是否在field范围内
            neighbour_list.append(self.get_cell(node.coordinate_x,node.coordinate_y+1))
        return neighbour_list

    def update_frontier(self,node,frontier_list):
    # 按照次序判断node的相邻结点是否被加入frontier_list
        neighbour_list=self.get_neighbour(node)
        for icell in neighbour_list: 
            if icell.state!=WALL and icell.cost==None:
                icell.set_cost(node.cost+1) # 更新结点cost
                if icell.coordinate_x-node.coordinate_x==1: # 更新结点路径方向
                    icell.set_path_direction("^")
                elif icell.coordinate_x-node.coordinate_x==-1:
                    icell.set_path_direction("v")
                elif icell.coordinate_y-node.coordinate_y==1:
                    icell.set_path_direction("<")
                elif icell.coordinate_y-node.coordinate_y==-1:
                    icell.set_path_direction(">")
                frontier_list.append(icell) # 当前节点加入frontier_list

        return frontier_list    

    def find_node(self,frontier_list,explored_list,algorithm):
    # 在frontier_list中找到下一步要操作的节点
        cost_list=[] # 下一步操作结点的评价标准
        for icell in frontier_list:
            if algorithm==1:
                cost_list.append(self.bfs_cost(icell))
            if algorithm==2:
                cost_list.append(self.a_star_cost(icell))
        node_index=np.argmin(cost_list) # 找到cost最小的结点下标
        node=frontier_list.pop(node_index) # 得到cost最小的结点对象
        explored_list.append(node) # 当前结点列为已搜索结点
        return node,frontier_list,explored_list

    def bfs(self):
        frontier_list = [self.get_start()]
        explored_list = []
        count_run = 0 # 算法运行时间计数
        while True:
        # 在frontier_list里面，找出操作的node
            node,frontier_list,explored_list=self.find_node(frontier_list,explored_list)
            print(f"node state:{node.state},run={count_run}")
            if check_end(node): # node 是否为终点
                print(f"node is end.cost={node.cost}")
                break
            else:
                frontier_list=self.update_frontier(node=node,frontier_list=frontier_list) # 如果不是终点，更新frontier_list
                if frontier_list==[]: # 如果更新完毕后，frontier_list为空，则问题无解
                    print("No solution")
                    break
            count_run=count_run+1
        self.show(result=node.cost,title="BFS")

    def find_shortest(self,algorithm=1):
        frontier_list = [self.get_start()]
        explored_list = []
        count_run = 0 # 算法运行时间计数
        while True:
        # 在frontier_list里面，找出操作的node
            node,frontier_list,explored_list=self.find_node(frontier_list,explored_list,algorithm)

            print(f"node state:{node.state},run={count_run}")
            if check_end(node): # node 是否为终点
                print(f"node is end.cost={node.cost}")
                break
            else:
                frontier_list=self.update_frontier(node=node,frontier_list=frontier_list) # 如果不是终点，更新frontier_list
                if frontier_list==[]: # 如果更新完毕后，frontier_list为空，则问题无解
                    print("No solution")
                    break
            count_run=count_run+1
        self.show(result=node.cost,title="BFS")

def manhattan_distance(node1,node2):
    return np.abs(node1.coordinate_x-node2.coordinate_x)+np.abs(node1.coordinate_y-node2.coordinate_y)

def a_star(field,node):
    cost=node.cost+manhattan_distance(node,field.get_end())
    return cost

def bfs(field,node):
    cost=node.cost
    return cost

def get_neighbour(field,node):
    # 按照次序判断node的相邻结点是否被加入frontier_list
    neighbour_list=[]
    if node.coordinate_x-1>=0:
        neighbour_list.append(field.get_cell(node.coordinate_x-1,node.coordinate_y))
    if node.coordinate_x+1<field.size_x:
        neighbour_list.append(field.get_cell(node.coordinate_x+1,node.coordinate_y))
    if node.coordinate_y-1>=0: # 判断相邻点是否在field范围内
        neighbour_list.append(field.get_cell(node.coordinate_x,node.coordinate_y-1))
    if node.coordinate_y+1<field.size_y: # 判断相邻点是否在field范围内
        neighbour_list.append(field.get_cell(node.coordinate_x,node.coordinate_y+1))
    return neighbour_list

def update_frontier(field,node,frontier_list):
    # 按照次序判断node的相邻结点是否被加入frontier_list
    neighbour_list=get_neighbour(field,node)
    for icell in neighbour_list: 
        if icell.state!=WALL and icell.cost==None:
            icell.set_cost(node.cost+1) # 更新结点cost
            if icell.coordinate_x-node.coordinate_x==1: # 更新结点路径方向
                icell.set_path_direction("^")
            elif icell.coordinate_x-node.coordinate_x==-1:
                icell.set_path_direction("v")
            elif icell.coordinate_y-node.coordinate_y==1:
                icell.set_path_direction("<")
            elif icell.coordinate_y-node.coordinate_y==-1:
                icell.set_path_direction(">")
            frontier_list.append(icell) # 当前节点加入frontier_list

    return frontier_list

def find_node(field,frontier_list,explored_list,cost_function): # 在frontier_list中找到下一步要操作的节点
    cost_list=[] # 下一步操作结点的评价标准
    for icell in frontier_list:
        cost_list.append(cost_function(field,icell))
    node_index=np.argmin(cost_list) # 找到cost最小的结点下标
    node=frontier_list.pop(node_index) # 得到cost最小的结点对象
    explored_list.append(node) # 当前结点列为已搜索结点
    return node,frontier_list,explored_list

def check_end(node): # 判断一个结点是否是结束
    if node.state==END:
        return True
    else:
        return False

