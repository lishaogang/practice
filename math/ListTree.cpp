#include<iostream>
using namespace std;
class Tree{
    private:
        int data;
        Tree * left;
        Tree * right;
        bool leftIsNull;
        bool rightIsNull;
    Tree (int * data, int & pos){
            this->data = data[pos];
            left = data[++pos] != 0 ? new Tree(data,pos) : NULL;
            leftIsNull = left == NULL;
            right = data[++pos] != 0 ? new Tree(data,pos) : NULL;
            rightIsNull = right == NULL;
        }
    Tree * makeList(Tree * pre){
        if(left != NULL)
            left->makeList(this);
        else
            left=pre;
        Tree * next
        if(right != NULL)
            next = right->makeList(this);
        else
            right = next;
        

        
    }
    void show(int n){
            n++;
            if(left!=NULL) left->show(n);
            cout << "data: " << data << ",pile: " << n-1 <<endl;
            if(right!=NULL) right->show(n);
    }
    public:
        Tree(int *data){
            int p = 0;
            new(this) Tree(data,p);
        }

        void show(){
           show(1);
        }
        
};
int main(){
    int data[] = {1,2,3,0,0,4,0,0,5,6,0,0,7,0,0};
    //int data[] = {1,2,3,0,0,0,0,0};
    Tree t(data);
    t.show();
    return 0;
}