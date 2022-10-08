a="0123456789ABCDEF"
b=list(bin(int(a,16))[2:])
lisM=[['0','0','0','0'],['0','0','0','1']]
lb=['0','0','0','0','0','0','0']
for i in b:
    lb.append(i)


j=0
ls=[]
for i in range(1,len(lb)):
    
    if j<4:
        ls.append(lb[i])
        j+=1
    else:
        lisM.append(ls)
        ls=[]
        ls.append(lb[i])
        j=1
        
        
print(lisM)
# print(lb)


k=list("")
col = 0
result = []

list2 = [32,1,2,3,4,5,4,5,6,7,8,9,8,9,10,11,12,13,12,13,14,15,16,17,16,17,18,19,20,21,20,21,22,23,24,25,24,25,26,27,28,29,28,29,30,31,32,1]
try:
    for i in list2:
        result.append(lb[i-1])

except:
    print('i')

for i in result:

    print(i, end=" ")
print(len(result))

d0="1010101011001100111100000000"
c0="1111000011001100101010100000"
R0=""