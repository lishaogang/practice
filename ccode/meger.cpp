#include<iostream>
#include<vector>
using namespace std;
vector<int> merge(vector<int>& nums1, vector<int>& nums2)
    {
        vector<int> arr;
        int i_len = nums1.size();
        int j_len = nums2.size();
        int i,j;
        for(i = 0 , j = 0; i != i_len && j != j_len;){

                nums1[i] < nums2[j] ? arr.push_back(nums1[i++]) : arr.push_back(nums2[j++]);
        }
        while(i < i_len)
            arr.push_back(nums1[i++]);
        while(j < j_len)
            arr.push_back(nums2[j++]);
        return arr;
    }
    
int main(void)
{
    vector<int> a = {2};
    vector<int> b = {1,3};
    vector<int> arr = merge(a,b);
    int len = arr.size();
    for(int i = 0; i < len; ++i)
        {
            std:cout << arr[i];
        }
    return 0;
}
