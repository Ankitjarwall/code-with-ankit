#include <iostream>
using namespace std;
int main()
{
    int a, b = 1, i = 1;
    cout << "Enter the number : ";
    cin >> a;
    for (int i = 1; i <= a; i++)
    {
        b = i * b;
    }
    cout << "Thr factorail of A is : " << b;
    return 0;
}