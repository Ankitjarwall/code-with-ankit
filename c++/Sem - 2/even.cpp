#include <iostream>
using namespace std;
int main()
{
    int input;
    cout << "Enter the value : ";
    cin >> input;
    if (input % 5 == 0)
    {
        cout << "Divisible by 5." << endl;
    }
    else
    {
        cout << "The number is not divisble by 5." << endl;
    }
    if (input % 2 == 0)
    {
        cout << "Even" << endl;
    }
    else
    {
        cout << "The number is not EVen" << endl;
    }
    return 0;
}