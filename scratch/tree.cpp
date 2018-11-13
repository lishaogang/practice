#include<iostream>
#define ElemType int
#define pass cout <<'';
using namespace std;
class Tree{
    public:
        ElemType data;
        Tree *left;
        Tree *right;
        char ltag,rtag;
        Tree(ElemType d = 0, Tree* l = NULL, Tree* r = NULL){
            this->data = d;
            this->left = l;
            this->right = r;
            this->ltag = this->rtag = 0;
        }

        void print(){
            cout << this->data << ' ';
            this->left != NULL ? this->left->print(): cout << '.';
            this->right != NULL ? this->right->print() : cout << '.' ;
        }
};
Tree * init(){
    ElemType d;
    cin >> d;
    if(d == 0) return NULL;
    Tree * l = init();
    Tree * r = init();
    return new Tree(d,l,r);
}

int main(int argc, char const *argv[])
{
    /* code */
    Tree *t = init();
    t->print();
    return 0;
}
