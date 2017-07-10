#include<stdio.h>
int main()
{
	int i = 3,j = 3;
	int sum = ++i + i++;// + --i;// + i-- + ++i;

	printf("%d,%d\n", sum,i);

	sum = j++ + ++j;
	printf("%d,%d\n", sum,j);
	return 0;
}
