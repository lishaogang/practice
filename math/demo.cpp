#include<iostream>
using namespace std;
int d[] = {3,7,3,2,1,1,8,7,3};
int f(){
	int i, j, k;
	k = 0;
	for(i = 0; i < 9; i++){
		for(j = 0; j < k && d[i] != d[j]; j++);
		if(j == k){
			if(k !=i) d[k] = d[i];
			k++;
		}
	}
	for(int i = 0; i < 9; i++)
			cout << d[i];
	cout << endl;
}
int main(){
	f();
	return 0;	
}
