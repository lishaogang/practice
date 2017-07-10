#include<stdio.h>
#include<string.h>
int fun(char * ShellCode)
{
	char str[4]="";
	strcpy(str,ShellCode);
	printf("%s\n",str);
	return 1;
}

int main()
{
	unsigned char code[]="123456789abcdef1111";
	fun(code);
	return 0;
}