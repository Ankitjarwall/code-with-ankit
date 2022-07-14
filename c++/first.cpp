#include <iostream>
#include <string>
using namespace std;
int main()
{
    int a, b;
    cout << "Enter the 1st number : ";
    cin >> a;
    cout << "Enter the 1st number : ";
    cin >> b;

    cout << "The values before of A =" << a << ",b =" << b << endl;

    a=a^b;
    b=a^b;
    a=a^b;

    cout << "The values after of A =" << a << ",b =" << b;

    return 0;
}