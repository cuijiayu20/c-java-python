#include<iostream>
#include<cstdio>
#include<cstring>
#include<algorithm>
const int N=10010;
using namespace std;
int n;
int tree[N];
int sum[N];
int main()
{   memset(tree,0,sizeof(tree));
    memset(sum,0,sizeof(sum));
    scanf("%d",&n);
    
    for(int j=1;j<=n;j++)//循环天数 
	{
		for(int i=1;i<=n;i++ )
		{
			tree[i]++;
			sum[i]=max(sum[i],tree[i]);
		}
		tree[j]=0;
		
		
		
	}
for(int i=1;i<=n;i++)	
{
	for(int j=n-1;j>0;j--)//循环天数 
	{
		for(int i=1;i<=n;i++ )
		{
			tree[i]++;
			sum[i]=max(sum[i],tree[i]);
		}
		
		tree[j]=0;
	
	}
	 for(int j=2;j<=n;j++)//循环天数 
	{
		for(int i=1;i<=n;i++ )
		{
			tree[i]++;
			sum[i]=max(sum[i],tree[i]);
		}
		tree[j]=0;
		
		
		
	}
}
	for(int i=1;i<=n;i++)
	{
		printf("%d",sum[i]);
		printf("\n");
	}
	
    
	
	return 0;
} 
