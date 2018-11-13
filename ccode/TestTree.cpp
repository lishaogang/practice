#include <iostream>
#include <string>
#include "tree.cpp"

#define TABLE_SIZE 100
using namespace std;

Tree<char> *init_char_tree(std::string path);
static int TABLE[TABLE_SIZE] = {5};

void initTable()
{
    for(int i = 0; i < TABLE_SIZE; i++) TABLE[i] = 5;
    TABLE['-'] = TABLE['+'] = 0;
    TABLE['*'] = TABLE['/'] = 1;
    TABLE['^'] = 2;
}


string n_rbrackets(int n){
    string str;
    while(n-->0){
        str = str + ")";
    }
    return str;
}

string t2e(Tree<char> *t)
{
    string str, left, right;
    if (t->left == NULL)
        return str = str + t->data + "";
    left = t2e(t->left);
    right = t2e(t->right);
    if (TABLE[t->left->data] < TABLE[t->data])
    {
        left = '(' + left + ')';
    }
    if (TABLE[t->right->data] < TABLE[t->data])
    {
        right = '(' + right + ')';
    }
    return str = str + left + t->data + right;
}

string tree2infix(Tree<char> *t)
{
    list<Tree<char> *> tl;
    string str;
    int rbrackets = 0;
    char op_in_s, op_in_e = '0';
    Tree<char> * pre_node;
    while (t != NULL || !tl.empty())
    {
        if (t != NULL)
        {
            op_in_s = !isnum(t->data) ? t->data : op_in_s;
            tl.push_front(t);
            t = t->left;
        }
        else
        {
            t = tl.front();
            if(TABLE[op_in_e] < TABLE[op_in_s]){
                str = "(" + str + ")" + t->data;
            }
            if(TABLE[pre_node->data] > TABLE[pre_node->right->data]){
                str = str + "(";
                rbrackets++;
            }
            if(pre_node->right == t && t->right == NULL){
                str = str + n_rbrackets(rbrackets);
                rbrackets = 0;
            }
            //str  = str + t->data;
            op_in_e = !isnum(t->data) ? t->data : op_in_e;

            pre_node = t;
            tl.pop_front();
            t = t->right;
        }
    }
    return str;
}

int main(int argc, char const *argv[])
{
    /* code */
    string path;
    cin >> path;
    Tree<char> *e = init_char_tree(path);
    e->IO_print2();
    initTable();
    cout << t2e(e) << endl;;
    cout << tree2infix(e) << endl;
    return 0;
}

Tree<char> *init_char_tree(std::string path)
{
    std::list<Tree<char> *> lt;
    Tree<char> *temp, *root, *cur;
    char data = ' ';

    data = input<char>(path, '#');
    //TODO: 空树应当考虑，待完善
    root = new Tree<char>(data);
    lt.push_back(root);

    while ((data = input<char>(path, '#')) != '#' && !lt.empty())
    {
        cur = lt.front();
        lt.pop_front();

        //左孩子
        temp = data != '.' ? new Tree<char>(data) : NULL;
        cur->left = temp;
        temp != NULL ? lt.push_back(temp) : pass;

        //右孩子
        data = input<char>(path, '#');
        temp = data != '.' ? new Tree<char>(data) : NULL;
        cur->right = temp;
        temp != NULL ? lt.push_back(temp) : pass;
    }
    std::cout << "init over" << std::endl;
    return root;
}