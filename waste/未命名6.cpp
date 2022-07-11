#include<iostream>
#include<cstdio>
#include<cstring>
#include<algorithm>
const int N=11000; 
using namespace std;

int n,m,k;
int s[N][N];
int res=0;
int c[N][N];
int a=50;	
int main()
{  	memset(c,0,sizeof(c));
    memset(s,0,sizeof(s));
    scanf("%d%d%d",&n,&m,&k);
	for(int i=1;i<=n;i++)
	{
		for(int j=1;j<=m;j++)
		{
			scanf("%d",&s[i][j]);
			c[50][a]=s[i][j];
			a++;
		}
	}
	for(int i=51;i<=(51+n*m);i++)
	{   for(int j=50;j<=N;j++)
		{
		c[i][j]=c[i-1][j]+c[i-1][j-m];
		j++;
		c[i][j]=c[i-1][j]+c[i-1][j+m];
		j++;
		c[i][j]=c[i-1][j]+c[i-1][j-1];
		j++;
		c[i][j]=c[i-1][j]+c[i-1][j+1];	
	    }
	}
	for(int i=51;i<=(51+n*m);i++)
	{   for(int j=50;j<=N;j++)
		{
			if(c[i][j]<=k&&c[i][j]!=0)
			{
				res++;
				if(c[i-1][j-m]==0||c[i-1][j-1]==0)//||c[i-1][j+1]==0)
				res--;
			}	
	    }
	}
	
printf("%d",res	);
	
	return 0;
} 
