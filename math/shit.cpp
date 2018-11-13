#include<iostream>
#define InitSize 100
#define MaxSize InitSize
using namespace std;

typedef struct{
    int  data[MaxSize];
    int length;
} SqList;

bool ListInsert(SqList &L, int i, int e){
    cout << "----------" << endl << "insert "<< e << " at  " << i << endl;
    if(i < 1 || i > L.length+1)
        return false;
    if(L.length >= MaxSize)
        return false;
    for(int j = L.length; j>= i; j--)
        L.data[j] = L.data[j-1];
    L.data[i-1] = e;
    L.length++;
    return true;
}

void show(SqList &L){
    for(int i = 0; i < L.length; i++)
        cout << L.data[i] << ' ' << (i%10==0 ? '\n' : ' ');
    cout << "length:" << L.length << endl;
}
int main()
{
    SqList L;
    int i;
    L.length = 0;
    show(L);
    for(i = 0; i < MaxSize; i++)
        ListInsert(L,i+1,i);
    show(L);
    ListInsert(L,i,999);
    show(L);
    return 0;
}