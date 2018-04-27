#include <stdio.h>
int tree [] = {0,0,0,2,2,3,3};

void print()
{
	for (int i = 0; i < 7; ++i)
	{
		/* code */
		printf("%d:%d \n", i, tree[i]);
	}
}

int find(int x)
{
	if(x != tree[x]){
		printf("%d's superior is %d\n", x,tree[x]);
		tree[x] = find(tree[x]);
	}
	return tree[x];
}

int main()
{
	int x = 5;
	printf("found %d's root : %d\n", x,find(x));
	print();
}