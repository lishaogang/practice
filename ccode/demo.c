#include<stdio.h>
#define N 2
void output(const int * a){
	for(int i = 0; i < N; i++)
		printf("%d ",a[i]);
	printf("\n");
}

void f(int *a, int n){
	for(a[n] = 1; a[n] < 10; a[n]++)
		if(n == 0)
			output(a);
		else
			f(a,n-1);
}
int main(){
	int a[N];
	f(a,1);
}
