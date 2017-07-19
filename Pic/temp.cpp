# include <stdio.h>
# include <stdlib.h>
#include <string.h>
#include <time.h>
int main (void)
{
int num = 100;
char str[]= "_sthis is strin";
char *s = "this is string";
printf("%d\n",strlen(s));
strcpy(str+1,s);
printf("The number 'num' is %d and the string 'str' is %s. \n" ,
num, str);
printf("%d",clock());
return 0;
}
