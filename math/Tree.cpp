#include<iostream>
using namespace std;
class Node{
    private:
        int data;
        Node* left;
        Node* right;
    public:
        Node(int *data, int & pos){
            this->data = data[pos];
            left = data[++pos] != 0 ? new Node(data,pos) : NULL;
            right = data[++pos] != 0 ? new Node(data,pos) : NULL;
        }
        void show(int n){
            cout << "data: " << this->data << "; pile:" << n++ << " ";
            if(left != NULL) left->show(n);

            if(right != NULL) right->show(n);
        }
        friend class Tree;
};

class Tree{
    private:
        Node * root;
    public:
        Tree(int *data){
            int pos = 0;
            root = new Node(data,pos);
        }
        void show(){
            if(root != NULL) root->show(1);
        }
};

int main(){
    int data[] = {1,2,0,0,3,0,4,0,0};
    Tree t(data);
    t.show();
    return 0;
}