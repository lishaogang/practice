#include<stdio.h>
#include<malloc.h>
#define MAX 1024
//--!更改成表驱动
char input[] = "int i, j; i = 1.023;bool f = i >= j;";
int p = 0; //下标, 表示现在已经读到input的哪个位置
char word[24]; // 当前读出的单词


//获取inpu中的下一个字符
char nextChar()
{
	
	return p < MAX ? input[p++] : '\0';

}

int identifier(char c);
int operator(char c);
int digit(char c);




//读取下一个单词
//返回1表示读到单词,存在word中,否则读取完毕
int nextWord()
{
	char c = nextChar();
	//消除前导的空格   如"  int, i, j;",
	while (c == ' ')
		c = nextChar();
	//如果到末尾,则返结束
	if(c=='\0')
		return -1;
	//如果读到数字,则往下读一个完整的数字
	//例如 "int i = 95001;", 则某一次nextWord()调用一定返回 '='
	//而下一次就会读到 '9', 则此时应当读取95001这个数字
	if(c >= '0' && c <= '9')
		return digit(c);
	
	//读取标识符,标识符以字母或者下划线开头, 如 _id   _id1  id123  num123id 等等
	if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || c == '_')
		return identifier(c);
	
	return operator(c);
}

int main()
{
	
	printf("input: %s\n",input);
	while(nextWord() != -1)
		printf("output: %s\n",word);
	return 0;
	
}




int  identifier(char c)
{
	int i = 0; //这次读取的标识符的长度
	word[i++] = c; //开头已经读取的字符
	c = nextChar();
	while(c != ' ' && ((c >= 'a' && c <= 'z')
					 || (c >= 'A' && c <= 'Z')
				     || (c >= '0' && c <= '9')))
	{
		word[i++] = c;
		c = nextChar();
	}
	p--;	//全局变量,再line 4定义
			//最后会读取一个不属于这个单词的字符,应当退回
			// 如 "j," 要读出 ','才会退出上面的循环, 而此时p的值
			//已经是表示','的下标
	word[i] = '\0'; 
	return 1;
}

int digit(char c)
{
	int i = 0;
	word[i++] = c;
	c = nextChar();
	while (c != ' ' && (c <= '9' && c >= '0')){
			word[i++] = c;
			c = nextChar();
			
	}
	//读到小数点,继续读取
	if(c == '.'){
		
		word[i++] = c;
		c = nextChar();
		while (c != ' ' && c != '#'  && (c <= '9' && c >= '0')){
			
			word[i++] = c;
			c = nextChar();
			
		}
	}
	p--;
	word[i] = '\0';
	return 1;
}

int operator(char c)
{
	word[0] = c;
	word[1] = '\0';
	//没有读下一个,不需要回退 p
	// 不能把 >= 识别成一个 单词  可以单独把一个字符以上的操作符 如 >= <= 单独处理
	c = nextChar();
	if(c == '=' && (word[0] == '>' || word[0] == '<'))
		word[1] = c;
	else
		p--;
	word[2] = '\0';
	return 1;
}