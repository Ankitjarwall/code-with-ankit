#include <iostream>
using namespace std;
int main()
{
    int a, b, c;
    cout << "Enter the Value of a : ";
    cin >> a;
    cout << "Enter the Value of b : ";
    cin >> b;
    cout << "Enter the Value of c : ";
    cin >> c;

    if (a > b)
    {
        if (a > c)
        {

            cout << "The highest number is A.";
        }
        if (a < c)
        {
            cout << "The highest number is c.";
        }
    }
    else if (c > a)
    {
        if (c > b)
        {
            cout << "The highest number is c.";
        }
        if (c < b)
        {
            cout << "The highest number is b.";
        }
    }
    else if (b > a)
    {
        if (b > c)
        {
            cout << "The highest number is b.";
        }
        if (b < c)
        {
            cout << "The highest number is c.";
        }
    }

    else
    {
        cout << "INVALID";
    }

    return 0;
}