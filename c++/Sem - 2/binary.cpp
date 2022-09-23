#include<iostream>
using namespace std;
int binary(int arr[],int lb,int ub,int mid,int x){
    if(arr[mid]==x){
        return mid;
    }
    else if(arr[mid]<x){
        lb = mid + 1;
        mid = (lb + ub) / 2;
        binary(arr, lb, ub, mid, x);
    }
    else if (arr[mid] > x)
    {
        ub = mid - 1;
        mid = (lb + ub) / 2;
        binary(arr, lb, ub, mid, x);
    }
}
int main(){
    int arr[5] = {1, 2, 3, 4, 5}, lb = 0, ub = 4, x,mid=(lb+ub)/2;
    cout << "Enter data to be found: ";
    cin >> x;
    int k;
   k= binary(arr, lb, ub, mid, x);
   cout << "Element was found at " << k << " index";
}