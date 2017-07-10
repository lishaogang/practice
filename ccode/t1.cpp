#include <iostream>
using namespace std;

template <class T, class T1>
class TestClass
{
public:
	TestClass()
	{
		cout<<"T, T1"<<endl;
	}
};
template <class T1>
class TestClass<int, T1*>
{
public:
	TestClass()
	{
		cout<<"T*, T1*"<<endl;
	}
};

template <class T, class T1>
class TestClass<const T*, T1*>
{
public:
	TestClass()
	{
		cout<<"const T*, T1*"<<endl;
	}
};

int main()
{
	TestClass<int, char> obj;
	TestClass<int *, char *> obj1;
	TestClass<const int *, char *> obj2;
	
	return 0;
}