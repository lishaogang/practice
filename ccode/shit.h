//tree

struct nodeData
{
    int symbolType; //1: number 2: operator
    int numberValue;
    char operatorValue;
};

struct Node
{
    struct nodeData NData;
    struct Node * lchilden;
    struct Node * rchilden;
};


char insertNode(struct Node * myNode,struct Node * father,char lr);
struct Node * CreateNodesetV(int number,char opr,int type);
void  CreateNode(struct nodeData nd,struct Node * myN);
void visitTree(struct Node * Root);