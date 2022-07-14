#include <iostream>
using namespace std;
int main()
{
    int user_input;
    cout << "Charge of unit less than 80 unit : 9.5 INR" << endl;
    cout << "Charge of unit from 80 unit to 150 unit : 10.5 INR" << endl;
    cout << "Charge of unit more then 150 unit: 12.0 INR" << endl;
    cout << "\nUnit consumed : ";
    cin >> user_input;

    if (user_input <= 80)
    {
        cout << "Amout need to pay : " << user_input * 9.5;
    }
    if (user_input > 80 && user_input <= 150)
    {
        cout << "Amout need to pay : " << user_input * 10.5;
    }
    if (user_input >= 150)
    {
        cout << "Amout need to pay : " << user_input * 12.0;
    }
    return 0;
}