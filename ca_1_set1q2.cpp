#include <iostream>
using namespace std;
int main()
{
    int user_input;
    cout << "Unit consumed : ";
    cin >> user_input; //210

    if (user_input <= 100)
    {
        cout << "Amout : " << user_input * 7.5;
    }
    if (user_input > 100 && user_input <= 200)
    {
        cout << "Amout : " << user_input * 8.5;
    }
    if (user_input >= 200)
    {
        cout << "Amout : " << user_input * 9.5;
    }
    return 0;
}