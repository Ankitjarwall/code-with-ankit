#include <iostream>
#include <math.h>
#include <stdio.h>
using namespace std;
int main()
{
    int number, w = 0, sum = 0, r, t;
    cout << "Enter the number : ";
    cin >> number;
    t = number;
    cout << "Your Number : ";
    w = printf("%d", number);
    do
    {
        r = number % 10;
        sum = sum + pow(r, w);
        number = number / 10;
    } while (number != 0);
    if (t == sum)
    {
        cout << " is Armstrong.";
    }
    else
    {

        cout << " is Not Armstrong.";
    }
    return 0;
}