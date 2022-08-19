#include <iostream>
using namespace std;
int main()
{
    int digit = 0, i = 1;
    cout << "Please enter the table number : ";
    cin >> digit;
up:
    cout << digit << " x " << i << " = " << digit * i << endl;
    i++;
    if (i <= 10)
    {
        goto up;
    }

    return 0;
}
