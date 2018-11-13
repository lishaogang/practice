#include "shit.h"
#include <malloc.h>
#include <stdio.h>

struct Node *CreateNodesetV(int number, char opr, int type)
{
    struct nodeData db;
    db.numberValue = number;
    db.operatorValue = opr;
    db.symbolType = type;

    struct Node *myNode = (struct Node *)malloc(sizeof(struct Node));

    CreateNode(db, myNode);
    return myNode;
};

void CreateNode(struct nodeData nd, struct Node *myN)
{
    myN->NData.numberValue = nd.numberValue;
    myN->NData.operatorValue = nd.operatorValue;
    myN->NData.symbolType = nd.symbolType;

    myN->lchilden = NULL;
    myN->rchilden = NULL;
};

char insertNode(struct Node *myNode, struct Node *father, char lr)
{
    char result = 'Y';
    if (lr == 'L')
    {
        if (father->lchilden == NULL)
        {
            father->lchilden = myNode;
        }
        else
        {
            result = 'N';
        }
    }
    else
    {
        if (father->rchilden == NULL)
        {
            father->rchilden = myNode;
        }
        else
        {
            result = 'N';
        }
    }

    return result;
}

//原来树的遍历也就是递归.我去,我说自己怎么老是不得要领.根源在于没想明白递归函数.
void visitTree(struct Node *Root)
{
    if (Root->lchilden != NULL)
    {
        visitTree(Root->lchilden);
    }
    else
    {
        //终止递归的确定事件,是无事件.
    }

    if (Root->NData.symbolType == 1)
    {
        printf("%d", Root->NData.numberValue);
    }
    else
    {
        printf("%c", Root->NData.operatorValue);
    }

    if (Root->rchilden != NULL)
    {
        visitTree(Root->rchilden);
    }
    else
    {
        //终止递归的确定事件,是无事件.
    }
}

//所以如果用递归来做运算必须是后序遍历，要等左右树都有结果了，才用符号。
//其实我们的心算和笔算，也是后续遍历。

int visitTree_2(struct Node *Root)
{
    int lvalue;
    int rvalue;
    int result;

    if (Root->lchilden != NULL)
    {
        lvalue = visitTree_2(Root->lchilden);
    }
    else
    {
        return Root->NData.numberValue;
    }

    if (Root->rchilden != NULL)
    {
        rvalue = visitTree_2(Root->rchilden);
    }
    else
    {
        return Root->NData.numberValue;
    }

    switch (Root->NData.operatorValue)
    {
    case '+':
    {
        result = lvalue + rvalue;
        break;
    }
    case '-':
    {
        result = lvalue - rvalue;
        break;
    }
    case '*':
    {
        result = lvalue * rvalue;
        break;
    }
    case '/':
    {
        result = lvalue / rvalue;
        break;
    }
    }

    return result;
}