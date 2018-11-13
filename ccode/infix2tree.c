#include<stdio.h>
#include "shit.h"

int lHeight(char oldc,char newl);
int main()
{
    char * expression="(2+3)*5-4/2";

    //每次取一个数字和操作符，对比操作符和之前操作符的优先顺序。
    //状1，大于，则符号为之前树的右子树，数字为符号的左子树。
    //状2，小于，则数字为之前树的右字树，之前树为符号的左子树。
    //直至最后，只有一个数字符，此数字为之前符号的右子树，

    char preOpe='#';
    struct Node * PreNode='\0';
    struct Node * RootNode='\0';
    int i=0;
    int strLen=getStrLen(expression);
    while(i<strLen)
    {
        if(i==0)//
        {
            int cNumber=expression[i]-48;
            i++;
            char cOperator=expression[i];
            i++;
            struct Node * LeftC=CreateNodesetV(cNumber,' ',1);
            struct Node * Root=CreateNodesetV(0,cOperator,2);
            insertNode(LeftC,Root,'L');
            preOpe=cOperator;
            PreNode=Root;
            RootNode=Root;
        }
        else
        {
            if(i+2<strLen)//get a number and operator
            {
                int cNumber=expression[i]-48;
                i++;
                char cOperator=expression[i];
                i++;
                if(lHeight(preOpe,cOperator)==1)
                {
                    struct Node * numberNode=CreateNodesetV(cNumber,' ',1);
                    struct Node * OpeNode=CreateNodesetV(0,cOperator,2);
                    insertNode(OpeNode,PreNode,'R');
                    insertNode(numberNode,OpeNode,'L');
                    preOpe=cOperator;
                    PreNode=OpeNode;
                }
                else if (lHeight(preOpe,cOperator)==0)
                {
                    struct Node * numberNode=CreateNodesetV(cNumber,' ',1);
                    struct Node * OpeNode=CreateNodesetV(0,cOperator,2);
                    insertNode(RootNode,OpeNode,'L');
                    insertNode(numberNode,PreNode,'R');
                    preOpe=cOperator;
                    PreNode=OpeNode;
                    RootNode=OpeNode;
                }
            }
            else//last char
            {
                int cNumber=expression[i]-48;
                i++;
                struct Node * numberNode=CreateNodesetV(cNumber,' ',1);
                insertNode(numberNode,PreNode,'R');
            }
        }
    }

    if(RootNode!='\0')
    {
        visitTree(RootNode);
        printf("\nresult:%d",visitTree_2(RootNode));
    }

    return 0;
}

int getStrLen(char * c)
{
    int i=0;
    while(c[i]!='\0')
    {
        i++;
    }
    return i;
}

int lHeight(char oldc,char newl)
{
    if(oldc=='+'||oldc=='-')
    {
        if(newl=='*'||newl=='/')
        {
            return 1;
        }
    }
    else if(oldc=='#')
    {
        return 1;
    }
    return 0;
}