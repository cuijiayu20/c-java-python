# from tkinter import N

# 计算mod
a,b,c=-1,-1,1
a=int(input("请输入e"))
b=int(input("请输入m"))
n=int(input("请输入n"))


while a!=0:

    if a%2==0:
        a=a//2
        b=(b*b)%n
        c=c
    elif a%2!=0:
        a=a-1
        c=(c*b)%n
        b=b
    print(a,b,c)
print("答案是",c)


# 37 30 77
# 77 66 119

#计算最大公约数
# a=int(input("请输入第一个整数"))
# b=int(input("请输入第二个整数"))

# if a<b: #如果a<b，则交换两数位置，否则不交换
#     a,b = b,a
# r = a % b #求a/b的余数
# while r != 0: #在余数不为零时，始终进行交换和相除
#     a,b = b,r
#     r = a % b
# print(b) #余数为零后，打印输出b


