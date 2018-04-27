#include<stdio.h>
#include<math.h>
#include<stdlib.h>

#define N 150

void fun(double x)
{
    int i; 
    //printf("%f===>",x);
    for(i = 0; i <= 5; ++i)
        printf("%20.12f ",(pow(x,(6-i))));
    printf(";\n ");
}
void getMat(int *arr, int len)
{
    int i = 0;
    for(i = 0; i < len; ++i)
        fun(rand()/750.0);
}
    
void char2int(char *s, int len)
{
    int i;
    for(i = 0; i < len; ++i)
        printf("%d ", s[i]);
    printf("\n\n");
}
//[15;15;15;15;15;15;15;15;15]
int main(void)
{
    //int name[] = {-94 -127 -27 -69 -70 -27 -115 -114};
    int name[] = {1,2,3,4,5,6,7,8,9};
    getMat(name,8);
    int z = (6)*pow(-24,5);
    printf("%d\n",z);
    return 0;
}