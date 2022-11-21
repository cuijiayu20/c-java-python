#include<iostream>
#include<stdio.h>
using namespace std;
int main(){
	int a;
	for(a=1;a<=9;a++){
		int x=a;
		for(int b=1;b<=a;b++){
			x=x-1;
			printf("%d",x);
		}
		printf("\n");
	}
} 
