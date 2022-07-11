  #include<stdio.h>
   int sum=0;
   int y=3; 
int main()
     
{    
    while(y--);//循环条件改变
    {
    sum++;
    ++y;
    printf("y=%d;sum=%d\n",y,sum);
    }
    printf("\ny=%d;sum=%d",y,sum);
}
