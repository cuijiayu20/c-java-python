for i in range(1000,10000):
   a=int(i/1000)
   b=int((i-1000*a)/100)
   c=int((i-1000*a-100*b)/10)
   d=int(i-1000*a-100*b-10*c)
   if i==pow(a,4)+pow(b,4)+pow(c,4)+pow(d,4):
      print(i)
