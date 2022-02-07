#include <iostream>
using namespace std;
int main()
{
    int a=0, b;
    cout << "Enter the number : ";
    cin >> a;
    for (int i = 1; i < a; i++)
    {
        if (i * i == a)
        {
            cout << "The square root of " << a << " is " << i << endl;
            return 0;
        }
    }
    cout<<"INVLID input";
    return 0;
}