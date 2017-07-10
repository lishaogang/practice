#include<stdio.h>
int fun()
{
	printf("hello\n");
	return 0;
}

int main()
{
	int (*PF)() = &fun;
	(*PF)();
}