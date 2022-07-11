#include<iostream>
#include<cstdio>
#include<cstring>
#include<algorithm>

using namespace std;

int a,b,n;
int day=0,sum=0;
int main()
{  scanf("%d%d%d",&a,&b,&n);
while(sum<=n+1)
{
	for(int i=1;i<=5;i++)
   {
   	 if(sum>=n)
   	 {
   	 	printf("%d",day);
   	 	return 0;
   	 }
   	 
   	 sum=sum+a;
   	 day++;
   	 
   }
    for(int i=1;i<=2;i++)
   {
   	 if(sum>=n)
   	 {
   	 	printf("%d",day);
   	 	return 0;
   	 }
   	 sum=sum+b;
   	 day++;
}
   
   	 
   }

} 
