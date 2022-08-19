#include <iostream>
#include <iomanip>
using namespace std;
int main()
{
    int user;
    cout << "Enter the table number : ";
    cin >> user;

    for (int i = 1; i <= 10; i++)
    {
        cout << user <<setw(3)<< "X" <<setw(3)<< i <<setw(3)<< "="<<setw(3) << i * user << endl;
    }

    return 0;
}