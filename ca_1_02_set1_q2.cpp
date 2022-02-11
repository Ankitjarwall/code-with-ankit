#include <iostream>
using namespace std;
int main()
{
    int digit = 0, sum = 0, n = 0;
    cout << "Enter the number : ";
    cin >> digit;

    do
    {
        sum = digit % 10;
        n = sum + n;
        digit = digit / 10;
    } while (digit != 0);
    cout << n;

    return 0;
}