#include <iostream>
using namespace std;
int main()
{
    int a = 0, b = 0;
    cout << "Enter the number : ";
    cin >> a;       //5

    b = a++ + a++ + a++ + ++a;
    //b = 8++ + 7++ + 6++ + 6;

    cout << "A Value :" << a<<endl;//9
    cout << "B Value :" << b<<endl;//24//27

    return 0;
}