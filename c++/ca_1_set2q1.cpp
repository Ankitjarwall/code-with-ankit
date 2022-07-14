#include <iostream>
using namespace std;
int main()
{
    int digit = 0, update = 0, j = 0, a = 0, b = 1;
    cout << "Enter the number  : ";
    cin >> digit;
    for (; digit != 0;)
    {
        j = a + b;
        cout << a << ",";
        a = b;
        b = j;
        digit--;
    }

    return 0;
}