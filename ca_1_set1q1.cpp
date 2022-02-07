#include <iostream>
using namespace std;
int main()
{
    int a = 0, b = 1, temp, number;
    cout << "Enter the number : ";
    cin >> number;
    do
    {
        temp = a + b;
        cout << a << ",";
        a = b;
        b = temp;
        number--;
    } while (number != 0);
    return 0;
}