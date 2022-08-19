#include <iostream>
using namespace std;
int main()
{
    int a, b, c;
    cout << "Enter the value of A : ";
    cin >> a;
    cout << "Enter the value of B : ";
    cin >> b;
   
    cout << "Swap before : A = " << a << " B = " << b << endl;
    //**********METHOD  1***********

    c = a;
    a = b;
    b = c;
    cout << "\tSwap After 3 variable: \nA = " << a << "\n B = " << b << endl;

    //**********METHOD  2***********
    a = a + b;
    b = a - b;
    a = a - b;
    cout << "\tSwap After add two varibale: \nA = " << a << "\n B = " << b << endl;

    //**********METHOD  3***********
    a = a * b;
    b = a / b;
    a = a / b;
    cout << "\tSwap After div two variable: \nA = " << a << "\n B = " << b << endl;
    
    //**********METHOD  4***********
    b = (a+b)-(a=b);
    cout << "\tSwap After Add one line : \nA = " << a << "\n B = " << b << endl;

    //**********METHOD  5 ***********
    b = (a*b)/(a=b);
    cout << "\tSwap After Div one line: \nA = " << a << "\n B = " << b << endl;

    //**********METHOD  6 ***********
    b = (a*b)/(a=b);
    cout << "\tSwap After Div one line: \nA = " << a << "\n B = " << b << endl;

    return 0;
}