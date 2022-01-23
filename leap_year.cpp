#include <iostream>
using namespace std;
int main()
{
    int year;
    cout << "Enter the year : ";
    cin >> year;
    if (year % 4 == 0)
    {
        if (year / 100 == 0)
        {
            cout << "the year is leap year.";
            if (year / 400 == 0)
            {
                cout << "the year is leap year.";
            }
            else
            {
                cout << "@@@@@@@@@@ the year is not leap year.";
            }
        }
        else
        {
            cout << "############# the year is not leap year.";
        }
    }
    else
    {
        cout << "************* the year is not leap year.";
    }
    return 0;
}