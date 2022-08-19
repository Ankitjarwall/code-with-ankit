#include <iostream>
using namespace std;
int main()
{
    int a = 0, b = 0, c = 0;
    cout << "Enter the input A : ";
    cin >> a;
    cout << "Enter the input B : ";
    cin >> b;
    c = (a * a) + (b * b) + (2 * a * b);
    cout << "Value : " << c;
    return 0;
}