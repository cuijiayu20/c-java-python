  #include<stdio.h>
   int sum=0;
   int y=3; 
int main()
     
{    
    while(y--);//ѭ�������ı�
    {
    sum++;
    ++y;
    printf("y=%d;sum=%d\n",y,sum);
    }
    printf("\ny=%d;sum=%d",y,sum);
}
