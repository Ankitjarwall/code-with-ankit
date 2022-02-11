#include <iostream>
using namespace std;

void elec(int a);
void elec(int a)
{
    if (a <= 100)
    {
        cout << "Amonut to be payed : " << a * 6.8;
    }
    else if (a > 100 && a <= 200)
    {
        cout << "Amonut to be payed : " << a * 7.8;
    }
    else if (a > 200 && a <= 300)
    {
        cout << "Amonut to be payed : " << a * 8.8;
    }
    else if (a > 300)
    {

        cout << "Amonut to be payed : " << a * 9.8;
    }
}

int main()
{
    int unit = 0;
    cout << "Enter the unit used : ";
    cin >> unit;
    cout << "\nUnit charge below 100 : 6.8 INR" << endl;
    cout << "Unit charge above 100 to 200 : 7.8 INR" << endl;
    cout << "Unit charge above 200 to 300 : 8.8 INR" << endl;
    cout << "Unit charge more than 300 : 9.8 INR\n"
         << endl;
    cout << "Total unit : " << unit << endl;
    elec(unit);
    return 0;
}