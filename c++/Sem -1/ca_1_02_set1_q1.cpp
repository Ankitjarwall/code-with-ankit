#include <iostream>
using namespace std;

int addition(int a, int b);
int division(int a, int b);
int subtraction(int a, int b);

int addition(int a, int b)
{
    int c = 0;
    c = a + b;
    return c;
}
int subtraction(int a, int b)
{
    int c = 0;
    c = a - b;
    return c;
}
int division(int a, int b)
{
    int c = 0;
    c = a / b;
    return c;
}

int main()
{
    int value1 = 0, value2 = 0;
    cout << "Enter the 1st Value : ";
    cin >> value1;
    cout << "Enter the 2nd Value : ";
    cin >> value2;

    cout << "The of Addition" << value1 << " + " << value2 << " : " << addition(value1, value2) << endl;
    cout << "The of substraction" << value1 << " - " << value2 << " : " << subtraction(value1, value2) << endl;
    cout << "The of Division" << value1 << " / " << value2 << " : " << division(value1, value2) << endl;
    return 0;
}