#include <iostream>
#include <fstream>
#include <list>

#define ElemType int
#define pass (void)NULL
#define MAX 100
using namespace std;

class Tree;
Tree *init_in_line();
Tree *init();
ElemType input();
void visit(Tree *t);

class Tree
{
  public:
    ElemType data;
    Tree *left;
    Tree *right;
    char ltag, rtag;
    Tree(ElemType d = 0, Tree *l = NULL, Tree *r = NULL)
    {
        this->data = d;
        this->left = l;
        this->right = r;
        this->ltag = this->rtag = 0;
    }
    void FO_print();
    void IO_print();
    void IO_print2();
    void print();
    void PO_print();
};

int main(int argc, char const *argv[])
{
    /* code */
    Tree *t = init_in_line();
    t->FO_print();
    t->IO_print();
    t->PO_print();
    return 0;
}

void Tree::PO_print()
{
    typedef struct SNode
    {
        SNode(Tree *p)
        {
            this->node = p;
        };
        Tree *node;
        bool rvisited;
    } SNode;

    list<SNode> tl;
    Tree *p = this;
    SNode pnode = NULL;
    cout << "Post oder: ";
    while (p != NULL)
    {
        tl.push_front(SNode(p));
        p = p->left;
    }
    while (!tl.empty())
    {
        pnode = tl.front();
        if (pnode.node->right == NULL || pnode.rvisited == true)
        {
            tl.pop_front();
            visit(pnode.node);
        }
        else
        {
            pnode.rvisited = true;
            p = pnode.node->right;
            while(p != NULL){
                tl.push_front(SNode(p));
                p = p->left;
            }
        }
    }
    cout << endl;
}

void Tree::print()
{
    cout << this->data << ' ';
    this->left != NULL ? this->left->print() : pass;
    this->right != NULL ? this->right->print() : pass;
}

void Tree::FO_print()
{
    list<Tree *> tl;
    Tree *p;
    tl.push_front(this);
    cout << "Fisrt order: ";
    while (!tl.empty())
    {
        p = tl.front();
        tl.pop_front();
        visit(p);
        p->right != NULL ? tl.push_front(p->right) : pass;
        p->left != NULL ? tl.push_front(p->left) : pass;
    }
    cout << endl;
}
void Tree::IO_print2()
{
    list<Tree *> tl;
    Tree *p = this;
    while (p != NULL || !tl.empty())
    {
        if (p != NULL)
        {
            tl.push_front(p);
            p = p->left;
        }
        else
        {
            p = tl.front();
            tl.pop_front();
            visit(p);
            p = p->left;
        }
    }
}
void Tree::IO_print()
{
    list<Tree *> tl;
    Tree *p = this;
    bool tag = false;
    if (this->left == NULL && this->left == NULL)
    {
        visit(this);
        return;
    }
    //初始化栈
    //初始化栈完毕
    tl.push_front(this);
    p = this;

    while (!tl.empty())
    {

        if (p->left == NULL || tag == true)
        {
            visit(p);
            if (p->right != NULL)
            {
                p = p->right;
                tag = false;
            }
            else
            {
                p = tl.front();
                tl.pop_front();
                tag = true;
            }
        }
        if (p->left != NULL && tag == false)
        {
            tl.push_front(p);
            p = p->left;
        }
    }
    cout << "over..." << endl;
}

//0 表示输入结束， -1 表示该节点为空
//若遇到非数字，则视为结束，data_file >> data ： data = 0
ElemType input()
{
    ElemType data = 0; //初始化应重写
    static ifstream data_file("data.txt");
    if (!data_file.is_open() || data_file.eof())
    {
        cout << "Not find data or the data is over" << endl;
        return 0;
    }
    data_file >> data;

    return data;
}

Tree *init_in_line()
{
    list<Tree *> lt;
    Tree *temp, *root, *cur;
    ElemType data = 0;

    data = input();
    //TODO: 空树应当考虑，待完善
    root = new Tree(data);
    lt.push_back(root);

    while ((data = input()) != 0 && !lt.empty())
    {
        cur = lt.front();
        lt.pop_front();

        //左孩子
        temp = data != -1 ? new Tree(data) : NULL;
        cur->left = temp;
        temp != NULL ? lt.push_back(temp) : pass;

        //右孩子
        data = input();
        temp = data != -1 ? new Tree(data) : NULL;
        cur->right = temp;
        temp != NULL ? lt.push_back(temp) : pass;
    }
    cout << "init over" << endl;
    return root;
}
Tree *init()
{
    ElemType d;
    cin >> d;
    if (d == 0)
        return NULL;
    Tree *l = init();
    Tree *r = init();
    return new Tree(d, l, r);
}
void visit(Tree *t)
{
    if (t != NULL)
        cout << t->data << " ";
}