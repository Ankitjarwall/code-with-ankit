#include<iostream>
using namespace std;
int main()
{
    int sum[10] = {1, 2, 3, 4, 5, 6, 8, 7, 9, 0};
    for(int i:sum)
    {
        cout << "Element " << i << ": " << sum[i]<<endl;
    }
    return 0;
}