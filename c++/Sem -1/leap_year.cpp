#include <iostream>
using namespace std;
int main()
{
    int a = 0;
    cout << "Enter the year : ";
    cin >> a;
    if (a % 4 == 0)
    {
        if (a % 100 == 0)
        {
            if (a % 400 == 0)
            {

                cout << "the year is leap year.";
            }
            else
            {

                cout << "The year is not leap year.";
            }
        }
        else
        {
            cout << "The year is leap year.";
        }
    }
    else
    {
        cout << "The year is not leap year.";
    }
    return 0;
}