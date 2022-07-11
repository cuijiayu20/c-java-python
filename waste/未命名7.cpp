#include<iostream>
#include<cstdio>
#include<cstring>
#include<algorithm>
const int N=10; 
using namespace std;
int n,m,k=2,res,a[N];

void dfs(int u)
{  
	if(u>(n+m))
	{   res++;
	
		return;
	}
	a[u]=0;
	k--;
	dfs[u+1];
	a[u]=-1;
	if(k==0) return;	
	a[u]=1;
	dfs(u+1);
	a[u]=-1;
}
int main()
{  memset (a,-1,sizeof(a));
   scanf("%d",&n,&m);
   dfs(1);
printf("%d",res);
}
