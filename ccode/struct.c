#include<stdio.h>
#include<stdlib.h>
#include<malloc.h>
typedef struct LinkNode
{
	struct LinkNode * pNext;
} tLinkNode;
typedef struct LinkTable{
	tLinkNode * head;
	tLinkNode * tail;
} tLinkTable;

typedef struct DataLinkNode
{
	
	tLinkNode * pNext;
	int data;
} tDataLinkNode;

int main(void)
{
	tLinkTable * Table = (tLinkTable*)malloc(sizeof(tLinkTable));
	
	tDataLinkNode * pNode = (tDataLinkNode*)malloc(sizeof(tDataLinkNode));
	tDataLinkNode * temp = pNode;
	pNode->data = 99;
	pNode->pNext = NULL;
	
	Table->head = (tLinkNode*)pNode;
	Table->tail = Table->head;
	
	pNode = (tDataLinkNode*)malloc(sizeof(tDataLinkNode));
	pNode->data = 11;
	Table->tail->pNext = (tLinkNode*)pNode;
	if(Table->tail->pNext == temp->pNext)
		printf("Equal\n");
	return 0;
}