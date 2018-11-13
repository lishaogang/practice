#include "tree.h"
#include <fstream>
#include <list>

#define pass (void)NULL
#define MAX_HEiGHT 100

bool isnum(char ch)
{
    return ch >= '0' && ch <= '9';
}

template <typename ElemType>
Tree<ElemType> *init_in_line(std::string path)
{
    std::list<Tree<ElemType> *> lt;
    Tree<ElemType> *temp, *root, *cur;
    ElemType data = 0;

    data = input<ElemType>(path);
    //TODO: 空树应当考虑，待完善
    root = new Tree<ElemType>(data);
    lt.push_back(root);

    while ((data = input<ElemType>(path)) != 0 && !lt.empty())
    {
        cur = lt.front();
        lt.pop_front();

        //左孩子
        temp = data != -1 ? new Tree<ElemType>(data) : NULL;
        cur->left = temp;
        temp != NULL ? lt.push_back(temp) : pass;

        //右孩子
        data = input<ElemType>(path);
        temp = data != -1 ? new Tree<ElemType>(data) : NULL;
        cur->right = temp;
        temp != NULL ? lt.push_back(temp) : pass;
    }
    std::cout << "init over" << std::endl;
    return root;
}


template <typename ElemType>
void Tree<ElemType>::PO_print()
{
    bool visited[MAX_HEiGHT] = {false};
    Tree<ElemType> *p = this;
    std::list<Tree<ElemType> *> tl;
    while (p != NULL)
    {
        tl.push_front(p);
        p = p->left;
    }
    std::cout << "Post order:";
    while (!tl.empty())
    {
        p = tl.front();
        if (p->right == NULL || visited[tl.size() - 1] == true)
        {
            visit(p);
            visited[tl.size() - 1] = false;
            tl.pop_front();
        }
        else
        {
            visited[tl.size() - 1] = true;
            p = p->right;
            while(p != NULL){
                tl.push_front(p);
                p = p->left;
            }
        }
    }
    std::cout << std::endl;
}

template <typename ElemType>
void Tree<ElemType>::FO_print()
{
    std::cout << "Fisrt oder traverse: ";
    std::list<Tree<ElemType> *> tl;
    tl.push_front(this);
    Tree<ElemType> *p;
    while (!tl.empty())
    {
        p = tl.front();
        visit(p);
        tl.pop_front();
        if (p->right != NULL)
            tl.push_front(p->right);
        if (p->left != NULL)
            tl.push_front(p->left);
    }
    std::cout << " ..." << std::endl;
}

template <typename ElemType>
void Tree<ElemType>::IO_print2()
{
    std::list<Tree *> tl;
    Tree<ElemType> *p;
    p = this;
    std::cout << "In oder traverse: ";
    while (!tl.empty() || p != NULL)
    {
        if (p != NULL)
        {
            tl.push_front(p);
            p = p->left;
        }
        else
        {
            p = tl.front();
            visit(p);
            tl.pop_front();
            p = p->right;
        }
    }
    std::cout << " ..." << std::endl;
}

template <typename ElemType>
void Tree<ElemType>::IO_print()
{
    std::list<Tree<ElemType> *> tl;
    Tree<ElemType> *p = this;
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
    std::cout << "over..." << std::endl;
}

//0 表示输入结束， -1 表示该节点为空
//若遇到非数字，则视为结束，data_file >> data ： data = 0
template <typename ElemType>
ElemType input(std::string path, ElemType stop = 0)
{
    ElemType data; //初始化应重写
    static std::ifstream data_file(path);
    if (!data_file.is_open() || data_file.eof())
    {
        //std::cout << "Not find data or the data is over " << stop << std::endl;
        //std::cout << stop << std::endl;
        return stop;
    }
    data_file >> data;
    return data;
}




template <typename ElemType>
void visit(Tree<ElemType> *t)
{
    if (t != NULL)
        std::cout << t->data << " ";
}


