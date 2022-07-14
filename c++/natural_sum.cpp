#include <iostream>
using namespace std;
int main()
{
    int a=0, b=0, i=0;
    cout << "Enter the n number : ";
    cin >> a;
    for (int i = 0; i <= a; i++)
    {
        b = b + i;
    }
    cout << "The sum of n number is " << b;
    return 0;
}