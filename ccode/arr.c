#include<stdio.h>
int main()
{
	int *p;
	int arr[3][5] = {{1,2,3,4,5},{1,2,3,4,5},{1,2,3,4,5}};
	p = &arr[0][0];
	printf("%d\n", *p);
	p+=10;
	printf("%d\n", *p);

	return 0;
}
