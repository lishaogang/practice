#ifndef TREE
#define TREE
#include<string>
#include <iostream>
template <typename ElemType>
class Tree;

template <typename ElemType>
Tree<ElemType> *init_in_line(std::string path);

template <typename ElemType>
ElemType input(std::string path);

template <typename ElemType>
void visit(Tree<ElemType> *t);

template <typename ElemType>
class Tree
{
  public:
    ElemType data;
    Tree<ElemType> *left;
    Tree<ElemType> *right;
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
    void PO_print();
};

#endif  