#include<iostream>
#include<string>
using namespace std;
struct person
{
	string name;
	int count;
}leader[3]={{"Li",0},{"Zhang",0},{"Sun",0}};
int main()
{
	cout<<leader[1].name<<leader[1].count<<endl;
	return 0;
}

