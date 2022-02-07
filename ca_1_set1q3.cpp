#include <iostream>
using namespace std;
int main()
{
    int table = 0, i = 1;
    cout << "Enter the number : ";
    cin >> table;
top:
    cout << table << " x " << i << " = " << table * i << endl;
    i++;
    if (i <= 10)
    {
        goto top;
    }

    return 0;
}