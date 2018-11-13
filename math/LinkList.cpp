#include<iostream>
using namespace std;
class Node{
    private:
        int data;
        Node * next;
    public:
        Node(int data,Node * next = NULL){
            this->data = data;
            this->next = next;
        }
        friend class List;
};

class List{
    private:
        //head is a indicator pointing no data  while tail points a node
        //and mark the end of the list by set the value of tail->next  to NULL
        Node * head;
        Node * tail;
    public:
        List(){
            head = new Node(0);
            tail = head;
        }
        // initialize a list with morethan 0 node
        List(initializer_list<int> ls){
            head = new Node(0);
            tail = head;
            for(auto ptr = ls.begin(); ptr != ls.end(); ptr++){
                Node *node = new Node(*ptr);
                tail->next = node;
                tail = tail->next;
            }
        }
        
        //return 1 if insert successfully, 0 therwise.
        //position starts from 1
        bool insert(int data, size_t pos){
            Node * t = head;
            //this first sequence of conditions will cause bug when insert into a position
            //out of boundary
            //while((--pos) && (t != NULL)){
            while((t != NULL) && (--pos > 0)){
                t = t->next;
            }
            
            if(t == NULL) return false;
            
            t->next = new Node(data,t->next);
            return true;
        }
        
        bool remove(size_t pos){
            
            Node * t = head; 
            while(t != NULL && --pos > 0){
                
                t = t->next;
                
            }
            //t point to the pre-node of the node to delete.
            if(t == NULL || t->next == NULL) return false;
            
            Node *trash = t->next;
            t->next = t->next->next;
            delete trash;
            return true;
        }
        void print(){
            Node * t = head->next;
            cout << "----";
            while(t){
                cout << t->data << " ";
                t = t->next;
            }
            cout << "----";
            cout<<endl;
        }
};

int main(){
    List l({1,2,3,4,5});
    l.print();
    l.insert(9,1);
    l.print();
    List s;
    s.print();
    
    s.insert(1,1);
    s.insert(2,2);
    s.print();
    s.insert(3,3);
    s.insert(4,4);
    s.print();
    cout << "remove" <<endl;
    s.remove(1);
    s.print();
    s.remove(2);
    s.print();
    s.remove(3);
    s.print();
    return 0;
}