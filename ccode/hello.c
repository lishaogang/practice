#include<stdio.h>
int f = 1;
int main()
{
	
	if(f==1)
	{
	f--;
	printf("%d\n",main());
	}
	
	return 999;
}