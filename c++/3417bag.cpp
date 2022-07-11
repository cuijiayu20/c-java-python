#include <stdio.h>
#include<mingw.thread.h>
//对元素的一些定义
typedef int item;
typedef int semaphore;

#define n 4//别打分号

int in = 0, out = 0;
item buffer[n];
semaphore mutex = 1, empty = n, full = 0;//标志定义

void producer() {
	item nextp = 0;
	do {
		nextp++;
		while (empty == 0);
		empty--;
		while (!mutex);//进入临界区
		mutex = 0;//临界资源占用标志
		buffer[in] = nextp;
		in = (in + 1) % n;
		mutex = 1;//释放临界资源
		full++;
		printf("执行生产,当前含有商品%d\n", full);
	} while (nextp<=10);
}
void consumer() {
	int nextc = 0;
	item item1, item2;
	do {
		while (full <= 1);
		nextc +=2;
		full-=2;
		while (!mutex);
		mutex = 0;
		item1 = buffer[out];
		out = (out + 1) % n;
		item2 = buffer[out];
		out = (out + 1) % n;
		printf("商品的内容为%d和%d。\n", item1,item2);
		mutex = 1;
		empty+=2;
	} while (nextc <= 10);
}
int main(void) {
	std::thread task01(producer);
	std::thread task02(consumer);
	printf("测试完毕\n");
	task01.join();
	task02.join();
	return 0;
}
