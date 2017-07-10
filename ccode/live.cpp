#include<iostream>
#include <stdlib.h>
using namespace std;

struct Cell
{
 bool live;
 int  others;
};


int main()
{

 Cell cell[10][10];

 for(int i=0;i<10;i++)
  for(int j=0;j<10;j++)
  {
   cell[i][j].live=true;
   cell[i][j].others=0;
  }

 while(1)
 {
  for(int i=0;i<10;i++)
   for(int j=0;j<10;j++)
   {
    cell[i][j].others=0;
   }

  for(int i=0;i<10;i++)
  {
   for(int j=0;j<10;j++)
   {
    if(cell[i][j].live)
     cout<<"$ ";
    else
     cout<<"- ";
   }
   cout<<endl;
  }

  for(int i=0;i<10;i++)
   for(int j=0;j<10;j++)
   {
    if((i-1)>=0 && (j-1)>=0 && cell[i-1][j-1].live)
     cell[i][j].others++;
    if((i-1)>=0 && cell[i-1][j].live)
     cell[i][j].others++;
    if((i-1)>=0 && (j+1)<10 && cell[i-1][j+1].live)
     cell[i][j].others++;
    if((j-1)>=0 && cell[i][j-1].live)
     cell[i][j].others++;
    if((j+1)<10 && cell[i][j+1].live)
     cell[i][j].others++;
    if((i+1)<10 && (j-1)>=0 && cell[i+1][j-1].live)
     cell[i][j].others++;
    if((i+1)<10 && cell[i+1][j].live)
     cell[i][j].others++;
    if((i+1)<10 && (j+1)<10 && cell[i+1][j+1].live)
     cell[i][j].others++;

    switch(cell[i][j].others)
    {
    case 2:break;
    case 3:cell[i][j].live=true;break;
    default:cell[i][j].live=false;break;
    }
   }
   _sleep(1000);
   //clrscr();
   system("cls");
 }
}
