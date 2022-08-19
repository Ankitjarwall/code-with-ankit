#include <iostream>
using namespace std;

void power(int x);
void power(int x)
{
    if (x <= 50)
    {
        cout << "Amonut to pay : " << x * 6.8 << " $" << endl;
    }
    else if (x > 50 && x <= 200)
    {
        cout << "Amonut to pay : " << x * 7.8 << " $" << endl;
    }
    else if (x > 200 && x <= 500)
    {
        cout << "Amonut to pay : " << x * 8.8 << " $" << endl;
    }
    else if (x > 500)
    {

        cout << "Amonut to pay : " << x * 9.8 << " $" << endl;
    }
}

int main()
{
    int elect_unit;
    cout << "\nElectricity Unit charges below 50 : 6.8 $" << endl;
    cout << "Electricity Unit charges above 50 to 200 : 7.8 $" << endl;
    cout << "Electricity Unit charges above 200 to 500 : 8.8 $" << endl;
    cout << "Electricity Unit charges more than 500 : 9.8 $\n"
         << endl;
    cout << "Enter the unit used : ";
    cin >> elect_unit;
    cout << "Total Electricity Unit : " << elect_unit << endl;
    power(elect_unit);
    return 0;
}